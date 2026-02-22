package org.example.vtschool_mps_2526.models.dto;

public class SubjectCourseDTO {
    private Integer id;
    private SubjectDTO subject;
    private CourseDTO course;

    public SubjectCourseDTO() {
    }

    public SubjectCourseDTO(Integer id, SubjectDTO subject, CourseDTO course) {
        this.id = id;
        this.subject = subject;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
