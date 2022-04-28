package pao.course;

import db.Dao;
import utils.Countable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        return null;
    }

    @Override
    public Course getById(long id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findAny().orElse(null);
    }

    //TODO: Query to get all courses using the aux_courses table, id_teacher and course table
    public List<Course> getCoursesByTeacherId(long id) {
        return null;
    }

    @Override
    public List<Course> getAll() {
        return new ArrayList<>();
    }

    @Override
    public long insert(Course course) {
        return 0;
    }

    @Override
    public long update(Course course, String[] params) {
        return 0;
    }

    @Override
    public void delete(Course course) {

    }


}
