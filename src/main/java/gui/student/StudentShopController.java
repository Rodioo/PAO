package gui.student;

import gui.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import pao.student.Student;

import java.io.IOException;

//TODO: Add shop UI and logic in controller
public class StudentShopController {

    private Student student;

    private @FXML Label usernameLabel;

    private @FXML Stage window;

    public void initData(@NotNull Student student) {
        this.student = student;
        window = (Stage) usernameLabel.getScene().getWindow();
        usernameLabel.setText(student.getUsername());
    }

    public void logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) usernameLabel.getScene().getWindow();
        window.setScene(scene);
    }

    public void loadStudentProfileScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentHomeController.class.getResource("home/studentHome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window.setScene(scene);
        StudentHomeController controller = fxmlLoader.getController();
        controller.initData(student);
    }

    public void loadAllCoursesScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentAllCoursesController.class.getResource("allCourses/studentAllCourses.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window.setScene(scene);
        StudentAllCoursesController controller = fxmlLoader.getController();
        controller.initData(student);
    }

    public void loadShopScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentAllCoursesController.class.getResource("shop/studentShop.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window.setScene(scene);
        StudentAllCoursesController controller = fxmlLoader.getController();
        controller.initData(student);
    }
}
