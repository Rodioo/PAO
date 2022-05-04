package pao.auxChapter;

import db.Dao;

import java.sql.ResultSet;
import java.util.List;

public class AuxChapterDao implements Dao<AuxChapter> {


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
        return null;
    }

    @Override
    public long insert(AuxChapter auxChapter) {
        return 0;
    }

    @Override
    public long update(AuxChapter auxChapter, String[] params) {
        return 0;
    }

    @Override
    public void delete(AuxChapter auxChapter) {

    }
}
