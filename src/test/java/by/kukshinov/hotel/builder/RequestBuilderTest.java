package by.kukshinov.hotel.builder;


import by.kukshinov.hotel.model.Application;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestBuilderTest {
    private static final String SAVE_APPLICATION = "INSERT INTO Application (id, capacity, arrival_date) VALUES(?, ?, ?)";
    private static final String UPDATE_APPLICATION = "UPDATE Application SET capacity=?, arrival_date=? WHERE id=?";

    @Test
    public void testBuildQueryShouldBuildSaveQuery() {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("id", 1);
        fields.put("capacity", 1);
        fields.put("arrival_date", LocalDate.now());
        RequestBuilder<Application> requestBuilder = new RequestBuilder<>();
        Application app = new Application();
        app.setId(null);

        String query = requestBuilder.buildQuery(app, "Application", fields);

        Assert.assertEquals(query, SAVE_APPLICATION);

    }

    @Test
    public void testBuildQueryShouldBuildUpdateQuery() {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("capacity", 1);
        fields.put("arrival_date", LocalDate.now());
        fields.put("id", 17);
        RequestBuilder<Application> requestBuilder = new RequestBuilder<>();
        Application app = new Application();
        app.setId(17l);

        String query = requestBuilder.buildQuery(app, "Application", fields);

        Assert.assertEquals(query, UPDATE_APPLICATION);

    }

}
