package by.kukshinov.hotel.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PageHelperImplTest {

    private static final int ITEMS_PER_PAGE = 7;
    private static final int CEIL_TOTAL_ITEMS_AMOUNT = 70;
    private static final int NOT_CEIL_TOTAL_ITEMS_AMOUNT = 72;
    private static final int FIFTH = 5;

    private static final String FIVE_STRING = "5";
    private static final String ONE_STRING = "1";
    private static final String ZERO_STRING = "0";
    private static final String NEGATIVE_STRING = "-11";
    private static final int FIRST = 1;

    private static final String TEN_STRING = "10";
    private static final int TENTH = 10;

    private static final String NINETY_FIVE_STRING = "95";
    private static final String ELEVEN_STRING = "11";
    private static final int ELEVENTH = 11;

    @DataProvider
    public Object[][] lastPageDataProvider() {
        return new Object[][]{
                {CEIL_TOTAL_ITEMS_AMOUNT, ITEMS_PER_PAGE, TENTH},
                {NOT_CEIL_TOTAL_ITEMS_AMOUNT, ITEMS_PER_PAGE, ELEVENTH},
                {FIRST, ITEMS_PER_PAGE, FIRST}
        };
    }

    @DataProvider
    public Object[][] currentPageDataProvider() {
        return new Object[][]{
                {ONE_STRING, ITEMS_PER_PAGE, CEIL_TOTAL_ITEMS_AMOUNT, FIRST},
                {NEGATIVE_STRING, ITEMS_PER_PAGE, CEIL_TOTAL_ITEMS_AMOUNT, FIRST},
                {ZERO_STRING, ITEMS_PER_PAGE, CEIL_TOTAL_ITEMS_AMOUNT, FIRST},

                {FIVE_STRING, ITEMS_PER_PAGE, CEIL_TOTAL_ITEMS_AMOUNT, FIFTH},

                {TEN_STRING, ITEMS_PER_PAGE, CEIL_TOTAL_ITEMS_AMOUNT, TENTH},
                {NINETY_FIVE_STRING, ITEMS_PER_PAGE, CEIL_TOTAL_ITEMS_AMOUNT, TENTH},

                {NINETY_FIVE_STRING, ITEMS_PER_PAGE, NOT_CEIL_TOTAL_ITEMS_AMOUNT, ELEVENTH},
                {ELEVEN_STRING, ITEMS_PER_PAGE, NOT_CEIL_TOTAL_ITEMS_AMOUNT, ELEVENTH}
        };

    }

    @Test(dataProvider = "currentPageDataProvider")
    public void tesGetValidPageShouldReturnFirstPage(String pageString, int itemsPerPage, int totalItemsAmount, int expected) {
        PageHelper pageValidator = new PageHelper();

        int page = pageValidator.getValidPage(pageString, totalItemsAmount, itemsPerPage);

        Assert.assertEquals(page, expected);
    }

    @Test(dataProvider = "lastPageDataProvider")
    public void testGetLastPageShouldReturnLastPage(int totalItemsAmount, int itemsPerPage, int expected) {
        PageHelper pageValidator = new PageHelper();

        int page = pageValidator.getLastPage(totalItemsAmount, itemsPerPage);

        Assert.assertEquals(page, expected);
    }

}
