package pao.user;

import db.AuditService;
import pao.student.Student;
import pao.student.StudentDao;
import pao.teacher.Teacher;
import pao.teacher.TeacherDao;

public class UserService {

    public static long registerUser(String username, String email, String password, String userType) {
        AuditService auditService = new AuditService();
        User user = UserFactory.getUser(username, email, password, userType);
        auditService.logAction("Register user");
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

    public static String receivePassword(String email) {
        AuditService auditService = new AuditService();
        auditService.logAction("Receive password");
        User user = UserDao.getInstance().getByEmail(email);
        if(user == null) {
            return null;
        }
        return user.getPassword();
    }
}
