package by.kukshinov.hotel.validators;

public interface PageValidator {
    // TODO: 27.12.2020  
    int getValidPage(String page, int allItems, int itemsPerPage);

    int getLastPage(int allItems, int itemsPerPage);
}
