package gui.student;

import org.jetbrains.annotations.NotNull;
import pao.chapter.Chapter;
import pao.course.Course;
import pao.course.CourseDao;
import pao.courseInformation.CourseInformation;
import pao.student.Student;
import pao.student.StudentDao;

//TODO: Add UI of scene and controller logic for progressing through the course
//TODO: Update course information when student gets to next chapter.
public class StudentReadCourseController {

    private CourseInformation courseInformation;
    private Student student;
    private Course course;
    private Chapter chapter;

    public void initData(@NotNull CourseInformation courseInformation) {
        this.courseInformation = courseInformation;
        this.student = StudentDao.getInstance().getById(courseInformation.getIdStudent());
        this.course = CourseDao.getInstance().getById(courseInformation.getIdCourse());
        this.chapter = course.getChapters().get(courseInformation.getNumberChapter());
    }
}
