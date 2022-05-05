package pao.course;

import db.Dao;
import pao.auxChapter.AuxChapterDao;
import pao.chapter.Chapter;
import pao.chapter.ChapterDao;
import utils.enums.AccessType;
import utils.interfaces.Countable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDao implements Countable, Dao<Course> {

    private static List<Course> courses = new ArrayList<>();
    private static CourseDao singleton = null;

    private CourseDao() {
        courses = getAll();
    }

    public static CourseDao getInstance() {
        if(singleton == null)
            singleton = new CourseDao();
        return singleton;
    }

    @Override
    public long getNextId() {
        return (courses == null) ? 1 : courses.size() + 1;
    }

    @Override
    public Course rowToObject(ResultSet resultSet) {
        try{
            long id = resultSet.getLong("idCourse");
            String name = resultSet.getString("name");
            String imageUrl = resultSet.getString("imageUrl");
            String description = resultSet.getString("description");
            String accessTemp = resultSet.getString("access");
            AccessType accessType = switch (accessTemp.toUpperCase()) {
                case "FREE" -> AccessType.FREE;
                case "PREMIUM" -> AccessType.PREMIUM;
                default -> AccessType.PRIVATE;
            };
            List<Long> chapterIds = AuxChapterDao.getInstance().getAllChaptersByCourseId(id);
            List<Chapter> chapters = ChapterDao.getInstance().getChaptersByIds(chapterIds);
            return new Course(id, name, description, imageUrl, accessType, chapters);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Course getById(long id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findAny().orElse(null);
    }

    //TODO: Query to get all courses using the aux_courses table, id_teacher and course table
    public List<Course> getCoursesByTeacherIds(List<Long> auxCourses) {
        return courses.stream()
                .filter(course -> auxCourses.contains(course.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> getAll() {
        List<Course> courseList = new ArrayList<>();
        try{
            String query = "SELECT * FROM proiectpao.courses;";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Course course = rowToObject(resultSet);
                courseList.add(course);
            }
        }catch (SQLException e) {
            System.out.println("Error occurred when getting the courses from the database.");
            System.out.println(e.getMessage());
        }
        return courseList;
    }

    @Override
    public long insert(Course course) {
        if(course == null)
            return -1;
        courses.add(course);
        try {
            String query = "INSERT INTO proiectpao.courses(idCourse, name, imageUrl, description, access) " +
                    "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, course.getId());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setString(3, course.getImageUrl());
            preparedStatement.setString(4, course.getDescription());
            preparedStatement.setString(5, course.getAccess().toString());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the course in the database.");
            System.out.println(e.getMessage());
        }
        return course.getId();
    }

    @Override
    public long update(Course course, String[] params) {
        return 0;
    }

    @Override
    public void delete(Course course) {

    }


}
