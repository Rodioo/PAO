package pao.course;

import pao.chapter.Chapter;
import utils.AccessType;

import java.util.List;

public class Course {

    private final long id;
    private String name;
    private String description;
    private String imageUrl;
    private AccessType access;
    private List<Chapter> chapters;

    public Course(String name, String description, String imageUrl) {
        this.id = CourseDao.getInstance().getNextId();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Course(String name, String description, String imageUrl, AccessType access, List<Chapter> chapters) {
        this.id = CourseDao.getInstance().getNextId();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.access = access;
        this.chapters = chapters;
    }

    public Course(long id, String name, String description, String imageUrl, AccessType access, List<Chapter> chapters) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.access = access;
        this.chapters = chapters;
    }

    public long getId() {
        return id;
    }

    public void setAccess(AccessType access) {
        this.access = access;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
