import java.io.*;
import java.util.Scanner;


/*Write a program to create a notepad. The program will ask for sentences
to the user and write it to a file. The file can exist or not, if it does, the user
should be asked whether to overwrite the information or append it. Lines
will be numbered sequentially. You may need to read point 6 first*/


public class Ejercicio51 {
    public static void main(String[] args) {

        File file = new File("sentences.txt");
        FileWriter fw = null;
        Boolean stop = true;
        int counter = 0;

            System.out.println("Press 'C' to create a new file.\nPress 'O' to overwrite the file");
            Scanner sc = new Scanner(System.in);
            String answer = sc.nextLine();

            try {
                if (answer.equalsIgnoreCase("C")) {
                    fw = new FileWriter(file, false);

                }
                else if (answer.equalsIgnoreCase("O")) {
                    fw = new FileWriter(file, true);
                }
                else{
                    System.out.println("You have to write either 'C' or 'O'");
                }

                BufferedWriter bw = new BufferedWriter(fw);

                PrintWriter pw = new PrintWriter(bw);

                do {
                    System.out.println("Write a sentence.\nLeave it blank to stop adding phrases");
                    String phrase = sc.nextLine();
                    if(phrase.isEmpty()){
                        pw.close();
                        stop = false;
                    }
                    counter++;
                    pw.println(counter+"." + phrase);
                }
                while(stop);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
