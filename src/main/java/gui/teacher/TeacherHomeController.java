package gui.teacher;

import gui.course.CreateCourseController;
import gui.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pao.teacher.Teacher;

import java.io.IOException;

public class TeacherHomeController {

    private Teacher teacher;
    private @FXML Label usernameLabel;
    private @FXML Stage window;

    public void initData(Teacher teacher) {
        this.teacher = teacher;
        usernameLabel.setText(this.teacher.getUsername());
    }

    public void logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) usernameLabel.getScene().getWindow();
        window.setScene(scene);
    }

    public void loadCreateCourseScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateCourseController.class.getResource("createCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) usernameLabel.getScene().getWindow();
        window.setScene(scene);
        CreateCourseController controller = fxmlLoader.getController();
        controller.initData(teacher);
    }

    public void loadTeacherProfileScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TeacherHomeController.class.getResource("teacherHome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window.setScene(scene);
        TeacherHomeController controller = fxmlLoader.getController();
        controller.initData(teacher);
    }

    public void loadAllCoursesScene() {

    }
}
