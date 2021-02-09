package by.kukshinov.hotel.util;


import by.kukshinov.hotel.model.Application;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestCreatorTest {
    private static final String SAVE_APPLICATION = "INSERT INTO Application (id, capacity, arrival_date) VALUES(?, ?, ?)";
    private static final String UPDATE_APPLICATION = "UPDATE Application SET capacity=?, arrival_date=? WHERE id=?";
    private static final String ID = "id";
    private static final String CAPACITY = "capacity";
    private static final String ARRIVAL_DATE = "arrival_date";
    private static final int ONE = 1;
    private static final LocalDate NOW = LocalDate.now();
    private static final String APPLICATION = "Application";

    @Test
    public void testBuildQueryShouldBuildSaveQuery() {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(ID, ONE);
        fields.put(CAPACITY, ONE);
        fields.put(ARRIVAL_DATE, NOW);
        RequestCreator requestCreator = new RequestCreator();
        Application app = new Application();
        app.setId(null);

        String query = requestCreator.getSaveQuery("Application", fields);

        Assert.assertEquals(query, SAVE_APPLICATION);

    }

    @Test
    public void testBuildQueryShouldBuildUpdateQuery() {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(CAPACITY, ONE);
        fields.put(ARRIVAL_DATE, NOW);
        fields.put(ID, 17);
        RequestCreator requestCreator = new RequestCreator();
        Application app = new Application();
        app.setId(17L);

        String query = requestCreator.getUpdateQuery(APPLICATION, fields);

        Assert.assertEquals(query, UPDATE_APPLICATION);

    }

}
