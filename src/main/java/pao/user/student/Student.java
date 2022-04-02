package pao.user.student;

import pao.user.User;

public class Student extends User {

    private int points;

    public Student(String username, String email, String password) {
        super(username, email, password);
        this.points = 0;
    }
}
