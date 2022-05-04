package pao.chapter;

import db.Dao;

import utils.Countable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChapterDao implements Countable, Dao<Chapter> {

    private static List<Chapter> chapters = new ArrayList<>();
    private static ChapterDao singleton = null;

    private ChapterDao() {
        chapters = getAll();
    }

    public static ChapterDao getInstance() {
        if(singleton == null) {
            singleton = new ChapterDao();
        }
        return singleton;
    }

    @Override
    public long getNextId() {
        return (chapters == null) ? 1 : chapters.size() + 1;
    }

    @Override
    public Chapter rowToObject(ResultSet resultSet) {
        return null;
    }

    @Override
    public Chapter getById(long id) {
        return null;
    }

    @Override
    public List<Chapter> getAll() {
        return new ArrayList<>();
    }

    @Override
    public long insert(Chapter chapter) {
        if(chapter == null)
            return -1;
        chapters.add(chapter);
        try {
            String query = "INSERT INTO proiectpao.chapters(idChapter, title, text) " +
                    "VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, chapter.getId());
            preparedStatement.setString(2, chapter.getTitle());
            preparedStatement.setString(3, chapter.getText());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the chapter in the database.");
            System.out.println(e.getMessage());
        }
        return chapter.getId();
    }

    @Override
    public long update(Chapter chapter, String[] params) {
        return 0;
    }

    @Override
    public void delete(Chapter chapter) {

    }
}
