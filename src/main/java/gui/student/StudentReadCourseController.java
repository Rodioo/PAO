package gui.student;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;
import pao.chapter.Chapter;
import pao.course.Course;
import pao.course.CourseDao;
import pao.courseInformation.CourseInformation;
import pao.student.Student;
import pao.student.StudentDao;

//TODO: Add data init for loading chapter from database
//TODO: Add controller logic for progressing through the course
//TODO: Update course information when student gets to next chapter.
public class StudentReadCourseController {

    private CourseInformation courseInformation;
    private Student student;
    private Course course;
    private Chapter chapter;

    private @FXML Label usernameLabel;
    private @FXML ImageView courseImage;
    private @FXML Label courseTitle;
    private @FXML Label chapterTitle;
    private @FXML TextArea chapterText;
    private @FXML Label questionTitle;
    private @FXML Label questionDifficulty;
    private @FXML ToggleGroup answers;

    public void initData(@NotNull CourseInformation courseInformation) {
        this.courseInformation = courseInformation;
        this.student = StudentDao.getInstance().getById(courseInformation.getIdStudent());
        this.course = CourseDao.getInstance().getById(courseInformation.getIdCourse());
        this.chapter = course.getChapters().get(courseInformation.getNumberChapter());
        this.usernameLabel.setText(this.student.getUsername());
    }

    public void submitAnswer() {

    }

    public void loadPreviousChapter() {

    }

    public void loadNextChapter() {

    }

}
