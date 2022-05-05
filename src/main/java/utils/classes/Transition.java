package utils.classes;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Transition {

    public static void displayErrorLabel(Label errorLabel) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), errorLabel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public static void hideErrorLabel(Label errorLabel) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), errorLabel);
        fadeOut.setFromValue(errorLabel.getOpacity());
        fadeOut.setToValue(0.0);
        fadeOut.play();
    }

}
