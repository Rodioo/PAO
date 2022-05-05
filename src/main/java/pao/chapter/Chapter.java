package pao.chapter;

import pao.question.Question;
import pao.question.QuestionDao;

import java.util.ArrayList;
import java.util.List;


public class Chapter {

    private final long id;
    private String title;
    private String text;
    private List<Question> questions;

    public Chapter(String title, String text) {
        this.id = QuestionDao.getInstance().getNextId();
        this.title = title;
        this.text = text;
        this.questions = new ArrayList<>();
    }

    public Chapter(String title, String text, List<Question> questions) {
        this.id = QuestionDao.getInstance().getNextId();
        this.title = title;
        this.text = text;
        this.questions = questions;
    }

    public Chapter(long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.questions = new ArrayList<>();
    }

    public Chapter(long id, String title, String text, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", questions=" + questions +
                '}';
    }
}
