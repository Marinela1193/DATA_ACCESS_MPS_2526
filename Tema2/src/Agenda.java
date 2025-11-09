import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    //Creo una lista de contactos
    private List<Contact> contacts = new ArrayList<>();

    //Creo el fichero para escribir la lista
    private final File file = new File("ContactAgenda.dat");

    //metodo para crear el contacto desde el Main
    public void CreateContact(Contact contact) throws IOException, FileNotFoundException, ClassNotFoundException {

        //Nos aseguramos que se haya creado el archivo, si existe lo leemos y leemos la lista. Lo ponemos entre try/catch por errores que nos ha dado la lsita.
        //Tras leer el archivo creamos la lista dentro del archivo y añadimos el contacto que nos vaya a pasar el usaurio desde el main.
        if(file.exists()){
            try (ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file))) {
                contacts = (List<Contact>) inputFile.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                contacts = new ArrayList<>();
            }
        }

        contacts.add(contact);

        //Accedemos al archivo y escribimos el contacto
        ObjectOutputStream objectsFile = new ObjectOutputStream(new FileOutputStream(new File("ContactAgenda.dat")));
        objectsFile.writeObject(contacts);

        System.out.println("Contact added");
    }

    public void ShowContacts() throws IOException, FileNotFoundException, ClassNotFoundException {

        //antes de leer el archivo nos aseguramos que exista y tenga contenido.
        //Accedemos y leemos
        if(file.exists() && file.length() > 0) {
            ObjectInputStream objectsFile = new ObjectInputStream(new FileInputStream(file));
            List<Contact> contacts = (List<Contact>) objectsFile.readObject();
            objectsFile.close();

            //Inicializamos un bucle que recorra todos los contactos y luego los muestre en pantalla.
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
                //Mientras recorremos la lista, vamos viendo que el campo nombre de c es equivalente a alguno que haya
                //en la lista contactos
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

    public void addContacts(List<Contact> importedContacts) throws IOException, ClassNotFoundException {
        if(!file.exists()){
            try (ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file))) {
                contacts = (List<Contact>) inputFile.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                contacts = new ArrayList<>();
            }
        }
    }
}

