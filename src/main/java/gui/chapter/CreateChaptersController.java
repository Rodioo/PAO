package gui.chapter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pao.teacher.Teacher;

public class CreateChaptersController {

    private Teacher teacher;
    private @FXML Label usernameLabel;

    public void initData(Teacher teacher, String title, String description, String imageUrl) {
        this.teacher = teacher;
        usernameLabel.setText(this.teacher.getUsername());
    }
}
