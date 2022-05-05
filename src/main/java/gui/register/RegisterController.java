package gui.register;

import gui.login.LoginController;
import gui.student.StudentHomeController;
import gui.teacher.TeacherHomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import pao.student.StudentDao;
import pao.teacher.TeacherDao;
import pao.user.UserDao;
import pao.user.UserService;
import utils.classes.Transition;

import java.io.IOException;
import java.util.regex.Pattern;


public class RegisterController{

    private @FXML TextField usernameField;
    private @FXML Button usernameErrorButton;
    private @FXML Label usernameErrorLabel;
    private @FXML TextField emailField;
    private @FXML Button emailErrorButton;
    private @FXML Label emailErrorLabel;
    private @FXML PasswordField passwordField;
    private @FXML Button passwordErrorButton;
    private @FXML Label passwordErrorLabel;
    private @FXML PasswordField confirmPasswordField;
    private @FXML Button confirmPasswordErrorButton;
    private @FXML Label confirmPasswordErrorLabel;
    private @FXML ToggleGroup userTypeOptions;
    private @FXML Label passwordRequirementsLabel;
    private @FXML Stage window;
    private long userId;

    public void displayUsernameError() {Transition.displayErrorLabel(usernameErrorLabel);}

    public void hideUsernameError() {Transition.hideErrorLabel(usernameErrorLabel);}

    public void displayEmailError() {Transition.displayErrorLabel(emailErrorLabel);}

    public void hideEmailError() {Transition.hideErrorLabel(emailErrorLabel);}

    public void displayPasswordError() {Transition.displayErrorLabel(passwordErrorLabel);}

    public void hidePasswordError() {Transition.hideErrorLabel(passwordErrorLabel);}

    public void displayConfirmPasswordError() {Transition.displayErrorLabel(confirmPasswordErrorLabel);}

    public void hideConfirmPasswordError() {Transition.hideErrorLabel(confirmPasswordErrorLabel);}

    public void registerUser() throws IOException {

        usernameErrorButton.setVisible(!validateUser());

        emailErrorButton.setVisible(!validateEmail());

        if(validatePassword()) {
            passwordErrorButton.setVisible(false);
            Transition.hideErrorLabel(passwordRequirementsLabel);
        }
        else {
            passwordErrorButton.setVisible(true);
            Transition.displayErrorLabel(passwordRequirementsLabel);
        }

        confirmPasswordErrorButton.setVisible(!validateConfirmPassword());

        String userType = ((RadioButton)userTypeOptions.getSelectedToggle()).getText();

        if(validateUser() && validateEmail() && validatePassword() && validateConfirmPassword()) {
            userId = UserService.registerUser(usernameField.getText(), emailField.getText(), passwordField.getText(), userType);
            loadScene(userType);
        }

    }

    private boolean validateUser() {
        if(usernameField.getText().isEmpty())
            return false;

        if(UserDao.getInstance().isUsernameTaken(usernameField.getText()))
            return false;

        return Pattern.compile("^[a-zA-Z0-9]\\w{3,20}$")
                .matcher(usernameField.getText())
                .matches();
    }

    private boolean validateEmail() {
        if(emailField.getText().isEmpty())
            return false;

        if(UserDao.getInstance().isEmailTaken(emailField.getText()))
            return false;

        return Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
                .matcher(emailField.getText())
                .matches();
    }

    private boolean validatePassword() {
        if(passwordField.getText().isEmpty())
            return false;

        return Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
                .matcher(passwordField.getText())
                .matches();
    }

    private boolean validateConfirmPassword() {
        if(confirmPasswordField.getText().isEmpty())
            return false;

        return confirmPasswordField.getText().equals(passwordField.getText());
    }

    private void loadScene(@NotNull String userType) throws IOException {
        window = (Stage) usernameField.getScene().getWindow();
        FXMLLoader fxmlLoader;
        Scene scene;
        if(userType.equals("STUDENT")) {
            fxmlLoader = new FXMLLoader(StudentHomeController.class.getResource("studentHome.fxml"));
            scene = new Scene(fxmlLoader.load(), 800, 600);
            window.setScene(scene);
            StudentHomeController controller = fxmlLoader.getController();
            controller.initData(StudentDao.getInstance().getById(userId));
        }
        else if(userType.equals("TEACHER")) {
            fxmlLoader = new FXMLLoader(TeacherHomeController.class.getResource("teacherHome.fxml"));
            scene = new Scene(fxmlLoader.load(), 800, 600);
            window.setScene(scene);
            TeacherHomeController controller = fxmlLoader.getController();
            controller.initData(TeacherDao.getInstance().getById(userId));
        }
    }

    public void loadLoginScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) usernameField.getScene().getWindow();
        window.setScene(scene);
    }

}
