package pao.student;

import db.AuditService;
import org.jetbrains.annotations.NotNull;
import pao.course.Course;
import pao.courseInformation.CourseInformation;
import pao.courseInformation.CourseInformationDao;
import utils.enums.AccessType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentService {

    private final Student student;
    private final AuditService auditService;

    public StudentService(Student student) {
        this.student = student;
        this.auditService = new AuditService();
    }

    public List<Course> filterCourses(@NotNull List<Course> courses) {
        List<Course> filteredCourses = new ArrayList<>();
        for(Course course : courses) {
            if(course.getAccess().equals(AccessType.FREE) ||
                    (course.getAccess().equals(AccessType.PREMIUM) && student instanceof PremiumStudent)) {
                filteredCourses.add(course);
            }
        }
        auditService.logAction("Filter courses");
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
        auditService.logAction("Enroll in course");
        return courseInformation;
    }

    public void increasePoints(int points) {
        student.increasePoints(points);
        var updateMap = new HashMap<String, String>();
        updateMap.put("points", String.valueOf(student.getPoints()));
        StudentDao.getInstance().update(student, updateMap);
        auditService.logAction("Increase points");
    }

    public Student becomePremium() {
        final int PREMIUM_PRICE = 200;
        if(student.getPoints() < PREMIUM_PRICE) {
            return student;
        }
        student.decreasePoints(PREMIUM_PRICE);
        var updateMap = new HashMap<String, String>();
        updateMap.put("points", String.valueOf(student.getPoints()));
        updateMap.put("isPremium", String.valueOf(1));
        StudentDao.getInstance().update(student, updateMap);
        auditService.logAction("Become premium");
        return new PremiumStudent(student);
    }
}
