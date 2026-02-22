package org.example.vtschool_mps_2526.models.dto;

public class ScoreDTO {
    private Integer id;
    private SubjectDTO subject;
    private Integer score;

    public ScoreDTO() {
    }

    public ScoreDTO(Integer id, SubjectDTO subject, Integer score) {
        this.id = id;
        this.subject = subject;
        this.score = score;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
