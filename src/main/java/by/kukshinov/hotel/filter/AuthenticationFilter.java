package by.kukshinov.hotel.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private static final String LOGIN_COMMAND = "login";
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
        Object login = session.getAttribute(LOGIN_COMMAND);
        String command = req.getParameter(COMMAND);
        String requestURL = req.getRequestURL().toString();
        if (login != null || LOGIN_COMMAND.equals(command) || isResourcesAccess(command, requestURL)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(LOGIN_PAGE);
            requestDispatcher.forward(servletRequest, servletResponse);
        }
    }

    private boolean isResourcesAccess(String command, String requestURL) {
        boolean b = (requestURL.contains("/static/")) && command == null;
        return b;
    }

    @Override
    public void destroy() {

    }
}
