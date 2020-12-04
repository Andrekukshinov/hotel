package by.kukshinov.hotel.request.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// TODO: 04.12.2020 refactor
public class RequestContextManager {
    public RequestContext create(HttpServletRequest req) {
        Enumeration parameterNames = req.getParameterNames();
        Enumeration attributeNames = req.getAttributeNames();
        HttpSession session = req.getSession();
        Enumeration sessionAttributeNames = session.getAttributeNames();

        Map<String, String> parametersMap = new HashMap<>();
        Map<String, Object> attributesMap = new HashMap<>();
        Map<String, Object> sessionAttributesMap = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            Object name = parameterNames.nextElement();
            String parameter = req.getParameter((String) name);
            parametersMap.put((String) name, parameter);
        }
        while (attributeNames.hasMoreElements()) {
            Object name = attributeNames.nextElement();
            Object parameter = req.getAttribute((String) name);
            attributesMap.put((String) name, parameter);
        }
        while (sessionAttributeNames.hasMoreElements()) {
            Object name = sessionAttributeNames.nextElement();
            Object parameter = session.getAttribute((String) name);
            sessionAttributesMap.put((String) name, parameter);
        }
        RequestContext requestContext = new RequestContext(parametersMap, attributesMap, sessionAttributesMap);
        System.out.println(requestContext);
        return requestContext;
    }

    public void updateRequest(HttpServletRequest req, RequestContext requestContext) {
        Map<String, Object> requestAttributes = requestContext.getRequestAttributes();
        Set<String> keys = requestAttributes.keySet();
        keys.forEach(key-> {
            Object attribute = requestAttributes.get(key);
            req.setAttribute(key, attribute);
        });
        Map<String, Object> sessionAttributes = requestContext.getSessionAttributes();
        Set<String> keysAttributes = sessionAttributes.keySet();
        keysAttributes.forEach(key-> {
            Object attribute = sessionAttributes.get(key);
            req.getSession().setAttribute(key, attribute);
        });

    }
}
