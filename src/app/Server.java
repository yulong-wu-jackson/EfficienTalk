package app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    ServerSocket serverSocket;
    public Server(){
        try {
            serverSocket = new ServerSocket(8088);
        } catch (IOException e) {
            System.out.println("port is occupied");
            throw new RuntimeException(e);
        }
    }

    public void start(){
        System.out.println("Waiting Client to connect...");

        // Listen whether a client want to connect to the port, waiting until Client connect
        try {
            Socket socket = serverSocket.accept();
            System.out.println("One Client connected!");

            // get data (stream of bytes) from client
            InputStream inputStream = socket.getInputStream();

            // convert bytes to string (utf-8)
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
            Scanner scanner = new Scanner(System.in);

            while(true){
                // receive message from client
                String line = bufferedReader.readLine();
                System.out.println("Client said: " + line);

                // send message to client
                String message = scanner.nextLine();
                printWriter.println(message);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        // ------   TODO: Constructing Socket Programming PART   ------
        Server server = new Server();
        System.out.println("port is available");

        server.start();



        // ------   TODO: Constructing Socket Programming PART   ------
    }
}
