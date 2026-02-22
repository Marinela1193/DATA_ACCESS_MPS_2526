package org.example.vtschool_mps_2526.models.dto;

import java.util.Set;

public class EnrollmentDTO {
    private Integer id;
    private CourseDTO course;
    private Integer year;
    private Set<ScoreDTO> scores;

    public EnrollmentDTO() {
    }

    public EnrollmentDTO(Integer id, CourseDTO course, Integer year, Set<ScoreDTO> scores) {
        this.id = id;
        this.course = course;
        this.year = year;
        this.scores = scores;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<ScoreDTO> getScores() {
        return scores;
    }

    public void setScores(Set<ScoreDTO> scores) {
        this.scores = scores;
    }
}
