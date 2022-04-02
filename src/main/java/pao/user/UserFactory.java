package pao.user;

import pao.user.student.Student;
import pao.user.teacher.Teacher;

public class UserFactory {

    public static User getUser(String username, String email, String password, String userType) {
        if(userType == null) {
            return null;
        }
        else if(userType.equals("STUDENT")) {
            return new Student(username, email, password);
        }
        else if(userType.equals("TEACHER")) {
            return new Teacher(username, email, password);
        }

        return null;
    }
}
