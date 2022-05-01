package gui.teacher;

import gui.course.CreateCourseController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pao.teacher.Teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherHomeController implements Initializable {

    private Teacher teacher;
    private @FXML Label usernameLabel;
    private static @FXML Stage window;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(Teacher teacher) {
        this.teacher = teacher;
        usernameLabel.setText(this.teacher.getUsername());
    }

    public void loadCreateCourseScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateCourseController.class.getResource("createCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) usernameLabel.getScene().getWindow();
        window.setScene(scene);
        CreateCourseController controller = fxmlLoader.getController();
        controller.initData(teacher);
    }


}
