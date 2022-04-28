package pao.problem;

import pao.questionInfo.QuestionInfo;
import utils.QuestionDifficulty;

public final class Problem extends QuestionInfo {


    public Problem(String text, QuestionDifficulty difficulty) {
        super(text, difficulty);
    }

    public Problem(long id, String text, QuestionDifficulty difficulty) {
        super(id, text, difficulty);
    }
}
