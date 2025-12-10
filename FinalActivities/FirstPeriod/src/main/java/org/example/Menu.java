package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;


public class Menu {

    private String[] args;

    public Menu(String[] args) {
        this.args = args;
    }

    public void start() {

        if (args.length == 0) {
            System.err.print("No arguments to read \n");
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
                    String filename = this.args[1];
                    addStudentsXMLFile(filename);
                } else {
                    System.err.println("The filename is mandatory");
                    helpScreen();
                }
                break;
            case "-e":
            case "--enroll":
                if (this.args.length > 2) {
                    String idCard = this.args[1];
                    int courseid  = Integer.parseInt(this.args[2]);
                    enrollStudent(idCard, courseid);
                } else {
                    System.err.println("Two parameters required");
                    helpScreen();
                }
                break;
            case "-p":
            case "--print":
                if (this.args.length > 2) {
                    String idCard = this.args[1];
                    int courseid  = Integer.parseInt(this.args[2]);
                    printScores(idCard, courseid);
                } else {
                    System.err.println("Two parameters required");
                    helpScreen();
                }
                break;
            case "-q":
            case "--qualify":
                if (this.args.length > 2) {
                    String idCard = this.args[1];
                    int courseid  = Integer.parseInt(this.args[2]);
                    introScores(idCard, courseid);
                } else {
                    System.err.println("Two parameters required");
                    helpScreen();
                }
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
        try{
            //we declare the class that contains the method to read the students
            StudentXmlParser myXMLStudentsHandler = new StudentXmlParser();

            //we declare a list were the students will be added
            List<Student> studentsXML = myXMLStudentsHandler.read(filename);

            addListToDataBase(studentsXML);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addListToDataBase(List<Student> studentList) throws RuntimeException {

        try(Session session = SessionFactory.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();

            try {
                for (Student student : studentList) {
                    System.out.println(student);
                    if (student.existsId(student.getIdcard())) {
                        throw new RuntimeException("IDCARD:  " + student.getIdcard() + " is already in the system.");
                    }

                    if (!student.checkEmail()) {
                        throw new RuntimeException("IDCARD:  " + student.getIdcard() + " has an invalid email");

                    }

                    if (student.getPhone() == null){
                        throw new RuntimeException("IDCARD:  " + student.getIdcard() + " does not have a phone number included, it is compulsory.");
                    }

                    if(!student.checkPhoneNumber()) {
                        throw new RuntimeException("IDCARD:  " + student.getIdcard() + " has an invalid phone number");

                    }
                    session.persist(student);
                }
            }catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                throw new RuntimeException("No students added to the system");
            }
            transaction.commit();

            System.out.println(studentList.size() + " Student(s) added correctly");
        }
    }

    public static void printScores(String idCard, int idCourse) {

        try (Session session = SessionFactory.getSessionFactory().openSession()) {

            //check that the IDCard is valid
            Student student = new Student();
            student.setIdcard(idCard);
            if (!student.checkIdCard()) {
                System.err.println("IDCARD:  " + idCard + " is invalid, it must have 8 characters.");
                return;
            }
            //check the IDCard exists
            if (!student.existsId(idCard)) {
                System.err.println("IDCARD:  " + idCard + " does not exist in the system");
                return;
            }

            if (student.getPhone() == null){
                throw new RuntimeException("IDCARD:  " + student.getIdcard() + " does not have a phone number included, it is compulsory.");
            }
            //check the IDCours exists
            Cours course = new Cours();
            if (!course.checkCourse(idCourse)) {
                System.err.println("IDCourse: " + idCourse + " does not exist in the system.");
                return;
            }

            List<Score> studentInfoList = student.studentInfo(idCard, idCourse);
            if (studentInfoList.isEmpty()) {
                System.out.println("No scores found for student " + idCard + " in course " + idCourse);
            } else {
                Score scores = new Score();
                scores.printScores(studentInfoList);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enrollStudent(String idCard, int idCourse) {
        /*The -e / --enroll option will enroll an existing student (idCard must be provided)
        in an existing course. */
        Transaction transaction = null;
        try (Session session = SessionFactory.getSessionFactory().openSession()) {

            //check that the IDCard is valid
            Student student = new Student().getStudentByIdcard(idCard);
            Cours course = new Cours().getCourseById(idCourse);

            if (student == null) {
                System.err.println("IDCARD: " + idCard + " does not exist in the system.");
                return;
            }

            if (student.getPhone() == null){
                throw new RuntimeException("IDCARD:  " + student.getIdcard() + " does not have a phone number included, it is compulsory.");
            }

            if (course == null) {
                System.err.println("IDCourse: " + idCourse + " does not exist in the system.");
                return;
            }

            //check table enrollments has id and course
            Enrollment enrollmentNew = new Enrollment();
            Subject subject = new Subject();
            Score score = new Score();
            transaction = session.beginTransaction();

            Enrollment enrollment = enrollmentNew.getEnrollment(session, idCard, idCourse);

            if (enrollment == null)  {
                System.err.println("IDCard: " + idCard + " does not exist in the Course: " + idCourse + " we will proceed to enroll the student in the first year");

                enrollment = enrollmentNew.createEnrollment(session, student, course, 2025);

                List<Subject> subjectsToEnroll = subject.getSubjectsFirstYear(idCourse);

                for (Subject s : subjectsToEnroll) {
                    score.createScore(session, enrollment, s);
                    System.out.println("Score(s): " + subjectsToEnroll.size() + " created successfully");

                }
            } else {

                if(enrollment.getYear() == 2024 && subject.getSubjectsFailed(idCard).isEmpty()){
                    System.out.println("The student has already passed the course, we cannot enroll again");
                    return;
                }
                /*  List<Subject> passedSubjects = subject.getSubjectsPassed(idCard);
                if (passedSubjects.size() == 5 && enroll.getYear()==2024) {
                    System.out.println("The student has already passed the course, we cannot enroll again");
                    return;
                }*/
                StringBuilder sb = new StringBuilder();

                //System.out.println("The student " + idCard + " is already enrolled in course: " + idCourse + " ,we will proceed with failed and second year subjects");

                List<Subject> secondYearSubjects = subject.getSubjectsSecondYear(idCourse);
                for (Subject s : secondYearSubjects) {
                    score.enrollInSubject(session, enrollment, s);
                    sb.append(secondYearSubjects.size() + " score(s) created successfully\n");
                }
                List<Subject> failedSubjects = subject.getSubjectsFailed(idCard);
                if(!failedSubjects.isEmpty()){
                    sb.append("The student has pending subjects from the first year, we will enroll in them for the new course\n");
                    for (Subject s : failedSubjects) {
                        score.enrollInSubject(session, enrollment, s);
                        sb.append(secondYearSubjects.size() + " score(s) created successfully\n");
                    }

                }
                sb.append("The student has been enrolled in the subjects failed and 2nd year subjects\n");

            }
            transaction.commit();

        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error during enrollment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void introScores (String idCard,int idCourse){

        Student student = new Student();
        student.setIdcard(idCard);
        if (!student.checkIdCard()) {
            System.err.println("IDCARD:  " + idCard + " is invalid, it must have 8 characteres.");
            return;
        }
        //check the IDCard exists
        if (!student.existsId(idCard)) {
            System.err.println("IDCARD:  " + idCard + " does not exist in the system");
        }

        //check the IDCours exists
        Cours course = new Cours();
        if (!course.checkCourse(idCourse)) {
            System.err.println("IDCourse: " + idCourse + " does not exist in the system.");
        }

        try (Session session = SessionFactory.getSessionFactory().openSession()) {
//            Scanner sc = new Scanner(System.in);

            //We create a list of the subjects this student is enrolled and
            Score score = new Score();
            List<Score> scoresStudent = score.getScores(idCard,idCourse);

            if(scoresStudent.isEmpty()){
                System.out.println("The student has no scores to be updated");
                return;
            }
            score.addScores(session, scoresStudent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


