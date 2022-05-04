package pao.auxCourse;

import db.Dao;

import java.sql.ResultSet;
import java.util.List;

public class AuxCourseDao implements Dao<AuxCourse> {


    @Override
    public AuxCourse rowToObject(ResultSet resultSet) {
        return null;
    }

    @Override
    public AuxCourse getById(long id) {
        return null;
    }

    @Override
    public List<AuxCourse> getAll() {
        return null;
    }

    @Override
    public long insert(AuxCourse auxCourse) {
        return 0;
    }

    @Override
    public long update(AuxCourse auxCourse, String[] params) {
        return 0;
    }

    @Override
    public void delete(AuxCourse auxCourse) {

    }
}
