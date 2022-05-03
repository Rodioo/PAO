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
import pao.chapter.Chapter;
import pao.course.Course;
import pao.teacher.Teacher;

import java.io.IOException;

public class CreateChaptersController {

    private Teacher teacher;
    private Course course;
    private Chapter chapter;
    private @FXML Label usernameLabel;
    private @FXML TextField titleField;
    private @FXML Button titleErrorIcon;
    private @FXML TextArea textField;
    private @FXML Button textErrorIcon;
    private @FXML Stage window;

    public void initData(Teacher teacher, Course course) {
        this.teacher = teacher;
        this.course = course;
        usernameLabel.setText(this.teacher.getUsername());
    }

    public void initChapter(Chapter chapter) {
        this.chapter = chapter;
        titleField.setText(this.chapter.getTitle());
        textField.setText(this.chapter.getText());
    }

    private boolean areFieldsEmpty() {
        titleErrorIcon.setVisible(titleField.getText().isEmpty());
        textErrorIcon.setVisible(textField.getText().isEmpty());

        return titleErrorIcon.isVisible() || textErrorIcon.isVisible();
    }

    public void loadQuestionScene() throws IOException {
        if(!areFieldsEmpty()) {
            chapter = new Chapter(titleField.getText(), textField.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(CreateQuestionsController.class.getResource("createQuestions.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            window = (Stage) usernameLabel.getScene().getWindow();
            window.setScene(scene);
            CreateQuestionsController controller = fxmlLoader.getController();
            controller.initData(teacher, course, chapter);
        }

    }

    //TODO: Finish adding chapter to course and add it to database
    public void addChapter() {
        if(!areFieldsEmpty()) {

        }
    }

    public void loadTeacherHomeScene() {
        if(areFieldsEmpty())
            return;
    }
}
