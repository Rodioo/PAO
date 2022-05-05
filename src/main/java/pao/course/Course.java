package pao.course;

import pao.chapter.Chapter;
import utils.enums.AccessType;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private final long id;
    private String name;
    private String description;
    private String imageUrl;
    private AccessType access;
    private List<Chapter> chapters;

    public Course(String name, String description, String imageUrl, AccessType access) {
        this.id = CourseDao.getInstance().getNextId();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.access = access;
        this.chapters = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AccessType getAccess() {
        return access;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", access=" + access +
                ", chapters=" + chapters +
                '}';
    }
}
