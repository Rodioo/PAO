package gui.chapter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pao.course.Course;
import pao.teacher.Teacher;

public class CreateChaptersController {

    private Teacher teacher;
    private Course course;
    private @FXML Label usernameLabel;

    public void initData(Teacher teacher, Course course) {
        this.teacher = teacher;
        usernameLabel.setText(this.teacher.getUsername());
        this.course = course;
    }
}
