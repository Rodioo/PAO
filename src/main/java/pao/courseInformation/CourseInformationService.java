package pao.courseInformation;


import pao.student.Student;
import pao.student.StudentDao;

import java.util.HashMap;

public class CourseInformationService {

    private final CourseInformation courseInformation;

    public CourseInformationService(CourseInformation courseInformation) {
        this.courseInformation = courseInformation;
    }

    public void incrementStudentProgress(int addition) {
        courseInformation.incrementNumberChapter(addition);
        var updateMap = new HashMap<String, String>();
        updateMap.put("numberChapter", String.valueOf(courseInformation.getNumberChapter()));
        CourseInformationDao.getInstance().update(courseInformation, updateMap);
    }

    public void finishCourse() {
        Student student = StudentDao.getInstance().getById(courseInformation.getIdStudent());
        student.setCourseInformation(null);
        CourseInformationDao.getInstance().delete(courseInformation);
    }

}
