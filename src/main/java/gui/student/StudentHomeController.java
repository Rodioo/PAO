package gui.student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pao.student.Student;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentHomeController implements Initializable {

    private Student student;
    @FXML
    private Label usernameLabel;
    @FXML
    private StackPane selectCoursePane;
    @FXML
    private StackPane continueCoursePane;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(Student student) {
        this.student = student;
        usernameLabel.setText(student.getUsername());
        displayCorrectScene();
    }

    private void displayCorrectScene() {
        if(student.getUsername().equals("ghee")) {
            continueCoursePane.setVisible(false);
            selectCoursePane.setVisible(true);
        }
        else {
            selectCoursePane.setVisible(false);
            continueCoursePane.setVisible(true);
        }
    }
}
