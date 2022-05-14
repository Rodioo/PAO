package gui.student;

import gui.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import pao.chapter.Chapter;
import pao.course.Course;
import pao.course.CourseDao;
import pao.course.CourseService;
import pao.courseInformation.CourseInformation;
import pao.courseInformation.CourseInformationDao;
import pao.student.Student;
import pao.student.StudentService;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class StudentHomeController{

    private Student student;
    private StudentService studentService;
    private List<Course> courses;
    private List<Course> filteredCourses;
    private CourseInformation pickedCourseInformation;
    private @FXML Label usernameLabel;
    private @FXML StackPane selectCoursePane;
    private @FXML StackPane continueCoursePane;
    private @FXML ImageView courseImage1;
    private @FXML Label courseTitle1;
    private @FXML Label courseTeacher1;
    private @FXML Label numberOfChapters1;
    private @FXML Label numberOfQuestions1;
    private @FXML Label courseDescription1;
    private @FXML Label courseAccess1;
    private @FXML Button button1;
    private @FXML ImageView courseImage2;
    private @FXML Label courseTitle2;
    private @FXML Label courseTeacher2;
    private @FXML Label numberOfChapters2;
    private @FXML Label numberOfQuestions2;
    private @FXML Label courseDescription2;
    private @FXML Label courseAccess2;

    private @FXML Label chapterName;
    private @FXML Label progressLabel;
    private @FXML ProgressBar progressBar;
    private @FXML ImageView courseImage;
    private @FXML Label courseTitle;
    private @FXML Label courseTeacher;
    private @FXML Label numberOfChapters;
    private @FXML Label numberOfQuestions;
    private @FXML Label courseDescription;
    private @FXML Label courseAccess;

    private @FXML Stage window;

    public void initData(@NotNull Student student) {
        this.student = student;
        studentService = new StudentService(student);
        window = (Stage) usernameLabel.getScene().getWindow();
        usernameLabel.setText(student.getUsername());
        courses = CourseDao.getInstance().getAll();
        filteredCourses = studentService.filterCourses(courses);
        displayCorrectScene();
    }

    public void logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window = (Stage) usernameLabel.getScene().getWindow();
        window.setScene(scene);
    }

    private Course pickRandomCourse() {
        if(filteredCourses == null || filteredCourses.size() == 0)
            return null;
        return filteredCourses.get(new Random().nextInt(filteredCourses.size()));
    }

    private void loadCourseData(Course course, ImageView courseImage, Label courseTitle, Label courseTeacher,
                           Label numberOfChapters, Label numberOfQuestions,
                           Label courseDescription, Label courseAccess) {
        if(course == null)
            return;
        CourseService courseService = new CourseService(course);
        try{
            courseImage.setImage(new Image(course.getImageUrl()));
        }catch (IllegalArgumentException exception) {
            courseImage.setImage(new Image("https://i.imgur.com/tsm7MJn.png"));
        }
        courseTitle.setText(course.getId() + ". " + course.getName());
        courseTeacher.setText("Made by " + courseService.getCourseTeacherName());
        numberOfChapters.setText(courseService.getCourseNumberOfChapters() + " CHAPTERS");
        numberOfQuestions.setText(courseService.getCourseNumberOfQuestions() + " QUESTIONS");
        courseDescription.setText(course.getDescription());
        courseAccess.setText(course.getAccess().toString());
    }

    private void pickCourseAndLoadData() {
        loadCourseData(pickRandomCourse(), courseImage1, courseTitle1, courseTeacher1,
                numberOfChapters1, numberOfQuestions1, courseDescription1, courseAccess1);
        loadCourseData(pickRandomCourse(), courseImage2, courseTitle2, courseTeacher2,
                numberOfChapters2, numberOfQuestions2, courseDescription2, courseAccess2);
    }

    private void loadCourseInformationData() {
        pickedCourseInformation = CourseInformationDao.getInstance()
                .getCourseInformationByStudentId(student.getId());
        Course course = CourseDao.getInstance().getById(pickedCourseInformation.getIdCourse());
        Chapter currentChapter = course.getChapters().get(pickedCourseInformation.getNumberChapter());
        chapterName.setText(currentChapter.getTitle());
        double percentageProgress = pickedCourseInformation.getNumberChapter() / (double) course.getChapters().size() * 100;
        progressLabel.setText(pickedCourseInformation.getNumberChapter() + " / " + course.getChapters().size() +
                " (" + (int) percentageProgress + "%) chapters completed.");
        progressBar.setProgress(percentageProgress / 100);
        loadCourseData(course, courseImage, courseTitle, courseTeacher,
                numberOfChapters, numberOfQuestions, courseDescription, courseAccess);
    }

    private void displayCorrectScene() {
        if(student.getCourseInformation() == null) {
            continueCoursePane.setVisible(false);
            selectCoursePane.setVisible(true);
            pickCourseAndLoadData();
        }
        else {
            selectCoursePane.setVisible(false);
            continueCoursePane.setVisible(true);
            loadCourseInformationData();
        }
    }

    public void startCourse(@NotNull ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        Course pickedCourse;
        if(sourceButton == button1) {
            long id = Long.parseLong(courseTitle1.getText().split("[.]")[0]);
            pickedCourse = CourseDao.getInstance().getById(id);
        }
        else {
            long id = Long.parseLong(courseTitle2.getText().split("[.]")[0]);
            pickedCourse = CourseDao.getInstance().getById(id);
    }
        pickedCourseInformation = studentService.enrollInCourse(pickedCourse);
        loadReadCourseScene();

    }

    public void loadReadCourseScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentReadCourseController.class.getResource("readCourse/studentReadCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        window.setScene(scene);
        StudentReadCourseController controller = fxmlLoader.getController();
        controller.initData(pickedCourseInformation);
    }


}
