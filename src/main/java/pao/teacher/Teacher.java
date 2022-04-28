package pao.teacher;

import org.jetbrains.annotations.NotNull;
import pao.course.Course;
import pao.user.User;

import java.util.List;

public final class Teacher extends User {

    private List<Course> course;

    public Teacher(String username, String email, String password) {
        super(username, email, password);
        this.course = null;
    }

    public Teacher(@NotNull User user, List<Course> course) {
        super(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        this.course = course;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }
}
