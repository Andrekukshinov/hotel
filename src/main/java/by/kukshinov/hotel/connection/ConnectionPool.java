package by.kukshinov.hotel.connection;

import by.kukshinov.hotel.exceptions.ConnectionPoolException;
import by.kukshinov.hotel.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int POOL_SIZE = 10;
    private static final ReentrantLock SINGLETON_LOCKER = new ReentrantLock();
    private static final ReentrantLock LOCKER = new ReentrantLock();
    private static ConnectionPool pool;
    private static final Semaphore CONNECTION_SEMAPHORE = new Semaphore(POOL_SIZE);

    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;
    private final ConnectionFactory connectionFactory;

    private ConnectionPool() throws DaoException {
        this.availableConnections = new ArrayDeque<>();
        this.connectionsInUse = new ArrayDeque<>();
        this.connectionFactory = new ConnectionFactory();
        for (int runner = 0; runner < POOL_SIZE; ++runner) {
            ProxyConnection e = new ProxyConnection(connectionFactory.createConnection(), this);
            availableConnections.add(e);
        }
    }

    public static ConnectionPool getInstance() {
        AtomicBoolean isNullInstance = new AtomicBoolean(pool == null);
        if (isNullInstance.get()) {
            try {
                SINGLETON_LOCKER.lock();
                ConnectionPool localInstance;
                if (isNullInstance.getAndSet(false)) {
                    localInstance = new ConnectionPool();
                    pool = localInstance;
                }
            } catch (DaoException e) {
                throw new ConnectionPoolException(e.getMessage(), e);
            } finally {
                SINGLETON_LOCKER.unlock();
            }
        }
        return pool;
    }

    public ProxyConnection getConnection() {
        try {
            CONNECTION_SEMAPHORE.acquire();
            LOCKER.lock();
            ProxyConnection proxyConnection = availableConnections.poll();
            connectionsInUse.offer(proxyConnection);
            return proxyConnection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        } finally {
            LOCKER.unlock();
        }
    }

    public void releaseConnection(ProxyConnection connection) {
        LOCKER.lock();
        try {
            if (connectionsInUse.contains(connection)) {
                connectionsInUse.remove(connection);
                availableConnections.offer(connection);
                CONNECTION_SEMAPHORE.release();
            }
        } finally {
            LOCKER.unlock();
        }
    }

    public void killConnections() {
        connectionsInUse.forEach(this::releaseConnection);
        closeQueueConnections(availableConnections);
    }

    private void closeQueueConnections(Queue<ProxyConnection> availableConnections) {
        for (ProxyConnection connection : availableConnections) {
            try {
                connection.killConnection();
            } catch (SQLException e) {
                throw new ConnectionPoolException(e.getMessage(), e);
            }
        }
    }

}
