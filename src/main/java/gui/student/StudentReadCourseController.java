package gui.student;

import gui.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import pao.chapter.Chapter;
import pao.course.Course;
import pao.course.CourseDao;
import pao.courseInformation.CourseInformation;
import pao.courseInformation.CourseInformationService;
import pao.question.Question;
import pao.student.Student;
import pao.student.StudentDao;
import pao.student.StudentService;
import utils.classes.Transition;

import java.io.IOException;
import java.util.Random;

public class StudentReadCourseController {

    private CourseInformation courseInformation;
    private CourseInformationService courseInformationService;
    private Student student;
    private StudentService studentService;
    private Course course;
    private Chapter chapter;
    private Question question;

    private @FXML Label usernameLabel;
    private @FXML ImageView courseImage;
    private @FXML Label courseTitle;
    private @FXML Label chapterTitle;
    private @FXML TextArea chapterText;
    private @FXML Label questionTitle;
    private @FXML Label questionDifficulty;
    private @FXML RadioButton answer1;
    private @FXML RadioButton answer2;
    private @FXML RadioButton answer3;
    private @FXML ToggleGroup answers;
    private @FXML Button nextChapterButton;
    private @FXML Label feedbackAnswerLabel;

    private @FXML Stage window;

    private Question pickRandomQuestion() {
        if(chapter == null || chapter.getQuestions() == null || chapter.getQuestions().size() == 0)
            return null;
        return chapter.getQuestions().get(new Random().nextInt(chapter.getQuestions().size()));
    }

    private void loadQuestion() {
        question = pickRandomQuestion();
        if(question == null)
            return;
        questionTitle.setText(question.getText());
        questionDifficulty.setText(question.getDifficulty() + ": " + question.getRewardPoints());
        answer1.setText(question.getOptions().get(0));
        answer2.setText(question.getOptions().get(1));
        answer3.setText(question.getOptions().get(2));
    }

    private void loadCurrentChapter() {
        Transition.hideErrorLabel(feedbackAnswerLabel);
        try{
            courseImage.setImage(new Image(course.getImageUrl()));
        }catch (IllegalArgumentException exception) {
            courseImage.setImage(new Image("https://i.imgur.com/tsm7MJn.png"));
        }
        courseTitle.setText(course.getId() + ". " + course.getName());
        chapterTitle.setText(chapter.getTitle());
        chapterText.setText(chapter.getText());
        loadQuestion();
    }

    public void initData(@NotNull CourseInformation courseInformation) {
        this.courseInformation = courseInformation;
        this.courseInformationService = new CourseInformationService(courseInformation);
        this.student = StudentDao.getInstance().getById(courseInformation.getIdStudent());
        this.studentService = new StudentService(student);
        this.course = CourseDao.getInstance().getById(courseInformation.getIdCourse());
        this.chapter = course.getChapters().get(courseInformation.getNumberChapter());
        this.usernameLabel.setText(this.student.getUsername());
        window = (Stage) usernameLabel.getScene().getWindow();
        loadCurrentChapter();
    }

    public void submitAnswer() {
        String answer = ((RadioButton)answers.getSelectedToggle()).getText();
        if(answer.equals(question.getAnswer())) {
            Transition.hideErrorLabel(feedbackAnswerLabel);
            feedbackAnswerLabel.setText("CONGRATS! RECEIVED " + question.getRewardPoints() + " POINTS.");
            feedbackAnswerLabel.setStyle("-fx-text-fill: #7DBF66;");
            Transition.displayErrorLabel(feedbackAnswerLabel);
            studentService.increasePoints(question.getRewardPoints());
            loadQuestion();
        }
        else {
            Transition.hideErrorLabel(feedbackAnswerLabel);
            feedbackAnswerLabel.setText("INCORRECT ANSWER! TRY AGAIN");
            feedbackAnswerLabel.setStyle("-fx-text-fill: #900E00;");
            Transition.displayErrorLabel(feedbackAnswerLabel);
        }
    }

    public void loadPreviousChapter() {
        if(courseInformation.getNumberChapter() == 0)
            return;
        courseInformationService.incrementStudentProgress(-1);
        this.chapter = course.getChapters().get(courseInformation.getNumberChapter());
        loadCurrentChapter();
    }

    public void loadNextChapter() throws IOException {
        if(courseInformation.getNumberChapter() == this.course.getChapters().size() - 1) {
            courseInformationService.finishCourse();
            loadStudentProfileScene();
            return;
        }
        else if(courseInformation.getNumberChapter() == this.course.getChapters().size() - 2) {
            nextChapterButton.setText("FINISH COURSE");
        }
        else {
            nextChapterButton.setText("NEXT CHAPTER");
        }
        courseInformationService.incrementStudentProgress(1);
        this.chapter = course.getChapters().get(courseInformation.getNumberChapter());
        loadCurrentChapter();
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
