package pao.question;

import utils.QuestionDifficulty;

import java.util.List;

public final class Question {

    private final long id;
    private String text;
    private QuestionDifficulty difficulty;
    private int rewardPoints;
    private List<String> options;
    private String answer;

    public Question(String text, QuestionDifficulty difficulty, List<String> options, String answer) {
        this.id = QuestionDao.getInstance().getNextId();
        this.text = text;
        this.difficulty = difficulty;
        this.rewardPoints = difficulty.getRewardPoints();
        this.options = options;
        this.answer = answer;
    }

    public Question(long id, String text, QuestionDifficulty difficulty, List<String> options, String answer) {
        this.id = id;
        this.text = text;
        this.difficulty = difficulty;
        this.rewardPoints = difficulty.getRewardPoints();
        this.options = options;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getDifficulty() {
        return difficulty.toString();
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", difficulty=" + difficulty +
                ", rewardPoints=" + rewardPoints +
                ", options=" + options +
                ", answer='" + answer + '\'' +
                '}';
    }
}
