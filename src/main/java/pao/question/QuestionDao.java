package pao.question;

import db.Dao;
import utils.Countable;
import utils.QuestionDifficulty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class QuestionDao implements Countable, Dao<Question> {

    private static List<Question> questions = new ArrayList<>();
    private static QuestionDao singleton = null;

    private QuestionDao() {
        questions = getAll();
    }

    public static QuestionDao getInstance() {
        if(singleton == null) {
            singleton = new QuestionDao();
        }
        return singleton;
    }

    @Override
    public long getNextId() {
        return (questions == null) ? 1 : questions.size() + 1;
    }

    @Override
    public Question rowToObject(ResultSet resultSet) {
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
            List<String> options = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                String option = resultSet.getString("option" + i);
                options.add(option);
            }
            String answer = resultSet.getString("answer");
            return new Question(id, text, difficulty, options, answer);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Question getById(long id) {
        return questions.stream()
                .filter(question -> question.getId() == id)
                .findAny().orElse(null);
    }

    public List<Question> getQuestionsByIds(List<Long> auxQuestions) {
        return questions.stream()
                .filter(question -> auxQuestions.contains(question.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Question> getAll() {
        List<Question> questionList = new ArrayList<>();
        try{
            String query = "SELECT * FROM proiectpao.questions;";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Question question = rowToObject(resultSet);
                questionList.add(question);
            }
        }catch (SQLException e) {
            System.out.println("Error occurred when getting the questions from the database.");
            System.out.println(e.getMessage());
        }
        return questionList;
    }

    @Override
    public long insert(Question question) {
        if(question == null)
            return -1;
        questions.add(question);
        try {
            String query = "INSERT INTO proiectpao.questions(idQuestion, text, difficulty, option0, option1, option2, answer) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, question.getId());
            preparedStatement.setString(2, question.getText());
            preparedStatement.setString(3, question.getDifficulty());
            for(int i = 0; i < 3; i++) {
                preparedStatement.setString(i + 4, question.getOptions().get(i));
            }
            preparedStatement.setString(7, question.getAnswer());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the questions in the database.");
            System.out.println(e.getMessage());
        }
        return question.getId();
    }

    @Override
    public long update(Question question, String[] params) {
        return 0;
    }

    @Override
    public void delete(Question question) {

    }
}
