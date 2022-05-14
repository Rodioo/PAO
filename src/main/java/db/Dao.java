package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;


public interface Dao<T> {

    Connection connection = DatabaseConnection.getConnection();

    T rowToObject(ResultSet resultSet);

    T getById(long id);

    List<T> getAll();

    long insert(T t);

    long update(T t, HashMap<String, String> params);

    void delete(T t);
}
