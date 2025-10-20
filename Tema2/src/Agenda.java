import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    //Accedo a la lista contactos en el main

    private List<Contact> contacts = new ArrayList<>();
    private final File file = new File("ContactAgenda.dat");

    public void CreateContact(Contact contact) throws IOException, FileNotFoundException, ClassNotFoundException {

        if(file.exists()){
            try (ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file))) {
                contacts = (List<Contact>) inputFile.readObject();
            } catch (EOFException | ClassNotFoundException e) {

                contacts = new ArrayList<>();
            }
        }

        contacts.add(contact);

        ObjectOutputStream objectsFile = new ObjectOutputStream(new FileOutputStream(new File("ContactAgenda.dat")));
        objectsFile.writeObject(contacts);

        System.out.println("Contact added");
    }

    public void ShowContacts() throws IOException, FileNotFoundException, ClassNotFoundException {

        if(file.exists() && file.length() > 0) {
            ObjectInputStream objectsFile = new ObjectInputStream(new FileInputStream(file));
            List<Contact> contacts = (List<Contact>) objectsFile.readObject();
            objectsFile.close();

            for (Contact c : contacts) {

                System.out.println(c.toString());
            }
        }
        else {
            System.out.println("There are no contacts in the list");
        }
    }

    public void SearchContact (String name) throws IOException, FileNotFoundException, ClassNotFoundException {

        if(file.exists() && file.length() > 0) {
            ObjectInputStream objectsFile = new ObjectInputStream(new FileInputStream(file));
            List<Contact> contacts = (List<Contact>) objectsFile.readObject();
            objectsFile.close();

            boolean found = false;

            for (Contact c : contacts) {

                if (c.name.equalsIgnoreCase(name)) {
                    System.out.println("Contacto encontrado");
                    System.out.println(c.toString());
                    found = true;
                    break;
                }

            }
            if(!found){
                System.out.println("Contacto no encontrado");
            }
        }else{
            System.out.println("The list is empty");
        }
    }
}

