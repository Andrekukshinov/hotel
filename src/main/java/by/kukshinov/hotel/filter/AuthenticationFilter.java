package by.kukshinov.hotel.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private static final String LOGIN = "login";
    private static final String COMMAND = "command";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    //todo return 403 page
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        Object login = session.getAttribute(LOGIN);
        String command = req.getParameter(COMMAND);
        if (login != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (LOGIN.equals(command)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(LOGIN_PAGE);
            requestDispatcher.forward(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
