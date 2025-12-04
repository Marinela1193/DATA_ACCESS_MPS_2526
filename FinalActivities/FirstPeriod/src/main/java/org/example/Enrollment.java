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

    public void enrollStudent(Student student) {

        Transaction transaction;
        Scanner sc = new Scanner(System.in);

        try (Session session = SessionFactory.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            student = new Student();
            student.setIdcard(getStudent().getIdcard());
            System.out.println("What is the name of the student?");
            String name = sc.nextLine();
            student.setFirstname(name);
            System.out.println("What is the last name of the student?");
            String lastName = sc.nextLine();
            student.setLastname(lastName);
            System.out.println("What is the email of the student?");
            String email = sc.nextLine();
            student.setEmail(email);
            System.out.println("What is the phone of the student?");
            String phone = sc.nextLine();
            student.setPhone(phone);

            session.persist(student);
        }
    }
}