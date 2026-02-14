package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }

        return sessionFactory;
    }

    public static void main( String[] args )
    {

        createDepartment();
        updateEmployee(7499);
        readDepartment();
        deleteEmployee(7499);

    }

    public static void createDepartment() {
        try (Session session = getSessionFactory().openSession()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce el id del departamento");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Introduce el nombre del departamento: ");
            String dname = scanner.nextLine();
            System.out.print("Dónde se encuentra: ");
            String dloc = scanner.nextLine();
            Transaction transaction;

            transaction = session.beginTransaction();
            Dept department = new Dept();
            department.setId(id);
            department.setDname( dname );
            department.setLoc( dloc );
            session.persist( department ); // you can also use ‘save’
            transaction.commit(); // End of transaction

            System.out.println("Departamento introducido correctamente");
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static void updateEmployee(int employeeNumber) {
        try (Session session = getSessionFactory().openSession()) {
            Employee employee =(Employee)session.find( Employee.class,
                    employeeNumber );
            if ( employee != null ) {
                session.beginTransaction();
                Dept newDepartment = (Dept)session.find( Dept.class, 30);
                employee.setDeptno(newDepartment);
                session.merge(employee); // you can also use ‘update’
                session.getTransaction().commit();
            }
            else
                System.out.println("Employee not found");
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static void readDepartment() {
        try (Session session = getSessionFactory().openSession()) {
            Query<Employee> myQuery =
                    session.createQuery("from org.example.Employee");
            List<Employee> employees = myQuery.list();
            for ( Employee employee : employees ) {
                System.out.printf("Number : %d Name: %s%n", employee.getId(),
                        employee.getEname());
            }
        }
    }

    public static void deleteEmployee(int employeeNumber) {
        try (Session session = getSessionFactory().openSession()) {
            Employee employee = session.find( Employee.class,
                    employeeNumber );
            if ( employee != null ) {
                session.remove(employee);
                session.getTransaction().commit(); // End of transaction
                System.out.println("El empleado se ha eliminado correctamente");
            }
            else {
                System.out.println("No se ha encontrado al empleado");
            }
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }

    }
}
