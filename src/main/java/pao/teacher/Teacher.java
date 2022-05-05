package pao.teacher;

import org.jetbrains.annotations.NotNull;
import pao.course.Course;
import pao.user.User;

import java.util.ArrayList;
import java.util.List;

public final class Teacher extends User {

    private List<Course> courses;

    public Teacher(String username, String email, String password) {
        super(username, email, password);
        this.courses = new ArrayList<>();
    }

    public Teacher(@NotNull User user, List<Course> course) {
        super(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        this.courses = course;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourse(List<Course> courses) {
        this.courses = courses;
    }
}
