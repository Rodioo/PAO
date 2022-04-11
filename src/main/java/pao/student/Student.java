package pao.student;

import org.jetbrains.annotations.NotNull;
import pao.courseInformation.CourseInformation;
import pao.user.User;

public sealed class Student extends User permits PremiumStudent {

    private int points;
    private CourseInformation courseInformation;

    public Student(String username, String email, String password) {
        super(username, email, password);
        this.points = 0;
        this.courseInformation = null;
    }

    public Student(@NotNull User user, int points, CourseInformation courseInformation) {
        super(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        this.points = points;
        this.courseInformation = courseInformation;
    }

    public int getPoints() {
        return points;
    }

    public CourseInformation getCourseInformation() {
        return courseInformation;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                "points=" + points +
                ", courseInformation=" + courseInformation +
                "} ";
    }
}
