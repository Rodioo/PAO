package gui.forgotPassword;

import gui.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pao.user.UserService;
import utils.classes.Transition;

import java.io.IOException;

public class ForgotPasswordController {

    private @FXML TextField emailField;
    private @FXML Button emailErrorButton;
    private @FXML Label emailErrorLabel;
    private @FXML Label passwordLabel;

    private @FXML Stage window;

    public void displayEmailError() {Transition.displayErrorLabel(emailErrorLabel);}

    public void hideEmailError() {Transition.hideErrorLabel(emailErrorLabel);}

    public void loadLoginScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) emailField.getScene().getWindow();
        window.setScene(scene);
    }

    public void receivePassword() {
        if(emailField.getText().isEmpty()) {
            emailErrorButton.setVisible(true);
            return;
        }
        String password = UserService.receivePassword(emailField.getText());
        if(password == null) {
            emailErrorButton.setVisible(true);
            return;
        }
        emailErrorButton.setVisible(false);
        passwordLabel.setText("YOUR PASSWORD IS: " + password);

    }
}
