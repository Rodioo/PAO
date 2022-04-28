package pao.questionInfo;

import db.Dao;
import pao.user.User;
import utils.Countable;
import utils.QuestionDifficulty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionInfoDao implements Countable, Dao<QuestionInfo> {

    private static List<QuestionInfo> questionsInfo = new ArrayList<>();
    private static QuestionInfoDao singleton = null;

    private QuestionInfoDao() {
        questionsInfo = getAll();
    }

    public static QuestionInfoDao getInstance() {
        if(singleton == null) {
            singleton = new QuestionInfoDao();
        }
        return singleton;
    }

    @Override
    public long getNextId() {
        return (questionsInfo == null) ? 1 : questionsInfo.size() + 1;
    }

    @Override
    public QuestionInfo rowToObject(ResultSet resultSet) {
        try{
            long id = resultSet.getLong("idQuestion");
            String text = resultSet.getString("text");
            String difficultyTemp = resultSet.getString("difficulty");
            QuestionDifficulty difficulty = switch (difficultyTemp.toUpperCase()) {
                case "EASY" -> QuestionDifficulty.EASY;
                case "MEDIUM" -> QuestionDifficulty.MEDIUM;
                case "HARD" -> QuestionDifficulty.HARD;
                default -> QuestionDifficulty.UNSPECIFIED;
            };
            return new QuestionInfo(id, text, difficulty);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public QuestionInfo getById(long id) {
        return questionsInfo.stream()
                .filter(questionInfo -> questionInfo.getId() == id)
                .findAny().orElse(null);
    }

    @Override
    public List<QuestionInfo> getAll() {
        List<QuestionInfo> questionInfoList = new ArrayList<>();
        try{
            String query = "SELECT * FROM proiectpao.questionsinfo;";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                QuestionInfo questionInfo = rowToObject(resultSet);
                questionInfoList.add(questionInfo);
            }
        }catch (SQLException e) {
            System.out.println("Error occurred when getting the questions information from database.");
            System.out.println(e.getMessage());
        }
        return questionInfoList;
    }

    @Override
    public long insert(QuestionInfo questionInfo) {
        if(questionInfo == null)
            return -1;
        questionsInfo.add(questionInfo);
        try {
            String query = "INSERT INTO proiectpao.questionsinfo(idQuestion, text, difficulty) " +
                    "VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, questionInfo.getId());
            preparedStatement.setString(2, questionInfo.getText());
            preparedStatement.setString(3, questionInfo.getDifficulty());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the questions information to database.");
            System.out.println(e.getMessage());
        }
        return questionInfo.getId();
    }

    @Override
    public long update(QuestionInfo questionInfo, String[] params) {
        return 0;
    }

    @Override
    public void delete(QuestionInfo questionInfo) {

    }
}
