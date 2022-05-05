package pao.auxCourse;

import db.Dao;
import pao.auxChapter.AuxChapter;
import pao.auxChapter.AuxChapterDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuxCourseDao implements Dao<AuxCourse> {

    private static List<AuxCourse> auxCourses = new ArrayList<>();
    private static AuxCourseDao singleton = null;

    private AuxCourseDao() {
        auxCourses = getAll();
    }

    public static AuxCourseDao getInstance() {
        if(singleton == null) {
            singleton = new AuxCourseDao();
        }
        return singleton;
    }

    @Override
    public AuxCourse rowToObject(ResultSet resultSet) {
        try{
            long idTeacher = resultSet.getLong("idTeacher");
            long idCourse = resultSet.getLong("idCourse");
            return new AuxCourse(idTeacher, idCourse);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AuxCourse getById(long id) {
        return null;
    }

    public long getTeacherIdByCourseId(long id) {
        return auxCourses.stream()
                .filter(auxCourse -> auxCourse.getIdCourse() == id)
                .map(AuxCourse::getIdTeacher)
                .findAny().orElse(-1L);
    }

    public List<Long> getAllCoursesByTeacherId(long id) {
        return auxCourses.stream()
                .filter(auxCourse -> auxCourse.getIdTeacher() == id)
                .map(AuxCourse::getIdCourse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuxCourse> getAll() {
        List<AuxCourse> auxCourseList = new ArrayList<>();
        try{
            String query = "SELECT * FROM proiectpao.aux_courses;";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                AuxCourse auxCourse = rowToObject(resultSet);
                auxCourseList.add(auxCourse);
            }
        }catch (SQLException e) {
            System.out.println("Error occurred when getting the aux_courses from the database.");
            System.out.println(e.getMessage());
        }
        return auxCourseList;
    }

    @Override
    public long insert(AuxCourse auxCourse) {
        if(auxCourse == null)
            return -1;
        auxCourses.add(auxCourse);
        try {
            String query = "INSERT INTO proiectpao.aux_courses(idTeacher, idCourse) " +
                    "VALUES (?, ?);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, auxCourse.getIdTeacher());
            preparedStatement.setLong(2, auxCourse.getIdCourse());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the courses in the database.");
            System.out.println(e.getMessage());
        }
        return auxCourse.getIdCourse();
    }

    @Override
    public long update(AuxCourse auxCourse, String[] params) {
        return 0;
    }

    @Override
    public void delete(AuxCourse auxCourse) {

    }
}
