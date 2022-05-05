package pao.auxChapter;

public class AuxChapter {

    private long idCourse;
    private long idChapter;

    public AuxChapter(long idCourse, long idChapter) {
        this.idCourse = idCourse;
        this.idChapter = idChapter;
    }

    public long getIdCourse() {
        return idCourse;
    }

    public long getIdChapter() {
        return idChapter;
    }
}
