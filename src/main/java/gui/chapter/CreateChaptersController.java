package gui.chapter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pao.course.Course;
import pao.teacher.Teacher;

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
        usernameLabel.setText(this.teacher.getUsername());
        this.course = course;
        System.out.println(this.course);
    }

    private boolean areFieldsEmpty() {
        titleErrorIcon.setVisible(titleField.getText().isEmpty());
        textErrorIcon.setVisible(textField.getText().isEmpty());

        return titleErrorIcon.isVisible() || textErrorIcon.isVisible();
    }

    public void loadQuestionScene() {
        if(areFieldsEmpty())
            return;
    }

    public void loadTeacherHomeScene() {
        if(areFieldsEmpty())
            return;
    }
}
