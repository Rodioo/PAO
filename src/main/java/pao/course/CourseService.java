package pao.course;

import pao.auxChapter.AuxChapter;
import pao.auxChapter.AuxChapterDao;
import pao.auxCourse.AuxCourseDao;
import pao.chapter.Chapter;
import pao.chapter.ChapterDao;
import pao.teacher.Teacher;
import pao.teacher.TeacherDao;
import utils.exceptions.TeacherNotFoundException;

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

    public String getCourseTeacherName() {
        long teacherId = AuxCourseDao.getInstance().getTeacherIdByCourseId(course.getId());
        if(teacherId == -1)
            throw new TeacherNotFoundException("Course with id " + course.getId() + " does not have a teacher.");
        Teacher teacher = TeacherDao.getInstance().getById(teacherId);
        return teacher.getUsername();
    }

    public int getCourseNumberOfChapters() {
        return course.getChapters().size();
    }

    public int getCourseNumberOfQuestions() {
        int numOfQuestions = 0;
        for(Chapter chapter : course.getChapters()) {
            numOfQuestions += chapter.getQuestions().size();
        }
        return numOfQuestions;
    }
}
