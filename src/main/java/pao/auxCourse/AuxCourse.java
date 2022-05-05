package pao.auxCourse;

public class AuxCourse {

    private long idTeacher;
    private long idCourse;

    public AuxCourse(long idTeacher, long idCourse) {
        this.idTeacher = idTeacher;
        this.idCourse = idCourse;
    }

    public long getIdTeacher() {
        return idTeacher;
    }

    public long getIdCourse() {
        return idCourse;
    }
}
