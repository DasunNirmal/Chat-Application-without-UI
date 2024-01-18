package lk.ijse;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        try {
            /*Create a Socket*/
            Socket socket = new Socket("localhost",3001);
            System.out.println("Service Started");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));;

            String message = "";
            String reply = "";

            while (!message.equals("END")) {
                reply = bufferedReader.readLine();
                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();

                message = dataInputStream.readUTF();
                System.out.println("Server :" + message);
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
