package org.example;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class App {
    static final String url = "jdbc:postgresql://aws-1-eu-west-1.pooler.supabase.com:5432/postgres";
    static String user = "postgres.hejarhufogoypiwzcfsk";
    static String password = "fS1k6e57";

    public static void main(String[] args)
            throws SQLException {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Class.forName("org.postgresql.Driver");

            System.out.println("Connection established!!!");
            conn.setAutoCommit(false);
            try {
                PreparedStatement st = conn.prepareStatement("INSERT INTO employee(ename, deptno) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

                Scanner sc = new Scanner(System.in);
                System.out.println("Escriba el nombre del empleado a introducir");
                String empname = sc.nextLine();
                st.setString(1, empname);
                System.out.println("Escriba el codigo del departamente al que pertenece dicho empelado");
                int deptcode = sc.nextInt();
                st.setInt(2, deptcode);

                st.executeUpdate();

                ResultSet keys = st.getGeneratedKeys();
                keys.next();

                System.out.println("La trasnaccion ha finalizado correctamente");
                conn.commit();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                conn.rollback();
            }


            /*CallableStatement statement = conn.prepareCall("{call listofemployeeofsales(?)}");
            statement.setString(1, "SALESMAN");
            ResultSet rs = statement.executeQuery();
            System.out.println("Number" + "\t" + "Name");
            System.out.println("----------------------------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
            }*/
            /*CallableStatement statement = conn.prepareCall("{call listofemployeeperdepartment(20)}");
            //statement.setInt(1, 20);
            ResultSet rs = statement.executeQuery();
            System.out.println("Number" + "\t" + "Name" + "\t" + "Department");
            System.out.println("----------------------------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(4));
            }
            rs.close();
            conn.close();*/

            /*try (CallableStatement statement = conn.prepareCall("{call listofemployeeperparameter(?)}")) {
                statement.setString(1, "%ER%");

                try (ResultSet rs = statement.executeQuery()) {
                    System.out.println("Number" + "\t" + "Name");
                    System.out.println("----------------------------------------------------------");
                    while (rs.next()) {
                        System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                    }
                }
                //Statement statement = conn.createStatement();
            /*String SQLsentence = "SELECT * FROM subjects ORDER BY code";
            ResultSet rs = statement.executeQuery(SQLsentence);
            System.out.println("Code" + "\t" + "Name" + "\t" + "Year");
            System.out.println("-----------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t " +
                        rs.getString(2) + "\t" + rs.getString(3));
            }
            rs.close();*/

            /*String SQLsentence = "INSERT INTO subjects (name, year) VALUES ('MARKUP LANGUAGE', 'First year')";
            int row = statement.executeUpdate(SQLsentence);
            System.out.println("Fila nueva añadida correctamente");
            String SQLsentence2 = "SELECT * FROM subjects ORDER BY code";
            ResultSet rs = statement.executeQuery(SQLsentence2);
            System.out.println("Code" + "\t" + "Name" + "\t" + "Year");
            System.out.println("-----------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t " +
                        rs.getString(2) + "\t" + rs.getString(3));
            }
            rs.close();*/

            /*String SQLsentence = "ALTER TABLE subjects ADD COLUMN hours INT";
            int row = statement.executeUpdate(SQLsentence);
            System.out.println("Columna nueva añadida correctamente");*/

            /*String SQLsentence3 = "SELECT * FROM subjects ORDER BY code";
            ResultSet rs = statement.executeQuery(SQLsentence3);
            System.out.println("Code" + "\t" + "Name" + "\t" + "Year" + "\t" + "Hours");
            System.out.println("-----------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t " +
                        rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t"+ rs.getString(4));
            }
            rs.close();
            conn.close();*/
        } catch (SQLException e) {
            System.out.println("Connection couldn't be established!");
        } /*finally {
                if (conn != null)
                    conn.close();
            }*/ catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}



