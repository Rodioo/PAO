package pao.student;

import db.Dao;
import pao.courseInformation.CourseInformation;
import pao.courseInformation.CourseInformationDao;
import pao.user.User;
import pao.user.UserDao;
import utils.classes.UpdateManipulation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentDao implements Dao<Student> {

    private static List<Student> students = new ArrayList<>();
    private static StudentDao singleton = null;

    private StudentDao() {
        students = getAll();
    }

    public static StudentDao getInstance() {
        if(singleton == null)
            singleton = new StudentDao();
        return singleton;
    }

    @Override
    public Student rowToObject(ResultSet resultSet) {
        User user = UserDao.getInstance().rowToObject(resultSet);
        try{
            int points = resultSet.getInt("points");
            CourseInformation courseInformation = CourseInformationDao.getInstance()
                    .getCourseInformationByStudentId(user.getId());
            boolean isPremium = resultSet.getBoolean("isPremium");
            return isPremium ? new PremiumStudent(user, points, courseInformation)
                    : new Student(user, points, courseInformation);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Student getById(long id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findAny().orElse(null);
    }

    @Override
    public List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();
        try{
            String query = "SELECT u.*, s.points, s.isPremium " +
                    "FROM proiectpao.users u , proiectpao.students s " +
                    "WHERE u.idUser = s.idUser;";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Student student = rowToObject(resultSet);
                studentList.add(student);
            }
        }catch (SQLException e) {
            System.out.println("Error occurred when getting the students from database.");
            System.out.println(e.getMessage());
        }
        return studentList;
    }

    @Override
    public long insert(Student student) {
        if(student == null)
            return -1;
        if(UserDao.getInstance().insert(student) == -1)
            return -1;
        students.add(student);
        try {
            String query = "INSERT INTO proiectpao.students(idUser, points, isPremium) " +
                    "VALUES (?, 0, false);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, student.getId());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the student to database.");
            System.out.println(e.getMessage());
        }
        return student.getId();
    }

    @Override
    public long update(Student student, HashMap<String, String> params) {
        if(student == null || getById(student.getId()) == null || params == null || params.isEmpty())
            return -1;
        try{
            String query = "UPDATE proiectpao.students " +
                    "SET" + UpdateManipulation.getUpdateQuery(params) +
                    " WHERE idUser=" + student.getId() + ";";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when updating the student in the database.");
            System.out.println(e.getMessage());
        }
        return student.getId();
    }

    @Override
    public void delete(Student student) {

    }
}
