package gui.welcome;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import gui.registration.RegistrationController;

import java.io.IOException;
import java.util.Objects;


public class WelcomeController {

    @FXML
    private Button getStartedButton;
    @FXML
    private StackPane parentContainer;
    @FXML
    private BorderPane borderPaneContent;

    public void loadRegistrationScene() throws IOException {

        //Get the relative path to the FXML file (the next scene) and the reference of the previous scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(RegistrationController.class.getResource("registration.fxml")));
        Scene scene = getStartedButton.getScene();

        //Adding the next scene to the stage (by putting it after the width of the first scene)
        root.translateXProperty().set(scene.getWidth());
        parentContainer.getChildren().add(root);

        //Making the second scene slide in with a basic ease-in animation and removing the previous scene from the stage
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(e -> parentContainer.getChildren().remove(borderPaneContent));
        timeline.play();
    }

}