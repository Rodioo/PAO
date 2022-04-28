package pao.student;

import pao.courseInformation.CourseInformation;
import pao.user.User;

public final class PremiumStudent extends Student{

    public PremiumStudent(String username, String email, String password) {
        super(username, email, password);
    }

    public PremiumStudent(User user, int points, CourseInformation courseInformation) {
        super(user, points, courseInformation);
    }

    @Override
    public String toString() {
        return "PremiumStudent{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", points='" + getPoints() + '\'' +
                ", courseInformation='" + getCourseInformation() + '\'' +
                '}';
    }
}