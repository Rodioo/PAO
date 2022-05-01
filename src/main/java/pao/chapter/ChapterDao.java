package pao.chapter;

import db.Dao;
import pao.question.Question;
import pao.question.QuestionDao;
import utils.Countable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChapterDao implements Countable, Dao<Chapter> {

    private static List<Chapter> chapters = new ArrayList<>();
    private static ChapterDao singleton = null;

    private ChapterDao() {
        chapters = getAll();
    }

    public static ChapterDao getInstance() {
        if(singleton == null) {
            singleton = new ChapterDao();
        }
        return singleton;
    }

    @Override
    public long getNextId() {
        return (chapters == null) ? 1 : chapters.size() + 1;
    }

    @Override
    public Chapter rowToObject(ResultSet resultSet) {
        return null;
    }

    @Override
    public Chapter getById(long id) {
        return null;
    }

    @Override
    public List<Chapter> getAll() {
        return null;
    }

    @Override
    public long insert(Chapter chapter) {
        return 0;
    }

    @Override
    public long update(Chapter chapter, String[] params) {
        return 0;
    }

    @Override
    public void delete(Chapter chapter) {

    }
}
