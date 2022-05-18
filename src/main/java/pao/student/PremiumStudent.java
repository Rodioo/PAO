package pao.student;

import pao.courseInformation.CourseInformation;
import pao.user.User;

public final class PremiumStudent extends Student{

    public PremiumStudent(User user, int points, CourseInformation courseInformation) {
        super(user, points, courseInformation);
    }

    public PremiumStudent(Student student) {
        super(student.getId(), student.getUsername(), student.getEmail(),
                student.getPassword(), student.getPoints(), student.getCourseInformation());
    }

    @Override
    public String toString() {
        return "PremiumStudent{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", points='" + points + '\'' +
                ", courseInformation='" + courseInformation + '\'' +
                '}';
    }
}
