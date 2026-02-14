import java.io.*;
import java.util.Scanner;

public class Ejercicio52 {
    public static void main(String[] args) {

        //Hago que el usuario introduzca los dos ficheros a comparar
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the filename1: ");
        String filename = scanner.nextLine();
        System.out.println("Please enter the filename2: ");
        String filename2 = scanner.nextLine();

        //Declaro cada fichero para poderlos comparar
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            BufferedReader reader2 = new BufferedReader(new FileReader(filename2));

            String line1 = reader.readLine();
            String line2 = reader2.readLine();

            File file = new File("comparison.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);


            while((line1 !=null) || (line2 !=null)) {

                if(line1 == null) {
                    pw.println(line2);
                    line2 = reader2.readLine();
                } else if(line2 == null) {
                    pw.println(line1);
                    line1 = reader.readLine();
                }

                else{
                    int comparison = line1.compareTo(line2);

                    if (comparison < 0) {
                        pw.println(line1);
                        line1 = reader.readLine();

                    } else if (comparison > 0) {

                        pw.println(line2);
                        line2 = reader2.readLine();

                    } else {

                        pw.println(line1);
                        pw.println(line2);
                        line1 = reader.readLine();
                        line2 = reader2.readLine();
                    }
                }
            }

            pw.close();
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}