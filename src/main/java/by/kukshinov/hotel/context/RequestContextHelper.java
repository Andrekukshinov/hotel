package by.kukshinov.hotel.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RequestContextHelper {
    private static final String LOGIN = "login";
    private static final String USER_ID = "user_id";
    private static final String ROLE = "role";

    public RequestContext create(HttpServletRequest req) {
        HttpSession session = req.getSession();

        Enumeration<String> parameterNames = req.getParameterNames();
        Enumeration<String> attributeNames = req.getAttributeNames();
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();
        Map<String, String> parametersMap = Collections.list(parameterNames).stream().collect(Collectors.toMap(
                (key) -> key,
                req::getParameter
        ));
        Map<String, Object> attributesMap = Collections.list(attributeNames).stream().collect(Collectors.toMap(
                (key) -> key,
                req::getAttribute
        ));
        Map<String, Object> sessionAttributesMap =  Collections.list(sessionAttributeNames).stream().collect(Collectors.toMap(
                (key) -> key,
                session::getAttribute
        ));
        return new RequestContext(parametersMap, attributesMap, sessionAttributesMap);
    }

    public void updateRequest(HttpServletRequest req, RequestContext requestContext) {
        Map<String, Object> requestAttributes = requestContext.getRequestAttributes();
        Map<String, Object> sessionAttributes = requestContext.getSessionAttributes();
        Set<String> requestAttrsKeys = requestAttributes.keySet();
        Set<String> sessionAttrsKeys = sessionAttributes.keySet();
        HttpSession session = req.getSession();
        requestAttrsKeys.forEach(key -> {
            Object attribute = requestAttributes.get(key);
            req.setAttribute(key, attribute);
        });
        fillingSession(sessionAttributes, sessionAttrsKeys, session);
    }

    private void fillingSession(Map<String, Object> sessionAttributes, Set<String> sessionAttrsKeys, HttpSession session) {
        if(isUserAuthorized(sessionAttributes)) {
            sessionAttrsKeys.forEach(key -> {
                Object attribute = sessionAttributes.get(key);
                session.setAttribute(key, attribute);
            });
        } else {
            session.invalidate();
        }
    }
    private boolean isUserAuthorized(Map<String, Object> sessionAttributes) {
        return sessionAttributes.get(LOGIN) != null && sessionAttributes.get(ROLE) != null &&
                sessionAttributes.get(USER_ID) != null;
    }
}
