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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String command = req.getParameter(COMMAND);
        if (command != null && command.contains(ADMIN)){
            HttpSession session = req.getSession();
            Object role = session.getAttribute(ROLE);
            if(!ROLE_ADMIN.equalsIgnoreCase((String) role)){
                resp.sendError(FORBIDDEN);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
