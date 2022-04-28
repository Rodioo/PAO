package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;


public interface Dao<T> {

    Connection connection = DatabaseConnection.getConnection();

    T rowToObject(ResultSet resultSet);

    T getById(long id);

    List<T> getAll();

    long insert(T t);

    long update(T t, String[] params);

    void delete(T t);
}
