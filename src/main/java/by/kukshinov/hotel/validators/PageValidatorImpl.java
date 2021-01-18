package by.kukshinov.hotel.validators;

public class PageValidatorImpl implements PageValidator {
    private static final int FIRST_PAGE = 1;

    @Override
    public int getValidPage(String stringPage, int allItems, int itemsPerPage) {
        int currentPage;
        if (stringPage == null) {
            return FIRST_PAGE;
        } else {
            int parsedPage = Integer.parseInt(stringPage);
            currentPage = Math.max(parsedPage, 1);
            int lastPage = getLastPage(allItems, itemsPerPage);
            return Math.min(currentPage, lastPage);
        }
    }

    @Override
    public int getLastPage(int allItems, int itemsPerPage) {
        int lastPageItems = allItems % itemsPerPage;
        int fullPagesAmount = allItems / itemsPerPage;
        if (lastPageItems == 0) {
            return fullPagesAmount;
        } else {
            return fullPagesAmount + 1;
        }
    }
}
