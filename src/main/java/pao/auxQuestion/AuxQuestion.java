package pao.auxQuestion;

public class AuxQuestion {

    private long idChapter;
    private long idQuestion;

    public AuxQuestion(long idChapter, long idQuestion) {
        this.idChapter = idChapter;
        this.idQuestion = idQuestion;
    }

    public long getIdChapter() {
        return idChapter;
    }

    public long getIdQuestion() {
        return idQuestion;
    }
}
