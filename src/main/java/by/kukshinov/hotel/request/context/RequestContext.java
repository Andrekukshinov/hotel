package by.kukshinov.hotel.request.context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RequestContext {
    private final Map<String, String> requestParameters;
    private final Map<String, Object> requestAttributes;
    private final Map<String, Object> sessionAttributes;

    public RequestContext(Map<String, String> requestParameters, Map<String, Object> requestAttributes, Map<String, Object> sessionAttributes) {
        this.requestParameters = requestParameters;
        this.requestAttributes = requestAttributes;
        this.sessionAttributes = sessionAttributes;
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }
}

