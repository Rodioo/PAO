package pao.teacher;

import pao.auxCourse.AuxCourse;
import pao.auxCourse.AuxCourseDao;
import pao.course.Course;
import pao.course.CourseDao;
import utils.AccessType;

public class TeacherService {

    private final Teacher teacher;

    public TeacherService(Teacher teacher) {
        this.teacher = teacher;
    }

    //TODO: AuxCourseDao for inserting idTeacher and idCourse
    public Course createCourse(String name, String description, String imageURL, AccessType accessType) {
        Course course = new Course(name, description, imageURL, accessType);
        teacher.getCourses().add(course);
        CourseDao.getInstance().insert(course);
        AuxCourseDao.getInstance().insert(new AuxCourse(teacher.getId(), course.getId()));
        return course;
    }

}
