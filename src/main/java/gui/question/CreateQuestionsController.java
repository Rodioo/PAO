package gui.question;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pao.course.Course;
import pao.question.Question;
import pao.teacher.Teacher;
import utils.AccessType;
import utils.QuestionDifficulty;

import java.util.Arrays;

public class CreateQuestionsController {

    private Teacher teacher;
    private Course course;
    private @FXML Label usernameLabel;
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

    public void initData(Teacher teacher, Course course) {
        this.teacher = teacher;
        this.course = course;
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

    public void loadCurrentChapterScene() {
        if(!areFieldsEmpty()) {
            correctAnswerErrorIcon.setVisible(correctAnswerChoice.getValue() == null);
            if(!correctAnswerErrorIcon.isVisible()) {
                Question question = new Question(textField.getText(), difficultyChoice.getValue(),
                        Arrays.asList(option1Field.getText(), option2Field.getText(), option3Field.getText()),
                        correctAnswerChoice.getValue());
                System.out.println(question);
            }
        }
    }
}
