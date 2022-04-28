package pao.question;

import utils.QuestionDifficulty;
import pao.questionInfo.QuestionInfo;

import java.util.List;

public final class Question extends QuestionInfo {

    private List<String> options;
    private String answer;



    public Question(long id, String text, QuestionDifficulty difficulty) {
        super(id, text, difficulty);
    }
}
