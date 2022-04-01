package gui.login;


import gui.register.RegisterController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button registerButton;
    @FXML
    private Stage window;

    //TODO surround with try-catch
    //Loads the next scene (Register in this case)
    public void loadRegisterScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterController.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) registerButton.getScene().getWindow();
        window.setScene(scene);

    }
}
