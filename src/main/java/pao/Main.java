package pao;

import javafx.application.Application;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gui.welcome.WelcomeController;
import pao.student.StudentDao;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage window) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomeController.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window.setTitle("OnLearn");
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}