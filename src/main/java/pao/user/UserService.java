package pao.user;

import pao.student.Student;
import pao.student.StudentDao;
import pao.teacher.Teacher;
import pao.teacher.TeacherDao;

public class UserService {

    public static long registerUser(String username, String email, String password, String userType) {
        User user = UserFactory.getUser(username, email, password, userType);
        if(user == null) {
            return -1;
        }
        else if(user instanceof Student) {
            return StudentDao.getInstance().insert((Student) user);
        }
        else if(user instanceof Teacher) {
            return TeacherDao.getInstance().insert((Teacher) user);
        }
        return -1;
    }
}
