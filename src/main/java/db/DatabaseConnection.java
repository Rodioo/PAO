package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection singleton = null;
    private static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/proiectpao";
    private static final String username = "root";
    private static final String password = "root";

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Couldn't establish database connection.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if(singleton == null)
            singleton = new DatabaseConnection();
        return connection;
    }
}
