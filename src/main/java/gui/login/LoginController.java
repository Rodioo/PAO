package gui.login;


import gui.forgotPassword.ForgotPasswordController;
import gui.register.RegisterController;
import gui.student.StudentHomeController;
import gui.teacher.TeacherHomeController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import pao.student.Student;
import pao.student.StudentDao;
import pao.teacher.Teacher;
import pao.teacher.TeacherDao;
import pao.user.User;
import pao.user.UserDao;

import java.io.IOException;

public class LoginController {

    private @FXML TextField usernameField;
    private @FXML TextField passwordField;
    private @FXML Label loginIncorrectLabel;
    private @FXML Button registerButton;
    private @FXML Stage window;


    private void displayErrorLabel(Label errorLabel) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), errorLabel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void loginUser() throws IOException {
        User user = UserDao.getInstance().getByUsernameAndPassword(usernameField.getText(), passwordField.getText());
        if(user == null) {
            displayErrorLabel(loginIncorrectLabel);
        }
        else {
            window = (Stage) usernameField.getScene().getWindow();
            Student student = StudentDao.getInstance().getById(user.getId());
            if(student == null) {
                Teacher teacher = TeacherDao.getInstance().getById(user.getId());
                if(teacher == null) {
                    displayErrorLabel(loginIncorrectLabel);
                }
                else {
                    FXMLLoader fxmlLoader = new FXMLLoader(TeacherHomeController.class.getResource("teacherHome.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                    window.setScene(scene);
                    TeacherHomeController controller = fxmlLoader.getController();
                    controller.initData(teacher);
                }
            }
            else {
                FXMLLoader fxmlLoader = new FXMLLoader(StudentHomeController.class.getResource("home/studentHome.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                window.setScene(scene);
                StudentHomeController controller = fxmlLoader.getController();
                controller.initData(student);
            }
        }
    }

    //Loads the next scene (Register in this case)
    public void loadRegisterScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterController.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) registerButton.getScene().getWindow();
        window.setScene(scene);
    }

    public void loadForgetPasswordScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ForgotPasswordController.class.getResource("forgotPassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) registerButton.getScene().getWindow();
        window.setScene(scene);
    }
}
