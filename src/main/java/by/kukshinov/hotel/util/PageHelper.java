package by.kukshinov.hotel.util;

public interface PageHelper {
    // TODO: 27.12.2020  
    Integer getValidPage(String page, int allItems, int itemsPerPage);

    Integer getLastPage(int allItems, int itemsPerPage);
}
