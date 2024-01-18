package lk.ijse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try {
            /*Create server socket to listing request*/
            ServerSocket serverSocket = new ServerSocket(3001);
            System.out.println("Service Started");

            /*Local Socket*/
            Socket socket = serverSocket.accept();

            System.out.println("Accepted");

            /*To read the data*/
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String message = "";
            String reply = "";

            while (!message.equals("END")) {
                message = dataInputStream.readUTF();
                System.out.println("Client :" + message);
                reply = bufferedReader.readLine();
                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();
            }
            dataInputStream.close();
            dataOutputStream.close();
            bufferedReader.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}