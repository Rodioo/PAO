package pao.teacher;

import db.AuditService;
import pao.auxCourse.AuxCourse;
import pao.auxCourse.AuxCourseDao;
import pao.course.Course;
import pao.course.CourseDao;
import utils.enums.AccessType;

public class TeacherService {

    private final Teacher teacher;
    private final AuditService auditService;

    public TeacherService(Teacher teacher) {
        this.teacher = teacher;
        this.auditService = new AuditService();
    }

    public Course createCourse(String name, String description, String imageURL, AccessType accessType) {
        Course course = new Course(name, description, imageURL, accessType);
        teacher.getCourses().add(course);
        CourseDao.getInstance().insert(course);
        AuxCourseDao.getInstance().insert(new AuxCourse(teacher.getId(), course.getId()));
        auditService.logAction("Create course");
        return course;
    }

}
