package gui.welcome;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import gui.login.LoginController;

import java.io.IOException;


public class WelcomeController {

    private @FXML Button getStartedButton;
    private static @FXML Stage window;

    //Loads the next scene (Login in this case)
    public void loadLoginScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) getStartedButton.getScene().getWindow();
        window.setScene(scene);
    }

}