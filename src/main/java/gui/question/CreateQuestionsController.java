package gui.question;

import gui.chapter.CreateChaptersController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pao.auxQuestion.AuxQuestion;
import pao.auxQuestion.AuxQuestionDao;
import pao.chapter.Chapter;
import pao.course.Course;
import pao.question.Question;
import pao.question.QuestionDao;
import pao.teacher.Teacher;
import utils.enums.QuestionDifficulty;

import java.io.IOException;
import java.util.Arrays;

public class CreateQuestionsController {

    private Teacher teacher;
    private Course course;
    private Chapter chapter;
    private @FXML Label usernameLabel;
    private @FXML Label questionsLabel;
    private @FXML TextArea textField;
    private @FXML Button textErrorIcon;
    private @FXML ChoiceBox<QuestionDifficulty> difficultyChoice;
    private @FXML TextField option1Field;
    private @FXML Button option1ErrorIcon;
    private @FXML TextField option2Field;
    private @FXML Button option2ErrorIcon;
    private @FXML TextField option3Field;
    private @FXML Button option3ErrorIcon;
    private @FXML ChoiceBox<String> correctAnswerChoice;
    private @FXML Button correctAnswerErrorIcon;
    private @FXML Stage window;

    public void initData(Teacher teacher, Course course, Chapter chapter) {
        this.teacher = teacher;
        this.course = course;
        this.chapter = chapter;
        usernameLabel.setText(this.teacher.getUsername());
        difficultyChoice.getItems().addAll(QuestionDifficulty.UNSPECIFIED, QuestionDifficulty.EASY,
                QuestionDifficulty.MEDIUM, QuestionDifficulty.HARD);
        difficultyChoice.setValue(QuestionDifficulty.UNSPECIFIED);
    }

    private boolean areFieldsEmpty() {
        textErrorIcon.setVisible(textField.getText().isEmpty());
        option1ErrorIcon.setVisible(option1Field.getText().isEmpty());
        option2ErrorIcon.setVisible(option2Field.getText().isEmpty());
        option3ErrorIcon.setVisible(option3Field.getText().isEmpty());

        return textErrorIcon.isVisible() || option1ErrorIcon.isVisible() || option2ErrorIcon.isVisible() || option3ErrorIcon.isVisible();
    }

    public void initAnswerData() {
        correctAnswerChoice.getItems().clear();
        if(!areFieldsEmpty()) {
            correctAnswerChoice.getItems().addAll(option1Field.getText(), option2Field.getText(), option3Field.getText());
        }
    }

    private void resetFields() {
        textField.setText("");
        option1Field.setText("");
        option2Field.setText("");
        option3Field.setText("");
        correctAnswerChoice.getItems().clear();
    }

    public void addQuestion() {
        if(!areFieldsEmpty()) {
            correctAnswerErrorIcon.setVisible(correctAnswerChoice.getValue() == null);
            if (!correctAnswerErrorIcon.isVisible()) {
                Question question = new Question(textField.getText(), difficultyChoice.getValue(),
                        Arrays.asList(option1Field.getText(), option2Field.getText(), option3Field.getText()),
                        correctAnswerChoice.getValue());
                QuestionDao.getInstance().insert(question);
                AuxQuestionDao.getInstance().insert(new AuxQuestion(this.chapter.getId(), question.getId()));
                this.chapter.addQuestion(question);
                questionsLabel.setText(this.chapter.getQuestions().size() + " QUESTIONS ADDED");
                resetFields();
            }
        }
    }

    public void loadCurrentChapterScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateChaptersController.class.getResource("createChapters.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) usernameLabel.getScene().getWindow();
        window.setScene(scene);
        CreateChaptersController controller = fxmlLoader.getController();
        controller.initData(teacher, course);
        controller.initChapter(chapter);
    }
}
