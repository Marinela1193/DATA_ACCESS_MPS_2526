package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Menu {

    private String[] args;

    public Menu(String[] args) {
        this.args = args;
    }

    public void start() {

        if (args.length == 0) {
            System.out.print("No arguments to read \n");
            helpScreen();
            return;
        }

        String arg = args[0];
        switch (arg) {
            case "-h":
            case "--help":
                helpScreen();
                break;
            case "-a":
            case "--add":
                if (this.args.length > 1) {
                    addStudentsXMLFile(this.args[1]);
                } else {
                    System.out.println("The filename is mandatory");
                    helpScreen();
                }
                break;
            case "-e":
            case "--enroll":
                enrollStudent();
                break;
            case "-p":
            case "--print":
                printScores();
                break;
            case "-q":
            case "--qualify":
                introScores();
                break;
        }
    }


    public static void helpScreen() {

        System.out.println("Options:");
        System.out.println("-h, --help: show this help");
        System.out.println("-a, --add {filename.xml}: add the students in the XML file to the database.");
        System.out.println("-e, --enroll {studentId} {courseId}: enroll a student in a course");
        System.out.println("-p, --print {studentId} {courseId}: show the scores of a student in a course");
        System.out.println("-q, --qualify {studentId} {courseId}: introduce the scores obtained by the student in the course.");

    }

    public static void addStudentsXMLFile(String filename) {

        //we declare the class that contains the method to read the students
        myXMLStudentsHandler myXMLStudentsHandler = new myXMLStudentsHandler();

        //we delcare a list were the students will be added
        List<Student> studentsXML = myXMLStudentsHandler.readXMLfile(filename);

        //we print the list
        System.out.println("Students list " + studentsXML.size());
        for (Student student : studentsXML) {
            System.out.println(student);
        }

        for (Student student : studentsXML) {
            if (!student.exists()){
                student.addToDatabase(student);
            }else {
                System.out.println("The student already exists in the system");
            }
        }

    }

    public static void enrollStudent() {

        //Create the stored functions
        try(Session session = SessionFactory.getSessionFactory().openSession()) {
            //ask the user the student to enroll
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce the ID of the student you would like to enroll");
            String id = sc.nextLine();
            //we ask in which module you want to enroll the student
            System.out.println("Introduce the course of the student you would like to enroll: 1 for  DAM and 2 for DAW");
            int course = sc.nextInt();

            //check if the student is in enrollments table
            //this query returns all students idcard and courses they are enroll
            Query<Enrollment> checkEnrollment = session.createQuery(
                    "FROM Enrollment e WHERE e.student.idcard = :studentId AND e.course.id = :courseId",
                    Enrollment.class
            );
            checkEnrollment.setParameter("studentId", id);
            checkEnrollment.setParameter("courseId", course);

            //we save the results in a list
            List<Enrollment> previousEnrollments = checkEnrollment.list();

            //if the id exists in DB
            if (previousEnrollments.isEmpty()) {
                //this means the student is not enrolled already in some courses.
                enrollStudent();

                    //we create a new enrollment with the studend ID and in year 1
                    Enrollment enrollment1 = new Enrollment();
                    //in order to introduce the student in the first year
                    //we create the course c in order to assign the year 1
                    Cours c = session.find(Cours.class, course);
                    enrollment1.setStudent(student);
                    enrollment1.setCourse(c);
                    enrollment1.setYear(2025);
                    //we add the student in all courses of 1 year

                    session.persist(enrollment1);

                    //we create a list that will have all subjects from 1st course of the course the user has indicated
                    List<Subject> subjects = session.createQuery(
                                    "SELECT sc.subject FROM SubjectCours sc " +
                                            "WHERE sc.course.id = :courseId AND sc.subject.year = 1",
                                    Subject.class
                            )
                            .setParameter("courseId", course)
                            .getResultList();

                    //we create a for in order to go for all subjects in the list created
                    //and create all subjects for this new enrollment
                    for (Subject s : subjects) {

                        Score score = new Score();
                        score.setEnrollment(enrollment1);
                        score.setSubject(s);
                        score.setScore(null);
                        session.persist(score);
                    }

                    transaction.commit();
                    System.out.println("Successfully added student");

                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }

                    System.out.println(e.getMessage());
                }
            } else {
                //if the student already exists, then, we want to know the year he is in and which subjects has he failed
                // We check within that course if he/she was previously enrolled
                System.out.println("The student is already enrolled. We will see if he/she has pending subjects");

                Transaction transaction2;
                transaction2 = session.beginTransaction();
                try {
                    Student student = session.find(Student.class, id);

                    List<Object[]> passedSubjectsObjects = session.createNativeQuery(
                            "SELECT * FROM subjectsPassed_mps_2526(:studentId)"
                    ).setParameter("studentId", id).list();

                    List<Subject> allCourseSubjects = session.createQuery(
                            "SELECT name FROM Subject s JOIN SubjectCours sc ON s.id = sc.subject.id WHERE sc.course.id = :courseId",
                            Subject.class
                    ).setParameter("courseId", course).getResultList();

                    if (passedSubjectsObjects.size() == allCourseSubjects.size()) {
                        System.out.println("The student has passed everything! Congratulations! You have graduated.");
                    } else {
                        //we obtain the subjectsfailed by this id student
                        List<Object[]> failedSubjectsObjects = session.createNativeQuery(
                                "SELECT * FROM subjectsPending_mps_2526(:studentId)"
                        ).setParameter("studentId", id).list();

                        if (!failedSubjectsObjects.isEmpty()) {

                            //we turn the objects into a list
                            List<Subject> failedSubjects = new ArrayList<>();

                            for (Object[] row : failedSubjectsObjects) {
                                Integer subjectCode = (Integer) row[2];
                                Subject failedSubject = session.find(Subject.class, subjectCode);
                                if (failedSubject != null) {
                                    failedSubjects.add(failedSubject);
                                }
                            }

                            List<Subject> secondYearSubjects = session.createQuery(
                                    "SELECT s FROM Subject s JOIN SubjectCours sc ON s.id = sc.subject.id WHERE sc.course.id = :courseId AND s.year = 2",
                                    Subject.class
                            ).setParameter("courseId", id).getResultList();


                            List<Subject> subjectsToEnroll = new ArrayList<>();
                            subjectsToEnroll.addAll(secondYearSubjects);

                            for (Subject s : failedSubjects) {
                                if (!subjectsToEnroll.contains(s)) {
                                    subjectsToEnroll.add(s);
                                }
                            }

                            Enrollment enrollment2 = new Enrollment();
                            Cours c2 = session.find(Cours.class, course);
                            enrollment2.setStudent(student);
                            enrollment2.setCourse(c2);
                            enrollment2.setYear(2025);

                            session.persist(enrollment2);

                            for (Subject s : subjectsToEnroll) {

                                Score score = new Score();
                                score.setEnrollment(enrollment2);
                                score.setSubject(s);
                                score.setScore(null);
                                session.persist(score);

                                System.out.println("Successfully added student in first year pending subjects and second year");
                            }
                        } else {
                            System.out.println("The student has passed the first year. Enrolling only in second-year subjects.");

                            List<Subject> secondYearSubjects1 = session.createQuery(
                                    "SELECT s FROM Subject s JOIN SubjectCours sc ON s.id = sc.subject.id WHERE sc.course.id = :courseId AND s.year = 2",
                                    Subject.class
                            ).setParameter("courseId", id).getResultList();


                            Enrollment enrollment3 = new Enrollment();
                            Cours c3 = session.find(Cours.class, course);
                            enrollment3.setStudent(student);
                            enrollment3.setCourse(c3);
                            enrollment3.setYear(2025);

                            session.persist(enrollment3);

                            for (Subject s : secondYearSubjects1) {

                                Score score = new Score();
                                score.setEnrollment(enrollment3);
                                score.setSubject(s);
                                score.setScore(null);
                                session.persist(score);
                            }
                        }
                        transaction2.commit();
                        System.out.println("Successfully enrolled student in second year");
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void printScores() {

        try (Session session = SessionFactory.getSessionFactory().openSession()) {
            Scanner sc = new Scanner(System.in);

            //we request the ID of the student that we want to print
            System.out.println("Introduce the ID of the student to see the information");
            String id = sc.nextLine();

            Transaction transaction2;
            transaction2 = session.beginTransaction();

            //We call the student we are about to update the scores
            Student student = (Student) session.find(Student.class, id);

            //we create a query to list all the student's information
            List<Score> studentInfoList = session.createQuery(
                            "SELECT sc " +
                                    "FROM Score sc " +
                                    "JOIN sc.enrollment e " +
                                    "JOIN e.student st " +
                                    "JOIN sc.subject sub " +
                                    "WHERE st.idcard = :studentId " +
                                    "ORDER BY e.year DESC",
                            Score.class
                    ).setParameter("studentId", id)
                    .getResultList();
            //we need to insert that information into a table
            //we create a loop with certain structure so details are added in order
            System.out.println("Year      Subjets                            Score");
            System.out.println("---------------------------------------------------");
            for (Score s : studentInfoList) {
                int year = s.getEnrollment().getYear();
                String subject = s.getSubject().getName();
                int score = s.getScore();
                System.out.println(year + " " + subject + " " + score);
            }
            transaction2.commit();
        }
    }


    public static void introScores() {

        try(Session session = SessionFactory.getSessionFactory().openSession()){
            Scanner sc = new Scanner(System.in);

            //we request the ID of the student that we want to introduce the scores
            System.out.println("Introduce the ID of the student to qualify");
            String id = sc.nextLine();

            Transaction transaction2;
            transaction2 = session.beginTransaction();


            //We call the student we are about to update the scores
            Student student = (Student) session.find(Student.class, id);

            if (student == null) {
                System.out.println("Student with ID " + id + " not found");
                return;
            }

            //We create al ist of the subjects this student is enrolled and
            //that the score is null
            // we create a query
            List<Score> subjectsToScore = session.createQuery(
                            "SELECT sc " +
                                    "FROM Score sc " +
                                    "JOIN sc.enrollment e " +
                                    "JOIN e.student st " +
                                    "WHERE sc.score IS NULL AND st.idcard = :studentId",
                            Score.class
                    ).setParameter("studentId", id)
                    .getResultList();

            if (subjectsToScore.isEmpty()) {
                System.out.println("This student has no pending subjects to qualify.");
                return;
            }

            //Then, we do an update of the scores of those subjects

            for (Score score : subjectsToScore) {

                Subject s = score.getSubject();
                System.out.println("Introduce the score for subject: " + s.getName() + " (0-10 or 99 to skip)");
                //we need to make sure first the score written goes between 0 - 10
                int scoreValue = sc.nextInt();
                //if user writes 99 it means he does not want to qualify that subject yet.
                if (scoreValue == 99) {
                    System.out.println("Skipping subject.");
                    continue;
                }
                if (scoreValue < 0 || scoreValue > 10) {
                    System.out.println("Invalid score. Must be between 0 and 10.");
                    continue;
                }
                score.setScore(scoreValue);
                session.merge(score);

                System.out.println("Score for subject " + s.getName() + " updated correctly.");

            }

            session.getTransaction().commit();
            System.out.println("All scores updated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
