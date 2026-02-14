import java.io.FileInputStream;
import java.util.Scanner;

public class Ejercicio43 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the filename: ");
        String filename = scanner.nextLine();

        FileInputStream fis = null;

        try {
            int tamanyo;
            int ancho;
            int alto;
            int bits;

            fis = new FileInputStream(filename);

            byte[] buffer = new byte[54];
            int bytesRead = fis.read(buffer);
            if (bytesRead < 54) {
                System.out.println("file is empty");
            }
            else if (!(buffer[0] == (byte) 0x42 && buffer[1] == (byte) 0x4D)) {
                System.out.println("Not a BMP file");
            }
            else {
                tamanyo = (buffer[2] & 0xFF) + ((buffer[3] & 0xFF)*256) + ((buffer[4] & 0xFF)*256*256) +((buffer[5] & 0xFF) * 256*256*256);

                ancho = (buffer[18] & 0xFF) + ((buffer[19] & 0xFF)*256) + ((buffer[20] & 0xFF)*256*256) +((buffer[21] & 0xFF) * 256*256*256);

                alto = (buffer[22] & 0xFF) + ((buffer[23] & 0xFF)*256) + ((buffer[24] & 0xFF)*256*256) +((buffer[25] & 0xFF) * 256*256*256);

                bits = (buffer[28] & 0xFF) + ((buffer[29] & 0xFF)*256);

                System.out.println("El tamaño del archivo es: " + tamanyo);
                System.out.println("El ancho del archivo es: " + ancho);
                System.out.println("El alto del archivo es: " + alto);
                System.out.println("Los bits por pixel del archivo es de: " + bits);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
