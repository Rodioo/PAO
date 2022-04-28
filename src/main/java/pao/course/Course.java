package pao.course;

public class Course {

    private final long id;

    public Course() {
        this.id = CourseDao.getInstance().getNextId();
    }

    public Course(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
