package pao.course;

import pao.chapter.Chapter;
import utils.AccessType;

import java.util.List;

public class Course {

    private final long id;
    private String name;
    private String imageUrl;
    private String description;
    private AccessType access;
    private List<Chapter> chapters;

    public Course(String name, String imageUrl, String description, AccessType access, List<Chapter> chapters) {
        this.id = CourseDao.getInstance().getNextId();
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.access = access;
        this.chapters = chapters;
    }

    public Course(long id, String name, String imageUrl, String description, AccessType access, List<Chapter> chapters) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.access = access;
        this.chapters = chapters;
    }

    public long getId() {
        return id;
    }
}
