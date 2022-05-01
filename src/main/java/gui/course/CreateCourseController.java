package gui.course;


import gui.chapter.CreateChaptersController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pao.course.Course;
import pao.teacher.Teacher;

import java.io.IOException;

//TODO:Make UI of course and add it to the database
//TODO: Create Chapter model, repository and table in the database
//TODO: Create Chapter scene and controller and bind it to course
//TODO: Create Question model, repository and table in the database
//TODO: Create Question scene and controller and bind it to Chapter
public class CreateCourseController {

    private Teacher teacher;
    private @FXML Label usernameLabel;
    private @FXML TextField titleField;
    private @FXML Button titleErrorIcon;
    private @FXML TextArea descriptionField;
    private @FXML Button descriptionErrorIcon;
    private @FXML TextField imageField;
    private @FXML Button imageErrorIcon;
    private @FXML ImageView previewedImage;
    private static @FXML Stage window;

    public void initData(Teacher teacher) {
        this.teacher = teacher;
        usernameLabel.setText(this.teacher.getUsername());
    }

    private boolean areFieldsEmpty() {
        titleErrorIcon.setVisible(titleField.getText().isEmpty());
        descriptionErrorIcon.setVisible(descriptionField.getText().isEmpty());
        imageErrorIcon.setVisible(imageField.getText().isEmpty());

        return titleErrorIcon.isVisible() || descriptionErrorIcon.isVisible() || imageErrorIcon.isVisible();
    }

    public void viewImage() {
        if(imageField.getText().isEmpty())
            return;
        previewedImage.setImage(new Image(imageField.getText()));
    }

    public void loadCreateChaptersScene() throws IOException {
        if(!areFieldsEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader(CreateChaptersController.class.getResource("createChapters.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            window = (Stage) usernameLabel.getScene().getWindow();
            window.setScene(scene);
            CreateChaptersController controller = fxmlLoader.getController();
            Course course = new Course(titleField.getText(), descriptionField.getText(), imageField.getText());
            controller.initData(teacher, course);
        }
    }
}
