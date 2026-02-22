package org.example.vtschool_mps_2526.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "scores", schema = "_da_vtschool_2526")
public class ScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private EnrollmentEntity enrollmentEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectEntity subjectEntity;

    @Column(name = "score")
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EnrollmentEntity getEnrollment() {
        return enrollmentEntity;
    }

    public void setEnrollment(EnrollmentEntity enrollmentEntity) {
        this.enrollmentEntity = enrollmentEntity;
    }

    public SubjectEntity getSubject() {
        return subjectEntity;
    }

    public void setSubject(SubjectEntity subjectEntity) {
        this.subjectEntity = subjectEntity;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}