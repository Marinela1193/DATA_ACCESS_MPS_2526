package org.example.vtschool_mps_2526.models.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "courses", schema = "_da_vtschool_2526")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 90)
    private String name;

    @OneToMany(mappedBy = "course")
    private Set<EnrollmentEntity> enrollmentEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<SubjectCourseEntity> subjectCourseEntities = new LinkedHashSet<>();

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

    public Set<EnrollmentEntity> getEnrollments() {
        return enrollmentEntities;
    }

    public void setEnrollments(Set<EnrollmentEntity> enrollmentEntities) {
        this.enrollmentEntities = enrollmentEntities;
    }

    public Set<SubjectCourseEntity> getSubjectCourses() {
        return subjectCourseEntities;
    }

    public void setSubjectCourses(Set<SubjectCourseEntity> subjectCourseEntities) {
        this.subjectCourseEntities = subjectCourseEntities;
    }

}