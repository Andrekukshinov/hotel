package by.kukshinov.hotel.util;

public interface PageHelper {
    Integer getValidPage(String page, int allItems, int itemsPerPage);

    Integer getLastPage(int allItems, int itemsPerPage);
}
