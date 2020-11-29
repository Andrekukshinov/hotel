package by.kukshinov.hotel.dao;

public interface DaoHelper<R, T extends Dao<R>> extends AutoCloseable {
     T createDao();
}
