package gui.student;

import gui.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import pao.student.PremiumStudent;
import pao.student.Student;
import pao.student.StudentService;
import utils.classes.Transition;

import java.io.IOException;


public class StudentShopController {

    private Student student;
    private StudentService studentService;

    private @FXML Label usernameLabel;
    private @FXML Label accountStatusLabel;
    private @FXML Label pointsLabel;
    private @FXML Button buyButton;
    private @FXML Label insufficientPointsLabel;

    private @FXML Stage window;

    public void initData(@NotNull Student student) {
        this.student = student;
        this.studentService = new StudentService(student);
        window = (Stage) usernameLabel.getScene().getWindow();
        usernameLabel.setText(student.getUsername());
        loadUI();
    }

    private void loadUI() {
        String status;
        if(student instanceof PremiumStudent) {
            status = "PREMIUM";
            buyButton.setText("ALREADY PREMIUM");
            buyButton.setDisable(true);
        }
        else {
            status = "FREE";
        }
        accountStatusLabel.setText("YOUR ACCOUNT STATUS IS " + status + ".");
        pointsLabel.setText("YOU CURRENTLY HAVE: " + student.getPoints());

    }

    public void buyPremium() {
        this.student = studentService.becomePremium();
        System.out.println(student);
        if(student instanceof PremiumStudent) {
            loadUI();
            Transition.hideErrorLabel(insufficientPointsLabel);
        }
        else {
            Transition.displayErrorLabel(insufficientPointsLabel);
        }
    }


    public void logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) usernameLabel.getScene().getWindow();
        window.setScene(scene);
    }

    public void loadStudentProfileScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentHomeController.class.getResource("home/studentHome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window.setScene(scene);
        StudentHomeController controller = fxmlLoader.getController();
        controller.initData(student);
    }

    public void loadAllCoursesScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentAllCoursesController.class.getResource("allCourses/studentAllCourses.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window.setScene(scene);
        StudentAllCoursesController controller = fxmlLoader.getController();
        controller.initData(student);
    }

    public void loadShopScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentShopController.class.getResource("shop/studentShop.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window.setScene(scene);
        StudentShopController controller = fxmlLoader.getController();
        controller.initData(student);
    }
}
