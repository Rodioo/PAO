package pao.user;

import java.util.concurrent.atomic.AtomicLong;

public abstract class User {

    private final long id;
    private static final AtomicLong counter = new AtomicLong(0);
    private String username;
    private final String email;
    private String password;

    public User(String username, String email, String password) {
        this.id = counter.incrementAndGet();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
