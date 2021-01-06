package by.kukshinov.hotel.validators;

public class PageValidatorImpl implements PageValidator {
    private static final int FIRST_PAGE = 1;

    @Override
    public int gatValidPage(String page, int allItems, int itemsPerPage) {
        int pageInt;
        int maxFullPages = allItems / itemsPerPage;
        int lastPageItems = allItems % itemsPerPage;
        if (page == null) {
            return FIRST_PAGE;
        } else {
            int longPage = Integer.parseInt(page);
            pageInt = Math.max(longPage, 1);
            if ((pageInt - 1 == maxFullPages || pageInt - 1 > maxFullPages) && lastPageItems == 0) {
                pageInt = maxFullPages;
            } else if (pageInt - 1 > maxFullPages) {
                pageInt = maxFullPages + 1;
            }
        }
        return pageInt;
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
