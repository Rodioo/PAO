package gui.course;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pao.teacher.Teacher;

//TODO:Make UI of course and add it to the database
//TODO: Create Chapter model, repository and table in the database
//TODO: Create Chapter scene and controller and bind it to course
//TODO: Create Question model, repository and table in the database
//TODO: Create Question scene and controller and bind it to Chapter
public class CreateCourseController {

    private Teacher teacher;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField imageField;
    @FXML
    private ImageView previewedImage;
    @FXML
    private static Stage window;

    public void initData(Teacher teacher) {
        this.teacher = teacher;
        usernameLabel.setText(this.teacher.getUsername());
    }

    public void viewImage() {
        if(imageField.getText().isEmpty())
            return;
        previewedImage.setImage(new Image(imageField.getText()));
    }

    public void loadCreateChaptersScene() {

    }
}
