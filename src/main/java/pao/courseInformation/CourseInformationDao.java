package pao.courseInformation;

import db.Dao;
import pao.course.Course;
import pao.course.CourseDao;
import pao.student.Student;
import pao.student.StudentDao;
import utils.interfaces.Countable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseInformationDao implements Countable, Dao<CourseInformation> {

    private static List<CourseInformation> coursesInformations = new ArrayList<>();
    private static CourseInformationDao singleton = null;

    private CourseInformationDao() {
        coursesInformations = getAll();
    }

    public static CourseInformationDao getInstance() {
        if(singleton == null)
            singleton = new CourseInformationDao();
        return singleton;
    }

    @Override
    public long getNextId() {
        return (coursesInformations == null) ? 1 : coursesInformations.size() + 1;
    }

    @Override
    public CourseInformation rowToObject(ResultSet resultSet) {
        try{
            long id = resultSet.getLong("id");
            long idStudent = resultSet.getLong("idStudent");
            Student student = StudentDao.getInstance().getById(idStudent);
            long idCourse = resultSet.getLong("idCourse");
            Course course = CourseDao.getInstance().getById(idCourse);
            int numberChapter = resultSet.getInt("numberChapter");
            return new CourseInformation(id, student, course, numberChapter);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public CourseInformation getById(long id) {
        return coursesInformations.stream()
                .filter(courseInformation -> courseInformation.getId() == id)
                .findAny().orElse(null);
    }

    public CourseInformation getByStudentAndCourse(Student student, Course course) {
        return coursesInformations.stream()
                .filter(courseInformation -> courseInformation.getStudent().equals(student)
                        && courseInformation.getCurrentCourse().equals(course))
                .findAny().orElse(null);
    }

    @Override
    public List<CourseInformation> getAll() {
        List<CourseInformation> courseInformationList = new ArrayList<>();
        try{
            String query = "SELECT * FROM proiectpao.courses_informations;";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                CourseInformation courseInformation = rowToObject(resultSet);
                courseInformationList.add(courseInformation);
            }
        }catch (SQLException e) {
            System.out.println("Error occurred when getting the courses informations from the database.");
            System.out.println(e.getMessage());
        }
        return courseInformationList;
    }

    @Override
    public long insert(CourseInformation courseInformation) {
        if(courseInformation == null)
            return -1;
        if(getByStudentAndCourse(courseInformation.getStudent(), courseInformation.getCurrentCourse()) != null) {
            System.out.println("Course information already in database with id " + courseInformation.getId());
            return courseInformation.getId();
        }
        coursesInformations.add(courseInformation);
        try {
            String query = "INSERT INTO proiectpao.courses_informations(id, idStudent, idCourse) " +
                    "VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, courseInformation.getId());
            preparedStatement.setLong(2, courseInformation.getStudent().getId());
            preparedStatement.setLong(3, courseInformation.getCurrentCourse().getId());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the courseInformation in the database.");
            System.out.println(e.getMessage());
        }
        return courseInformation.getId();
    }

    @Override
    public long update(CourseInformation courseInformation, String[] params) {
        return 0;
    }

    @Override
    public void delete(CourseInformation courseInformation) {

    }
}
