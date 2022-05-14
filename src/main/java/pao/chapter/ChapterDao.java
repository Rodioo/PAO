package pao.chapter;

import db.Dao;

import pao.auxQuestion.AuxQuestionDao;
import pao.question.Question;
import pao.question.QuestionDao;
import utils.interfaces.Countable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        try{
            long id = resultSet.getLong("idChapter");
            String title = resultSet.getString("title");
            String text = resultSet.getString("text");
            List<Long> questionsIds = AuxQuestionDao.getInstance().getAllQuestionsIdByChapterId(id);
            List<Question> questions = QuestionDao.getInstance().getQuestionsByIds(questionsIds);
            return new Chapter(id, title, text, questions);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Chapter getById(long id) {
        return chapters.stream()
                .filter(chapter -> chapter.getId() == id)
                .findAny().orElse(null);
    }

    public List<Chapter> getChaptersByIds(List<Long> auxChapters) {
        return chapters.stream()
                .filter(chapter -> auxChapters.contains(chapter.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Chapter> getAll() {
        List<Chapter> chapterList = new ArrayList<>();
        try{
            String query = "SELECT * FROM proiectpao.chapters;";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Chapter chapter = rowToObject(resultSet);
                chapterList.add(chapter);
            }
        }catch (SQLException e) {
            System.out.println("Error occurred when getting the chapters from the database.");
            System.out.println(e.getMessage());
        }
        return chapterList;
    }

    @Override
    public long insert(Chapter chapter) {
        if(chapter == null)
            return -1;
        chapters.add(chapter);
        try {
            String query = "INSERT INTO proiectpao.chapters(idChapter, title, text) " +
                    "VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, chapter.getId());
            preparedStatement.setString(2, chapter.getTitle());
            preparedStatement.setString(3, chapter.getText());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the chapter in the database.");
            System.out.println(e.getMessage());
        }
        return chapter.getId();
    }

    @Override
    public long update(Chapter chapter, HashMap<String, String> params) {
        return 0;
    }

    @Override
    public void delete(Chapter chapter) {

    }
}
