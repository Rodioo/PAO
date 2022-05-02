package gui.chapter;

import gui.question.CreateQuestionsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pao.course.Course;
import pao.teacher.Teacher;

import java.io.IOException;

public class CreateChaptersController {

    private Teacher teacher;
    private Course course;
    private @FXML Label usernameLabel;
    private @FXML TextField titleField;
    private @FXML Button titleErrorIcon;
    private @FXML TextArea textField;
    private @FXML Button textErrorIcon;
    private @FXML Label questionsLabel;
    private @FXML Stage window;

    public void initData(Teacher teacher, Course course) {
        this.teacher = teacher;
        this.course = course;
        usernameLabel.setText(this.teacher.getUsername());
    }

    private boolean areFieldsEmpty() {
        titleErrorIcon.setVisible(titleField.getText().isEmpty());
        textErrorIcon.setVisible(textField.getText().isEmpty());

        return titleErrorIcon.isVisible() || textErrorIcon.isVisible();
    }

    public void loadQuestionScene() throws IOException {
        if(!areFieldsEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader(CreateQuestionsController.class.getResource("createQuestions.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            window = (Stage) usernameLabel.getScene().getWindow();
            window.setScene(scene);
            CreateQuestionsController controller = fxmlLoader.getController();
            controller.initData(teacher, course);
        }

    }

    public void loadTeacherHomeScene() {
        if(areFieldsEmpty())
            return;
    }
}
