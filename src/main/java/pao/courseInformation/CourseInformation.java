package pao.courseInformation;

import org.jetbrains.annotations.NotNull;
import pao.chapter.Chapter;
import pao.course.Course;
import pao.student.Student;

public class CourseInformation {

    private final long id;
    private Student student;
    private Course currentCourse;
    private Chapter currentChapter;

    public CourseInformation(Student student, @NotNull Course currentCourse) {
        this.id = CourseInformationDao.getInstance().getNextId();
        this.student = student;
        this.currentCourse = currentCourse;
        this.currentChapter = currentCourse.getChapters().get(0);
    }

    public CourseInformation(long id, Student student, @NotNull Course currentCourse, int numberChapter) {
        this.id = id;
        this.student = student;
        this.currentCourse = currentCourse;
        this.currentChapter = currentCourse.getChapters().get(numberChapter);
    }

    public long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public Chapter getCurrentChapter() {
        return currentChapter;
    }

    @Override
    public String toString() {
        return "CourseInformation{" +
                "id=" + id +
                ", currentCourse=" + currentCourse +
                ", currentChapter=" + currentChapter +
                '}';
    }
}
