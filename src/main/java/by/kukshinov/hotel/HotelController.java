package by.kukshinov.hotel;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.command.CommandFactory;
import by.kukshinov.hotel.dao.DaoException;
import by.kukshinov.hotel.dao.UserDao;
import by.kukshinov.hotel.dao.UserDaoImpl;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;

public class HotelController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String commandParam = req.getParameter("command");
            Command command = CommandFactory.createCommand(commandParam);
            CommandResult commandResult = command.execute(req, resp);
            dispatchRequest(req, resp, commandResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispatchRequest(HttpServletRequest req, HttpServletResponse resp, CommandResult commandResult) throws ServletException, IOException {
        boolean redirect = commandResult.isRedirect();
        String urlToGoTo = commandResult.getPageUrl();
        if (redirect) {
            resp.sendRedirect(urlToGoTo);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(urlToGoTo);
            dispatcher.forward(req, resp);
        }
    }
}


class J {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/hotel_management?serverTimezone=UTC&useSSL=false";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "root");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        UserDao userDao;
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        try {
            cn = DriverManager.getConnection(url, prop);
            userDao = new UserDaoImpl(cn);
            Optional<User> user = userDao.findByCredentials("admin", "pass");
            System.out.println(user.get());
        } catch (SQLException | DaoException e) {
            System.err.println("DB connection error: " + e);

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch
                (SQLException e) {
                    System.err.println("Сonnection close error:" + e);
                }
            }
        }
    }
}
