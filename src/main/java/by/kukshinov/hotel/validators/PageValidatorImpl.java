package by.kukshinov.hotel.validators;

public class PageValidatorImpl implements PageValidator{
    private static final int FIRST_PAGE = 1;


    public int gatValidPage(String page) {
        int pageInt;
        if (page == null ) {
            pageInt = FIRST_PAGE;
        } else {
            long longPage = Long.parseLong(page);
            int currentPage = longPage > Integer.MAX_VALUE? 1: (int) longPage;
            pageInt = Math.max(currentPage, 1);
        }
        return pageInt;
    }
}
