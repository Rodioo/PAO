package pao.auxChapter;

import db.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        try{
            long idCourse = resultSet.getLong("idCourse");
            long idChapter = resultSet.getLong("idChapter");
            return new AuxChapter(idCourse, idChapter);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AuxChapter getById(long id) {
        return null;
    }

    public List<Long> getAllChaptersByCourseId(long id) {
        return auxChapters.stream()
                .filter(auxChapter -> auxChapter.getIdCourse() == id)
                .map(AuxChapter::getIdChapter)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuxChapter> getAll() {
        List<AuxChapter> auxChapterList = new ArrayList<>();
        try{
            String query = "SELECT * FROM proiectpao.aux_chapters;";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                AuxChapter auxChapter = rowToObject(resultSet);
                auxChapterList.add(auxChapter);
            }
        }catch (SQLException e) {
            System.out.println("Error occurred when getting the aux_chapters from the database.");
            System.out.println(e.getMessage());
        }
        return auxChapterList;
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
    public long update(AuxChapter auxChapter, HashMap<String, String> params) {
        return 0;
    }

    @Override
    public void delete(AuxChapter auxChapter) {

    }
}
