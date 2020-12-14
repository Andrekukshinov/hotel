package by.kukshinov.hotel.context;

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


    public String getRequestParameter(String key) {
        return requestParameters.get(key);
    }

    public Object getRequestAttribute(String key) {
        return requestAttributes.get(key);
    }

    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    public void removeSessionAttribute(String key) {
        sessionAttributes.remove(key);
    }

    public void setRequestParameter(String key, String value) {
        requestParameters.put(key, value);
    }

    public void setRequestAttribute(String key, Object value) {
        requestAttributes.put(key, value);
    }

    public void setSessionAttribute(String key, Object value) {
        sessionAttributes.put(key, value);
    }



    Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }
}
