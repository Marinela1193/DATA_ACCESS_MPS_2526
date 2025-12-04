package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class myXMLStudentsHandler extends DefaultHandler {

    protected String tagContent;
    //we create the list were we are going to add the students of the XML file
    private List<Student> studentsToAdd;
    //we create the object Student so we can save here every student of the XML file
    private Student currentStudent;

    //we add the constructor
    public myXMLStudentsHandler() {
        this.studentsToAdd = new ArrayList<>();
        this.currentStudent = null;
    }
    //we delcare the getters and setters for the list to access it
    public List<Student> getStudentsToAdd() {
        return studentsToAdd;
    }

    public void setStudentsToAdd(List<Student> studentsToAdd) {
        this.studentsToAdd = studentsToAdd;
    }
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        if (qName.equals("student")) {
            currentStudent = new Student();
        }
    }
    // Tag content, usually CDATA

    public void characters(char ch[], int start, int length)
            throws SAXException {
        tagContent = new String(ch, start, length);
    }
    // Tag ending

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (currentStudent != null) {
            switch  (qName.toUpperCase()) {
                case "idCard":
                    currentStudent.setIdcard(tagContent);
                    break;
                case "firstName":
                    currentStudent.setFirstname(tagContent);
                    break;
                case "lastName":
                    currentStudent.setLastname(tagContent);
                    break;
                case "phone":
                    currentStudent.setPhone(tagContent);
                    break;
                case "email":
                    currentStudent.setEmail(tagContent);
                    break;
                case "student":
                    System.out.println(currentStudent);
                    studentsToAdd.add(currentStudent);
                    currentStudent = null;
                default:
            }
        }
    }


    List<Student> readXMLfile(String file) {

        try {
            SAXParser saxParser = SAXParserFactory.
                    newInstance().newSAXParser();

            saxParser.parse(file, this);
            return getStudentsToAdd();
        }catch (FileNotFoundException e){
            System.err.println("File not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getStudentsToAdd();
    }
}
