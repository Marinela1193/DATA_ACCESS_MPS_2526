import java.io.FileInputStream;
import java.util.Scanner;

public class Ejercicio42 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the filename: ");
        String filename = scanner.nextLine();

        FileInputStream fis = null;

        boolean recognized = false;

        try {

            fis = new FileInputStream(filename);

            byte[] buffer = new byte[6];
            int bytesRead = fis.read(buffer);
            if (bytesRead < 6) {
                System.out.println("file is empty");
            }
            else{
                if(buffer[0] == (byte) 0x42 && buffer[1] == (byte) 0x4D){
                    System.out.println("BMP file detected");
                    recognized = true;
                }
                if(buffer[0] == (byte) 0x47 && buffer[1] == (byte) 0x49 && buffer[2] == (byte) 0x46 && buffer[3] == (byte) 0x38 && buffer[5] == (byte) 0x61) {
                    System.out.println("GIF file detected");
                    recognized = true;
                }
                if(buffer[0] == (byte) 0x00 && buffer[1] == (byte) 0x00 && buffer[2] == (byte) 0x01 && buffer[3] == (byte) 0x00){
                    System.out.println("ICO file detected");
                    recognized = true;
                }
                if(buffer[0] == (byte) 0xFF && buffer[1] == (byte) 0xD8 && buffer[2] == (byte) 0xFF){
                    System.out.println("JPEG file detected");
                    recognized = true;
                }
                if(buffer[0] == (byte) 0x89 && buffer[1] == (byte) 0x50 && buffer[2] == (byte) 0x4E && buffer[3] == (byte) 0x47) {
                    System.out.println("PNG file detected");
                    recognized = true;
                }
                if(!recognized) {
                    System.out.println("File extension not recognized");
                }
            }
        }
        catch(Exception e){
            System.out.println("There was an error opening the file: " + filename + e.getMessage());
        }
    }
}
