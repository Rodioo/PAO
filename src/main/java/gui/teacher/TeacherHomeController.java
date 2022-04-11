package gui.teacher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pao.teacher.Teacher;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherHomeController implements Initializable {

    private Teacher teacher;
    @FXML
    private Label usernameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(Teacher teacher) {
        this.teacher = teacher;
        usernameLabel.setText(this.teacher.getUsername());
    }

}
