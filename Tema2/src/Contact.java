import java.io.Serializable;

public class Contact implements Serializable {

    //variables
    protected String name;
    protected String surname;
    protected String email;
    protected int phone;
    protected String description;

    //Constructor
    public Contact(String _name, String _surname, String _email, int _phone, String _description) {
        this.name = _name;
        this.surname = _surname;
        this.email = _email;
        this.phone = _phone;
        this.description = _description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact() {}

    //Metodo para imprimir el contacto
    @Override
    public String toString() {
        return "Contact: " +'\n' +
                "Name: " + name + '\n' +
                "Surname: " + surname + '\n' +
                "Email: " + email + '\n' +
                "Phone number: " + phone + '\n' +
                "Description: " + description + '\n';
    }

}
