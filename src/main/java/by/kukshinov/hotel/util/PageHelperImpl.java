package by.kukshinov.hotel.util;

public class PageHelperImpl implements PageHelper {
    private static final int FIRST_PAGE = 1;

    @Override
    public Integer getValidPage(String stringPage, int allItems, int itemsPerPage) {
        if (stringPage == null) {
            return FIRST_PAGE;
        } else {
            int parsedPage = Integer.parseInt(stringPage);
            int currentPage = Math.max(parsedPage, 1);
            int lastPage = getLastPage(allItems, itemsPerPage);
            return Math.min(currentPage, lastPage);
        }
    }

    @Override
    public Integer getLastPage(int allItems, int itemsPerPage) {
        int lastPageItems = allItems % itemsPerPage;
        int fullPagesAmount = allItems / itemsPerPage;
        if (lastPageItems == 0) {
            return fullPagesAmount;
        } else {
            return fullPagesAmount + 1;
        }
    }
}
