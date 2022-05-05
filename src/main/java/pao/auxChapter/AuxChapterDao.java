package pao.auxChapter;

import db.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuxChapterDao implements Dao<AuxChapter> {

    private static List<AuxChapter> auxChapters = new ArrayList<>();
    private static AuxChapterDao singleton = null;

    private AuxChapterDao() {
        auxChapters = getAll();
    }

    public static AuxChapterDao getInstance() {
        if(singleton == null) {
            singleton = new AuxChapterDao();
        }
        return singleton;
    }

    @Override
    public AuxChapter rowToObject(ResultSet resultSet) {
        return null;
    }

    @Override
    public AuxChapter getById(long id) {
        return null;
    }

    @Override
    public List<AuxChapter> getAll() {
        return new ArrayList<>();
    }

    @Override
    public long insert(AuxChapter auxChapter) {
        if(auxChapter == null)
            return -1;
        auxChapters.add(auxChapter);
        try {
            String query = "INSERT INTO proiectpao.aux_chapters(idCourse, idChapter) " +
                    "VALUES (?, ?);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, auxChapter.getIdCourse());
            preparedStatement.setLong(2, auxChapter.getIdChapter());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the chapters in the database.");
            System.out.println(e.getMessage());
        }
        return auxChapter.getIdChapter();
    }

    @Override
    public long update(AuxChapter auxChapter, String[] params) {
        return 0;
    }

    @Override
    public void delete(AuxChapter auxChapter) {

    }
}
