package pao.user;

import db.Dao;
import org.jetbrains.annotations.NotNull;
import utils.interfaces.Countable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserDao implements Countable, Dao<User> {

    private static List<User> users = new ArrayList<>();
    private static UserDao singleton = null;

    private UserDao() {
        users = getAll();
    }

    public static UserDao getInstance() {
        if(singleton == null)
            singleton = new UserDao();
        return singleton;
    }

    @Override
    public long getNextId() {
        return (users == null || users.isEmpty()) ? 1 : users.get(users.size() - 1).getId() + 1;
    }

    @Override
    public User rowToObject(@NotNull ResultSet resultSet) {
        try{
            long id = resultSet.getLong("idUser");
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            return new User(id, username, email, password);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findAny().orElse(null);
    }

    public boolean isUsernameTaken(String username) {
        return users.stream().
                filter(user -> user.getUsername().equals(username)).
                findAny().orElse(null) != null;
    }

    public boolean isEmailTaken(String email) {
        return users.stream().
                filter(user -> user.getEmail().equals(email)).
                findAny().orElse(null) != null;
    }

    public User getByUsernameAndPassword(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findAny().orElse(null);
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try{
            String query = "SELECT * FROM proiectpao.users;";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                User user = rowToObject(resultSet);
                userList.add(user);
            }
        }catch (SQLException e) {
            System.out.println("Error occurred when getting the users from database.");
            System.out.println(e.getMessage());
        }
        return userList;
    }

    @Override
    public long insert(User user) {
        if(user == null)
            return -1;
        users.add(user);
        try {
            String query = "INSERT INTO proiectpao.users(idUser, username, email, password) " +
                    "VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the user to database.");
            System.out.println(e.getMessage());
        }
        return user.getId();
    }

    @Override
    public long update(User user, HashMap<String, String> params) {
        return 0;
    }

    @Override
    public void delete(User user) {

    }
}
