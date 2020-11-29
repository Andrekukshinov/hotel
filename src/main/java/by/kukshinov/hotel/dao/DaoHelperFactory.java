package by.kukshinov.hotel.dao;

public class DaoHelperFactory {

    private static final String USER = "user";

    public  DaoHelper createHelper(String helperName) {
        switch (helperName) {
            case USER:
                return new UserDaoHelper();
            default:
                throw new IllegalArgumentException();
        }
    }
}
