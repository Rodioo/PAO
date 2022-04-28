package pao.teacher;

import pao.course.Course;
import pao.user.User;

public final class Teacher extends User {

    private Course course;

    public Teacher(String username, String email, String password) {
        super(username, email, password);
        this.course = null;
    }

    public Teacher(User user, Course course) {
        super(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
