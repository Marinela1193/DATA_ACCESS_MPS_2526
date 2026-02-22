package org.example.vtschool_mps_2526.models.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "subjects", schema = "_da_vtschool_2526")
public class SubjectEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "year")
    private Integer year;

    @Column(name = "hours")
    private Integer hours;

    @OneToMany(mappedBy = "subjectEntity")
    private Set<ScoreEntity> scoreEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "subjectEntity")
    private Set<SubjectCourseEntity> subjectCoursEntities = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Set<ScoreEntity> getScores() {
        return scoreEntities;
    }

    public void setScores(Set<ScoreEntity> scoreEntities) {
        this.scoreEntities = scoreEntities;
    }

    public Set<SubjectCourseEntity> getSubjectCourses() {
        return subjectCoursEntities;
    }

    public void setSubjectCourses(Set<SubjectCourseEntity> subjectCoursEntities) {
        this.subjectCoursEntities = subjectCoursEntities;
    }

}