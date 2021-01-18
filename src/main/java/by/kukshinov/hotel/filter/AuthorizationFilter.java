package by.kukshinov.hotel.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private static final String COMMAND = "command";
    private static final String ADMIN = "admin";
    private static final String ROLE = "role";
    private static final int FORBIDDEN = 403;
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String USER = "USER";
    private static final String HOME = "home";
    private static final String LOGOUT = "logout";
    private static final String LOGIN = "login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String command = req.getParameter(COMMAND);
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute(ROLE);
        if (command != null && command.contains(ADMIN)) {
            if (!ROLE_ADMIN.equalsIgnoreCase(role)) {
                resp.sendError(FORBIDDEN);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else if (command != null) {
            if(USER.equals(role) || (command.equals(HOME) || command.equals(LOGOUT) || command.equals(LOGIN))) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                resp.sendError(FORBIDDEN);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
