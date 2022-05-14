package pao.courseInformation;


public class CourseInformation {

    private final long id;
    private long idStudent;
    private long idCourse;
    private int numberChapter;

    public CourseInformation(long idStudent, long idCourse) {
        this.id = CourseInformationDao.getInstance().getNextId();
        this.idStudent = idStudent;
        this.idCourse = idCourse;
        this.numberChapter = 0;
    }

    public CourseInformation(long id, long idStudent, long idCourse, int numberChapter) {
        this.id = id;
        this.idStudent = idStudent;
        this.idCourse = idCourse;
        this.numberChapter = numberChapter;
    }

    public long getId() {
        return id;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public long getIdCourse() {
        return idCourse;
    }

    public int getNumberChapter() {
        return numberChapter;
    }

    public void incrementNumberChapter(int addition) {
        this.numberChapter += addition;
    }

    @Override
    public String toString() {
        return "CourseInformation{" +
                "id=" + id +
                ", idStudent=" + idStudent +
                ", idCourse=" + idCourse +
                ", numberChapter=" + numberChapter +
                '}';
    }
}
