package pao.courseInformation;


import db.AuditService;
import pao.student.Student;
import pao.student.StudentDao;

import java.util.HashMap;

public class CourseInformationService {

    private final CourseInformation courseInformation;
    private final AuditService auditService;

    public CourseInformationService(CourseInformation courseInformation) {
        this.courseInformation = courseInformation;
        this.auditService = new AuditService();
    }

    public void incrementStudentProgress(int addition) {
        courseInformation.incrementNumberChapter(addition);
        var updateMap = new HashMap<String, String>();
        updateMap.put("numberChapter", String.valueOf(courseInformation.getNumberChapter()));
        CourseInformationDao.getInstance().update(courseInformation, updateMap);
        auditService.logAction("Increment student progress");
    }

    public void finishCourse() {
        Student student = StudentDao.getInstance().getById(courseInformation.getIdStudent());
        student.setCourseInformation(null);
        CourseInformationDao.getInstance().delete(courseInformation);
        auditService.logAction("Finish course");
    }

}
