package gui.course;


import gui.chapter.CreateChaptersController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pao.course.Course;
import pao.teacher.Teacher;
import pao.teacher.TeacherService;
import utils.classes.Transition;
import utils.enums.AccessType;

import java.io.IOException;

public class CreateCourseController {

    private Teacher teacher;
    private @FXML Label usernameLabel;
    private @FXML TextField titleField;
    private @FXML Button titleErrorIcon;
    private @FXML Label titleErrorLabel;
    private @FXML TextArea descriptionField;
    private @FXML Button descriptionErrorIcon;
    private @FXML Label descriptionErrorLabel;
    private @FXML TextField imageField;
    private @FXML Button imageErrorIcon;
    private @FXML ImageView previewedImage;
    private @FXML ChoiceBox<AccessType> accessChoice;
    private static @FXML Stage window;

    public void initData(Teacher teacher) {
        this.teacher = teacher;
        usernameLabel.setText(this.teacher.getUsername());
        accessChoice.getItems().addAll(AccessType.FREE, AccessType.PREMIUM, AccessType.PRIVATE);
        accessChoice.setValue(AccessType.PRIVATE);
    }

    private boolean isTitleCorrect() {
        if(titleField.getText().isEmpty())
            return false;
        return titleField.getText().length() <= 15;
    }

    private boolean isDescriptionCorrect() {
        if(descriptionField.getText().isEmpty())
            return false;
        return descriptionField.getText().length() >= 15 && descriptionField.getText().length() <= 80;
    }

    private boolean areFieldsCorrect() {
        titleErrorIcon.setVisible(!isTitleCorrect());
        descriptionErrorIcon.setVisible(!isDescriptionCorrect());
        imageErrorIcon.setVisible(imageField.getText().isEmpty());

        return !titleErrorIcon.isVisible() && !descriptionErrorIcon.isVisible() && !imageErrorIcon.isVisible();
    }

    public void viewImage() {
        if (imageField.getText().isEmpty())
            return;
        try {
            previewedImage.setImage(new Image(imageField.getText()));
            imageErrorIcon.setVisible(false);
        }catch (IllegalArgumentException exception) {
            imageErrorIcon.setVisible(true);
        }
    }

    public void displayTitleError() {Transition.displayErrorLabel(titleErrorLabel);}

    public void hideTitleError() {Transition.hideErrorLabel(titleErrorLabel);}

    public void displayDescriptionError() {Transition.displayErrorLabel(descriptionErrorLabel);}

    public void hideDescriptionError() {Transition.hideErrorLabel(descriptionErrorLabel);}

    public void loadCreateChaptersScene() throws IOException {
        if(areFieldsCorrect()) {
            TeacherService teacherService = new TeacherService(teacher);
            Course course = teacherService.createCourse(
                    titleField.getText(),
                    descriptionField.getText(),
                    imageField.getText(),
                    accessChoice.getValue());
            FXMLLoader fxmlLoader = new FXMLLoader(CreateChaptersController.class.getResource("createChapters.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            window = (Stage) usernameLabel.getScene().getWindow();
            window.setScene(scene);
            CreateChaptersController controller = fxmlLoader.getController();
            controller.initData(teacher, course);
        }
    }

}
