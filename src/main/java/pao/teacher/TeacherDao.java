package pao.teacher;

import db.Dao;
import pao.auxChapter.AuxChapterDao;
import pao.auxCourse.AuxCourseDao;
import pao.chapter.Chapter;
import pao.chapter.ChapterDao;
import pao.course.Course;
import pao.course.CourseDao;
import pao.user.User;
import pao.user.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherDao implements Dao<Teacher> {

    private static List<Teacher> teachers = new ArrayList<>();
    private static TeacherDao singleton = null;

    private TeacherDao() {
        teachers = getAll();
    }

    public static TeacherDao getInstance() {
        if(singleton == null)
            singleton = new TeacherDao();
        return singleton;
    }

    @Override
    public Teacher rowToObject(ResultSet resultSet) {
        User user = UserDao.getInstance().rowToObject(resultSet);
        List<Long> courseIds = AuxCourseDao.getInstance().getAllCoursesByTeacherId(user.getId());
        List<Course> courses = CourseDao.getInstance().getCoursesByTeacherIds(courseIds);
        return new Teacher(user, courses);
    }

    @Override
    public Teacher getById(long id) {
        return teachers.stream()
                .filter(teacher -> teacher.getId() == id)
                .findAny().orElse(null);
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teacherList = new ArrayList<>();
        try{
            String query = "SELECT u.* " +
                    "FROM proiectpao.users u , proiectpao.teachers t " +
                    "WHERE u.idUser = t.idUser;";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Teacher teacher = rowToObject(resultSet);
                teacherList.add(teacher);
            }
        }catch (SQLException e) {
            System.out.println("Error occurred when getting the teachers from database.");
            System.out.println(e.getMessage());
        }
        return teacherList;
    }

    @Override
    public long insert(Teacher teacher) {
        if(teacher == null)
            return -1;
        if(UserDao.getInstance().insert(teacher) == -1)
            return -1;
        teachers.add(teacher);
        try {
            String query = "INSERT INTO proiectpao.teachers(idUser) " +
                    "VALUES (?)";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, teacher.getId());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return teacher.getId();
    }

    @Override
    public long update(Teacher teacher, HashMap<String, String> params) {
        return 0;
    }

    @Override
    public void delete(Teacher teacher) {

    }
}
