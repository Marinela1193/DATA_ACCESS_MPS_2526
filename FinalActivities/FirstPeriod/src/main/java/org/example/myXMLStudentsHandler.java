package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class myXMLStudentsHandler extends DefaultHandler {
    protected String tagContent;
    //creamos lista donde guardaremos estudiantes que leamos del XML para luego hacer insert?
    List<Student> studentsToAdd = new ArrayList<>();
    // Tag opening found

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        if (qName.equals("student")) {
            System.out.println(": " + attributes.getValue("id"));
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
        if (!qName.isBlank()) {
            switch  (qName){
                case "student":
                    System.out.println(": ");
                    break;
                    case "idCard":
                        System.out.println("idCard: ");
                        break;
                        case "firstName":
                            System.out.println("firstName: ");
                            break;
                            case "lastName":
                                System.out.println("lastName: ");
                                break;
                                case "phone":
                                    System.out.println("phone: ");
                                    break;
                                    case "email":
                                        System.out.println("email: ");
                                        break;
            }
        }
    }


    public static void readXMLfile() {
        System.out.println("Adding students XML file...");

        try {
            SAXParser saxParser = SAXParserFactory.
                    newInstance().newSAXParser();
            saxParser.parse("students.xml", new
                    myXMLStudentsHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
