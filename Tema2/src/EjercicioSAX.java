import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class EjercicioSAX extends DefaultHandler {
    protected String tagContent;

    private static List<Contact> contactList = new ArrayList<>();
    private Contact tempContact;

    public static List<Contact> getContactList(){
        return contactList;
    }
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.equalsIgnoreCase("contact")) {
            tempContact = new Contact();
        }
    }

    public void characters (char ch[], int start, int lenght) throws SAXException {
        tagContent = new String (ch, start, lenght);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(!qName.isBlank() ) {
            if (!qName.equalsIgnoreCase("contact")) {
                System.out.println(" " +  qName + ": " +  tagContent);
            }
            else if(qName.equalsIgnoreCase("name")) {
                tempContact.setName(tagContent.trim());
            }
            else if(qName.equalsIgnoreCase("surname")) {
                tempContact.setSurname(tagContent.trim());
            }
            else if(qName.equalsIgnoreCase("email")) {
                tempContact.setEmail(tagContent.trim());
            }
            else if(qName.equalsIgnoreCase("phone")) {
                tempContact.setPhone(Integer.parseInt(tagContent.trim()));
            }
            else if(qName.equalsIgnoreCase("description")) {
                tempContact.setDescription(tagContent.trim());
            }
        }
    }
}
