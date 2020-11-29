package by.kukshinov.hotel.connection;

import by.kukshinov.hotel.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int POOL_SIZE = 10;
    private static ConnectionPool pool;

    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;
    private static final ReentrantLock LOCKER = new ReentrantLock();

    private ConnectionPool() throws DaoException {
        this.availableConnections = new ArrayDeque<>();
        this.connectionsInUse = new ArrayDeque<>();
        for (int runner = 0; runner < POOL_SIZE; ++runner) {
            ProxyConnection e = new ProxyConnection(createConnection());
            availableConnections.add(e);
        }
    }

    public static ConnectionPool getInstance() {
        if (pool == null) {
            try {
                LOCKER.lock();
                ConnectionPool localInstance;
                if (pool == null) {
                    localInstance = new ConnectionPool();
                    pool = localInstance;
                }
            } catch (DaoException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                LOCKER.unlock();
            }
        }
        return pool;
    }


    private Connection createConnection() throws DaoException {
        String url = "jdbc:mysql://localhost:3306/hotel_management?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "root");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection(url, prop);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        LOCKER.lock();
        try {
            ProxyConnection proxyConnection = availableConnections.poll();
            connectionsInUse.offer(proxyConnection);
            return proxyConnection;
        } finally {
            LOCKER.unlock();
        }
    }

    public void releaseConnection(ProxyConnection connection) {
        LOCKER.lock();
        try {
            if(connectionsInUse.contains(connection)) {
                connectionsInUse.remove(connection);
                availableConnections.offer(connection);
            }
        } finally {
            LOCKER.unlock();
        }
    }
    
    public void killConnections() {
        for (int runner = 0; runner < POOL_SIZE; ++runner) {
            ProxyConnection activeConnection = connectionsInUse.poll();
            availableConnections.offer(activeConnection);
            ProxyConnection availableConnection = availableConnections.poll();
            try {
                availableConnection.killConnection();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

}
