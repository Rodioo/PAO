package pao.questionInfo;

import pao.problem.Problem;
import pao.question.Question;
import utils.QuestionDifficulty;

public sealed class QuestionInfo permits Question, Problem {

    private final long id;
    private String text;
    private QuestionDifficulty difficulty;
    private int rewardPoints;

    public QuestionInfo(String text, QuestionDifficulty difficulty) {
        this.id = QuestionInfoDao.getInstance().getNextId();
        this.text = text;
        this.difficulty = difficulty;
        this.rewardPoints = difficulty.getRewardPoints();
    }

    public QuestionInfo(long id, String text, QuestionDifficulty difficulty) {
        this.id = id;
        this.text = text;
        this.difficulty = difficulty;
        this.rewardPoints = this.difficulty.getRewardPoints();
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
}
