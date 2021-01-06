package by.kukshinov.hotel.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private static final String LOGIN_COMMAND = "login";
    private static final String LOGOUT_COMMAND = "logout";
    private static final String DISABLED_COMMAND = "disabled";
    private static final String COMMAND = "command";
    private static final String DOMAIN = "http://localhost:8081/hotel/";
    private static final int UNAUTHORIZED = 401;
    private static final String RESOURCES = "/static/";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    //todo return 403 page
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Object login = session.getAttribute(LOGIN_COMMAND);
        String command = req.getParameter(COMMAND);
        String requestURL = req.getRequestURL().toString();

        boolean isDomainReq = DOMAIN.equals(requestURL);

        boolean isLogin = LOGIN_COMMAND.equals(command);
        boolean isLogout = LOGOUT_COMMAND.equals(command);
        boolean isDisabled = DISABLED_COMMAND.equals(command);
        if (login != null || isLogin || isLogout || isResourcesAccess(command, requestURL) || isDomainReq || isDisabled) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendError(UNAUTHORIZED);
        }
    }

    private boolean isResourcesAccess(String command, String requestURL) {
        boolean b = (requestURL.contains(RESOURCES)) && command == null;
        return b;
    }

    @Override
    public void destroy() {

    }
}
