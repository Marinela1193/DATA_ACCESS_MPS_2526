package org.example;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

@Entity
@Table(name = "enrollments", schema = "_da_vtschool_2526")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course", nullable = false)
    private Cours course;

    @Column(name = "year", nullable = false)
    private Integer year;

    @OneToMany(mappedBy = "enrollment")
    private Set<Score> scores = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Cours getCourse() {
        return course;
    }

    public void setCourse(Cours course) {
        this.course = course;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public boolean checkEnrollment(String idCard, int courseCode) {
        try(Session session = SessionFactory.getSessionFactory().openSession()) {
            Long count = session.createQuery(
                            "SELECT COUNT(e) FROM Enrollment e " +
                                    "WHERE e.student.idcard = :studentId " +
                                    "AND e.course.id = :courseId",
                            Long.class
                    )
                    .setParameter("studentId", idCard)
                    .setParameter("courseId", courseCode)
                    .uniqueResult();

            return count != null && count > 0;

        }
    }

    public Enrollment createEnrollment(Session session, Student student, Cours course, int year) {

            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setYear(year);
            session.persist(enrollment);
            return enrollment;
    }

    public Enrollment getEnrollment(Session session, String idCard, int courseId) {
        return session.createQuery(
                        "FROM Enrollment e " +
                                "WHERE e.student.idcard = :studentId AND e.course.id = :courseId",
                        Enrollment.class)
                .setParameter("studentId", idCard)
                .setParameter("courseId", courseId)
                .uniqueResult();
    }
}