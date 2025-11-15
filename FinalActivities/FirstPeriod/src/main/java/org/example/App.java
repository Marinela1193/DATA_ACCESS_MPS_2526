package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class App {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }

        return sessionFactory;
    }
    public static void main(String[] args) {

        if(args.length ==0){
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
                    addStudentsXMLFile();
                    break;
            case "-e":
            case "--enroll":
                enrollStuden();
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

            Scanner sc = new Scanner(System.in);
            System.out.println("Options:");
            System.out.println("-h, --help: show this help");
            System.out.println("-a, --add {filename.xml}: add the students in the XML file to the database.");
            System.out.println("-e, --enroll {studentId} {courseId}: enroll a student in a course");
            System.out.println("-p, --print {studentId} {courseId}: show the scores of a student in a course");
            System.out.println("-q, --qualify {studentId} {courseId}: introduce the scores obtained by the student in the course.");

    }

    public static void addStudentsXMLFile() {
        System.out.println("Adding students XML file...");

        InputStream xml = App.class.getClassLoader().getResourceAsStream("students.xml");
        if (xml == null) {
            System.err.println("Error: The file students.xml cannot be found or is empty");
            return;
        }
        try(Session session = getSessionFactory().openSession()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("student");


            Transaction transaction;
            transaction = session.beginTransaction();

            List<Student> studentsDb = session
                    .createQuery("FROM Student", Student.class)
                    .list();

            int count = 0;

            for  (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Node idNode = element.getElementsByTagName("idcard").item(0);
                    Node nameNode = element.getElementsByTagName("firstname").item(0);
                    Node lastNameNode = element.getElementsByTagName("lastname").item(0);
                    Node emailNode = element.getElementsByTagName("email").item(0);
                    Node phoneNode = element.getElementsByTagName("phone").item(0);

                    if (idNode == null || nameNode == null || lastNameNode == null) {
                        System.out.println("Estudiante con datos obligatorios incompletos, se omite");
                        continue;
                    }

                    String idCard = idNode.getTextContent();
                    String firstName = nameNode.getTextContent();
                    String lastName = lastNameNode.getTextContent();
                    String email = emailNode != null ? emailNode.getTextContent() : null;
                    Integer phone = phoneNode != null ? Integer.parseInt(phoneNode.getTextContent()) : null;

                    boolean exists = false;
                    for (Student s : studentsDb) {
                        if (s.getIdcard().equals(idCard)) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists) {
                        System.out.println("Student with IDCard:" + idCard + " already exists");
                        continue;
                    }

                    Student student = new Student();
                    student.setIdcard(idCard);
                    student.setFirstname(firstName);
                    student.setLastname(lastName);
                    student.setEmail(email);
                    student.setPhone(String.valueOf(phone));

                    session.persist(student);
                    count++;
                }
            }
            transaction.commit();
            if(count>0) {
                System.out.println("Successfully added students");
            }
            else{
                System.out.println("Error adding students, try again with students that are not already in the system");
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }finally {
            sessionFactory.close();
        }
    }

    public static void enrollStuden() {

    }

    public static void printScores() {

    }

    public static void introScores() {

    }
}