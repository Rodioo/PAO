package pao.teacher;

import db.Dao;
import pao.user.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao implements Dao<Teacher> {

    private static List<Teacher> teachers = new ArrayList<>();
    private static TeacherDao singleton = null;

    private TeacherDao() {

    }

    public static TeacherDao getInstance() {
        if(singleton == null)
            singleton = new TeacherDao();
        return singleton;
    }

    @Override
    public Teacher rowToObject(ResultSet resultSet) {
        return null;
    }

    @Override
    public Teacher getById(long id) {
        return teachers.stream()
                .filter(teacher -> teacher.getId() == id)
                .findAny().orElse(null);
    }

    @Override
    public List<Teacher> getAll() {
        return null;
    }

    @Override
    public long insert(Teacher teacher) {
        if(teacher == null)
            return -1;
        if(UserDao.getInstance().insert(teacher) == -1)
            return -1;
        teachers.add(teacher);
        try {
            String query = "INSERT INTO proiectpao.teachers(idUser, coursesIds) " +
                    "VALUES (?, null)";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, teacher.getId());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return teacher.getId();
    }

    @Override
    public long update(Teacher teacher, String[] params) {
        return 0;
    }

    @Override
    public void delete(Teacher teacher) {

    }
}
