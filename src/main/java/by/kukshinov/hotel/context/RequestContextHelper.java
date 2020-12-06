package by.kukshinov.hotel.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

// TODO: 04.12.2020 rework with while
public class RequestContextHelper {

    public RequestContext create(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Enumeration<String> parameterNames = req.getParameterNames();
        Enumeration<String> attributeNames = req.getAttributeNames();
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();
        Map<String, String> parametersMap = createMap(parameterNames, req::getParameter);
        Map<String, Object> attributesMap = createMap(attributeNames, req::getAttribute);
        Map<String, Object> sessionAttributesMap = createMap(sessionAttributeNames, session::getAttribute);
        return new RequestContext(parametersMap, attributesMap, sessionAttributesMap);
    }

    public void updateRequest(HttpServletRequest req, RequestContext requestContext) {
        Map<String, Object> requestAttributes = requestContext.getRequestAttributes();
        Map<String, Object> sessionAttributes = requestContext.getSessionAttributes();
        HttpSession session = req.getSession();
        fillAttributes(requestAttributes, req::setAttribute);
        fillAttributes(sessionAttributes, session::setAttribute);
    }

    private void fillAttributes(Map<String, Object> requestAttributes, BiConsumer<String, Object> consumer) {
        Set<String> keys = requestAttributes.keySet();
        keys.forEach(key -> {
            Object attribute = requestAttributes.get(key);
            consumer.accept(key, attribute);
        });
    }

    private <R> Map<String, R> createMap(Enumeration<String> keyNames, Function<String, R> apply) {
        return Collections.list(keyNames).stream().collect(Collectors.toMap(
                (key) -> key,
                apply
        ));
    }
}
