package gui.register;



import gui.student.StudentHomeController;
import gui.teacher.TeacherHomeController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import pao.student.Student;
import pao.student.StudentDao;
import pao.teacher.TeacherDao;
import pao.user.User;
import pao.user.UserFactory;
import pao.user.UserService;

import java.io.IOException;
import java.util.regex.Pattern;


public class RegisterController{

    @FXML
    private TextField usernameField;
    @FXML
    private Button usernameErrorButton;
    @FXML
    private Label usernameErrorLabel;
    @FXML
    private TextField emailField;
    @FXML
    private Button emailErrorButton;
    @FXML
    private Label emailErrorLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button passwordErrorButton;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button confirmPasswordErrorButton;
    @FXML
    private Label confirmPasswordErrorLabel;
    @FXML
    private ToggleGroup userTypeOptions;
    @FXML
    private Label passwordRequirementsLabel;
    @FXML
    private Stage window;

    private long userId;

    private void displayErrorLabel(Label errorLabel) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), errorLabel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    private void hideErrorLabel(Label errorLabel) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), errorLabel);
        fadeOut.setFromValue(errorLabel.getOpacity());
        fadeOut.setToValue(0.0);
        fadeOut.play();
    }

    public void displayUsernameError() {displayErrorLabel(usernameErrorLabel);}

    public void hideUsernameError() {hideErrorLabel(usernameErrorLabel);}

    public void displayEmailError() {displayErrorLabel(emailErrorLabel);}

    public void hideEmailError() {hideErrorLabel(emailErrorLabel);}

    public void displayPasswordError() {displayErrorLabel(passwordErrorLabel);}

    public void hidePasswordError() {hideErrorLabel(passwordErrorLabel);}

    public void displayConfirmPasswordError() {displayErrorLabel(confirmPasswordErrorLabel);}

    public void hideConfirmPasswordError() {hideErrorLabel(confirmPasswordErrorLabel);}


    //TODO Check for taken username and email in database after implementing
    public void registerUser() throws IOException {

        usernameErrorButton.setVisible(!validateUser());

        emailErrorButton.setVisible(!validateEmail());

        if(validatePassword()) {
            passwordErrorButton.setVisible(false);
            hideErrorLabel(passwordRequirementsLabel);
        }
        else {
            passwordErrorButton.setVisible(true);
            displayErrorLabel(passwordRequirementsLabel);
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

        return Pattern.compile("^[a-zA-Z0-9]\\w{3,20}$")
                .matcher(usernameField.getText())
                .matches();
    }

    private boolean validateEmail() {
        if(emailField.getText().isEmpty())
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
}
