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

    public void createScore() {
        Transaction transaction = null;
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            Score score = new Score();
            score.setEnrollment(enrollment);
            score.setSubject(subject);
            score.setScore(null);
            session.persist(score);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
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

    public List<Score> getScores(String idCard) {
        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT sc " +
                                    "FROM Score sc " +
                                    "JOIN sc.enrollment e " +
                                    "JOIN e.student st " +
                                    "WHERE sc.score IS NULL AND st.idcard = :studentId",
                            Score.class
                    ).setParameter("studentId", idCard)
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

        Transaction transaction = null;

        for (Score score : scores) {
            Subject s = score.getSubject();
            int scoreValue;

            while(true) {

                System.out.println("Introduce the score for subject: " + s.getName() + " (0-10 or 99 to skip)");
                //we need to make sure first the score written goes between 0 - 10
                scoreValue = sc.nextInt();

                int checkValue = this.checkScorevalue(scoreValue);

                if (checkValue == 99) {
                    continue;
                }
                if (checkValue == -1) {
                    break;
                }

                score.setScore(scoreValue);
                session.merge(score);
                transaction.commit();
                System.out.println("Score for subject " + s.getName() + " updated correctly.");
                break;
            }
            transaction.rollback();
        }
    }
}