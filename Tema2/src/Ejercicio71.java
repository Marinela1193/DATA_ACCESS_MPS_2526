
/*Write a program to create a contact list. First of all, you will need a class
Contact, with the following information: name, surname, e-mail, phone
number, description. The program must permit to create new contacts,
show the current list of contacts and search for a contact by its full name
or phone. Data must be stored and retrieved in a file named contacts.obj. if it
doesn't exist, it must be created*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio71 {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {


        Agenda agenda = new Agenda();
        Scanner sc = new Scanner(System.in);
        int option;

        do{
            System.out.println("MENÚ");
            System.out.println("1. Crear nuevo contacto");
            System.out.println("2. Mostrar lista contactos");
            System.out.println("3. Buscar un contacto");
            System.out.println("4. Salir");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Introduce the name of the contact");
                    String name = sc.nextLine();
                    System.out.println("Introduce the surname of the contact");
                    String surname = sc.nextLine();
                    System.out.println("Introduce the email of the contact");
                    String email = sc.nextLine();
                    System.out.println("Introduce the phone number of the contact");
                    int phone = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introduce a description for the contact");
                    String description = sc.nextLine();

                    Contact contact = new Contact(name, surname, email, phone, description);

                    agenda.CreateContact(contact);
                    break;

                case 2:
                    agenda.ShowContacts();
                    break;

                case 3:
                    System.out.println("Introduce the name of the contact to search");
                    name = sc.nextLine();
                    agenda.SearchContact(name);
                    break;

                case 4:
                    System.out.println("Exit successful");
                    break;
            }
        }
        while(option != 4);
    }
}
