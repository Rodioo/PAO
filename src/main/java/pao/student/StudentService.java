package pao.student;

import org.jetbrains.annotations.NotNull;
import pao.course.Course;
import pao.courseInformation.CourseInformation;
import pao.courseInformation.CourseInformationDao;
import utils.enums.AccessType;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final Student student;

    public StudentService(Student student) {
        this.student = student;
    }

    public List<Course> filterCourses(@NotNull List<Course> courses) {
        List<Course> filteredCourses = new ArrayList<>();
        for(Course course : courses) {
            if(course.getAccess().equals(AccessType.FREE) ||
                    (course.getAccess().equals(AccessType.PREMIUM) && student instanceof PremiumStudent)) {
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }

    public CourseInformation enrollInCourse(@NotNull Course course) {
        if(course.getAccess().equals(AccessType.PRIVATE)) {
            return null;
        }
        if(course.getAccess().equals(AccessType.PREMIUM) && !(student instanceof PremiumStudent)) {
            return null;
        }
        CourseInformation courseInformation = new CourseInformation(student.getId(), course.getId());
        CourseInformationDao.getInstance().insert(courseInformation);
        student.setCourseInformation(courseInformation);
        return courseInformation;
    }
}
