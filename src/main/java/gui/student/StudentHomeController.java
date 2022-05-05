package gui.student;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;
import pao.course.Course;
import pao.course.CourseDao;
import pao.course.CourseService;
import pao.student.Student;

import java.util.List;
import java.util.Random;

public class StudentHomeController{

    private Student student;
    private List<Course> courses;
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
    private @FXML ImageView courseImage2;
    private @FXML Label courseTitle2;
    private @FXML Label courseTeacher2;
    private @FXML Label numberOfChapters2;
    private @FXML Label numberOfQuestions2;
    private @FXML Label courseDescription2;
    private @FXML Label courseAccess2;

    public void initData(@NotNull Student student) {
        this.student = student;
        usernameLabel.setText(student.getUsername());
        courses = CourseDao.getInstance().getAll();
        displayCorrectScene();
    }

    private Course pickRandomCourse() {
        if(courses == null)
            return null;
        return courses.get(new Random().nextInt(courses.size()));
    }

    private void loadCourseData(ImageView courseImage, Label courseTitle, Label courseTeacher,
                           Label numberOfChapters, Label numberOfQuestions,
                           Label courseDescription, Label courseAccess) {
        Course course = pickRandomCourse();
        if(course == null)
            return;
        System.out.println(course);
        CourseService courseService = new CourseService(course);
        try{
            courseImage.setImage(new Image(course.getImageUrl()));
        }catch (IllegalArgumentException exception) {
            courseImage.setImage(new Image("https://i.imgur.com/tsm7MJn.png"));
        }
        courseTitle.setText(course.getName());
        courseTeacher.setText("Made by " + courseService.getCourseTeacherName());
        numberOfChapters.setText(courseService.getCourseNumberOfChapters() + " CHAPTERS");
        numberOfQuestions.setText(courseService.getCourseNumberOfQuestions() + " QUESTIONS");
        courseDescription.setText(course.getDescription());
        courseAccess.setText(course.getAccess().toString());
    }

    private void displayCorrectScene() {
        if(student.getCourseInformation() == null) {
            continueCoursePane.setVisible(false);
            selectCoursePane.setVisible(true);
            loadCourseData(courseImage1, courseTitle1, courseTeacher1,
                    numberOfChapters1, numberOfQuestions1, courseDescription1, courseAccess1);
            loadCourseData(courseImage2, courseTitle2, courseTeacher2,
                    numberOfChapters2, numberOfQuestions2, courseDescription2, courseAccess2);
        }
        else {
            selectCoursePane.setVisible(false);
            continueCoursePane.setVisible(true);
            //TODO: Initialize CourseInformation and display accordingly
        }
    }

    public void startCourse() {

    }
}
