package org.example;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "scores", schema = "_da_vtschool_2526")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "score")
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void createScore(Session session, Enrollment enrollment, Subject subject) {
        try {
            Score score = new Score();
            score.setEnrollment(enrollment);
            score.setSubject(subject);
            score.setScore(null);
            session.persist(score);

        } catch (Exception e) {

            System.err.println("Error creating score: " + e.getMessage());
        }

    }

    public void printScores(List<Score> scores){
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            System.out.println("Year                 Subjets                 Score");
            System.out.println("---------------------------------------------------");
            for (Score s : scores) {
                int year = s.getEnrollment().getYear();
                String subject = s.getSubject().getName();
                int score = s.getScore();
                System.out.println(year + "           " + subject + " " + score);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Score> getScores(String idCard, int idCourse) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT sc " +
                                    "FROM Score sc " +
                                    "JOIN FETCH sc.enrollment e " +
                                    "JOIN FETCH sc.subject s " +
                                    "JOIN FETCH e.student st " +
                                    "JOIN FETCH e.course c " +
                                    "WHERE sc.score IS NULL AND st.idcard = :studentId AND c.id = :courseId",
                            Score.class
                    ).setParameter("studentId", idCard)
                    .setParameter("courseId", idCourse)
                    .getResultList();
        }
    }

    public int checkScorevalue(int value) {
        if (value == 99) {
            System.out.println("Skipping subject.");
            return 99;
        }
        if (value < 0 || value > 10) {
            System.out.println("Invalid score. Must be between 0 and 10.");
            return -1;
        }
        return value;
    }

    public void addScores(Session session, List<Score> scores) {
        Scanner sc = new Scanner(System.in);

        Transaction transaction = session.beginTransaction();

        try {
            for (Score score : scores) {

                Subject s = score.getSubject();
                int scoreValue;

                while (true) {

                    System.out.println("Introduce the score for subject: " + s.getName() + " (0-10 or 99 to skip)");
                    //we need to make sure first the score written goes between 0 - 10
                    scoreValue = sc.nextInt();

                    int checkValue = this.checkScorevalue(scoreValue);

                    if (checkValue == 99) {
                        break;
                    }
                    if (checkValue == -1) {
                        continue;
                    }

                    score.setScore(scoreValue);
                    session.merge(score);
                    System.out.println("Score for subject " + s.getName() + " updated correctly.");
                    break;
                }
            }

            transaction.commit();
            System.out.println("All scores added correctly");

        }catch (Exception e){
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error updating scores", e);
        }
    }

    public void enrollInSubject(Session session, Enrollment enrollment, Subject subject) {
        try {
            Long count = session.createQuery(
                            "SELECT COUNT(s) FROM Score s " +
                                    "WHERE s.enrollment.id = :enrollmentId " +
                                    "AND s.subject.id = :subjectId",
                            Long.class
                    )
                    .setParameter("enrollmentId", enrollment.getId())
                    .setParameter("subjectId", subject.getId())
                    .uniqueResult();

            if (count == null || count == 0) {
                Score score = new Score();
                score.setEnrollment(enrollment);
                score.setSubject(subject);
                score.setScore(null);
                session.persist(score);
                System.out.println("Enrolled student in subject: " + subject.getName());

            } else {
                System.out.println("Student already enrolled in subject: " + subject.getName());
            }

        } catch (Exception e) {
            System.err.println("Error during subject enrollment: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}