package clienteadivinador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ClienteAdivinador {

    public static void main(String[] args) {
        // IP y puerto del servidor
        String host = "localhost";
        int port = 12345;
        int min = 0;
        int max = 100;
        int propuesta;
        
        
        try {
            String answer = "";
            Socket socket = new Socket(host, port);
            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");

            // Enviarle un mensaje
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            boolean continuar = true;
            while (continuar) {
                propuesta = min + new Random().nextInt(max - min + 1);
                System.out.println("Propongo el número " + propuesta);
                output.println(propuesta);

                answer = input.readLine();
                System.out.println("Servidor dice: " + answer);
                if (answer.equals("Es mayor")) {
                    min = propuesta;
                } else if (answer.equals("Es menor")) {
                    max = propuesta;
                } else if (answer.equals("Acertaste!")) {
                    continuar = false;
                }
                System.out.println("Mínimo: " + min + " | Máximo " + max + "\n");
            }
            
            System.out.println("El juego ha acabado.");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}