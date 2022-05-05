package pao.course;

import pao.auxChapter.AuxChapter;
import pao.auxChapter.AuxChapterDao;
import pao.chapter.Chapter;
import pao.chapter.ChapterDao;

public class CourseService {

    private final Course course;

    public CourseService(Course course) {
        this.course = course;
    }

    public Chapter createChapter(String title, String text) {
        Chapter chapter = new Chapter(title, text);
        course.getChapters().add(chapter);
        ChapterDao.getInstance().insert(chapter);
        AuxChapterDao.getInstance().insert(new AuxChapter(course.getId(), chapter.getId()));
        return chapter;
    }

}
