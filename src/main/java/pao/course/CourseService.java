package pao.course;

import pao.chapter.Chapter;

public class CourseService {

    private final Course course;

    public CourseService(Course course) {
        this.course = course;
    }

    public boolean addChapter(Chapter chapter) {
        if(chapter == null) {
            return false;
        }
        this.course.getChapters().add(chapter);
        return true;
    }

}
