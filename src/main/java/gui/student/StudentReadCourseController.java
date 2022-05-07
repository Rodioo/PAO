package gui.student;

import pao.chapter.Chapter;
import pao.course.Course;
import pao.courseInformation.CourseInformation;
import pao.student.Student;

public class StudentReadCourseController {

    private CourseInformation courseInformation;
    private Student student;
    private Course course;
    private Chapter chapter;

    public void initData(CourseInformation courseInformation) {
        this.courseInformation = courseInformation;
        this.student = courseInformation.getStudent();
        this.course = courseInformation.getCurrentCourse();
        this.chapter = courseInformation.getCurrentChapter();
    }
}
