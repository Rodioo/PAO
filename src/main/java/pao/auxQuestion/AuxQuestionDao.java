package pao.auxQuestion;

import db.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuxQuestionDao implements Dao<AuxQuestion> {

    private static List<AuxQuestion> auxQuestions = new ArrayList<>();
    private static AuxQuestionDao singleton = null;

    private AuxQuestionDao() {
        auxQuestions = getAll();
    }

    public static AuxQuestionDao getInstance() {
        if(singleton == null) {
            singleton = new AuxQuestionDao();
        }
        return singleton;
    }

    @Override
    public AuxQuestion rowToObject(ResultSet resultSet) {
        return null;
    }

    @Override
    public AuxQuestion getById(long id) {
        return null;
    }

    @Override
    public List<AuxQuestion> getAll() {
        return new ArrayList<>();
    }

    @Override
    public long insert(AuxQuestion auxQuestion) {
        if(auxQuestion == null)
            return -1;
        auxQuestions.add(auxQuestion);
        try {
            String query = "INSERT INTO proiectpao.aux_questions(idChapter, idQuestion) " +
                    "VALUES (?, ?);";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
            preparedStatement.setLong(1, auxQuestion.getIdChapter());
            preparedStatement.setLong(2, auxQuestion.getIdQuestion());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Error occurred when inserting the questions in the database.");
            System.out.println(e.getMessage());
        }
        return auxQuestion.getIdQuestion();
    }

    @Override
    public long update(AuxQuestion auxQuestion, String[] params) {
        return 0;
    }

    @Override
    public void delete(AuxQuestion auxQuestion) {

    }
}
