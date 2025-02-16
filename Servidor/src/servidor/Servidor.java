package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {

    public static void main(String[] args) {
        // Puerto del servidor
        int port = 12345;

        int secretNum = new Random().nextInt(0, 100);
        int numPropuesto = -1;
        System.out.println("El numero secreto es el " + secretNum);

        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port + ".");

            while (true) {
                // Aceptar una conexión
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress());

                // Leer y responder al cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                String answer = "";
                while (numPropuesto != secretNum) {
                    numPropuesto = Integer.parseInt(input.readLine());
                    System.out.println("El cliente propone el número " + numPropuesto);
                    if (numPropuesto > secretNum) {
                        answer = "Es menor";
                    } else if (numPropuesto < secretNum) {
                        answer = "Es mayor";
                    } else {
                        answer = "Acertaste!";
                    }
                    System.out.println(answer);
                    output.println(answer);
                }

                client.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}