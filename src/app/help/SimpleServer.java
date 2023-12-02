package app.help;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * !!!AWARE!!!
 * This class is used for testing purpose, it is not used in the final client-server program.
 */
public class SimpleServer {
    ServerSocket serverSocket;
    public List printWriters = new ArrayList(); // store all the printWriters of all the clients
    public SimpleServer(int port){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("port is occupied");
            throw new RuntimeException(e);
        }
    }

    public void start(){
        // Listen whether a client want to connect to the port, one Client connect, create one Socket.
        try {
            while(true){
                System.out.println("Waiting Client to connect...");
                Socket socket = serverSocket.accept();
                System.out.println("One Client connected!");

                // give the Socket to a thread
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("server closed");
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class ClientHandler implements Runnable{
        Socket socket;
        String host;
        InputStream inputStream;
        OutputStream outputStream;
        PrintWriter printWriter;
        public ClientHandler(Socket socket){
            this.socket = socket;
            this.host = socket.getInetAddress().getHostAddress();
        }
        @Override
        public void run(){
            try {
                // get data (stream of bytes) from client
                inputStream = socket.getInputStream();

                // convert bytes to string (utf-8)
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                outputStream = socket.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                printWriter = new PrintWriter(bufferedWriter, true);
                // store the printWriter of this client
                printWriters.add(printWriter);

                System.out.println("Client " + host + " connected!");
                System.out.println("Current online Client number: " + printWriters.size());

                Scanner scanner = new Scanner(System.in);

                while(true){

                    // receive message from client
                    String line = bufferedReader.readLine();
                    System.out.println("Client " + host + " said: " + line);

                    // send message to all the clients
                    for (Object printWriter1 : printWriters){
                        PrintWriter pw = (PrintWriter) printWriter1;
                        pw.println(line);
                    }

                    // send message to client
//                    String message = scanner.nextLine();
//                    printWriter.println("Server said: " + message);
                }
            }
            catch (IOException e) {
                try {
                    // if client disconnect, the server release the resource
                    inputStream.close();
                    outputStream.close();
                    socket.close();


                    System.out.println("Client " + host + " disconnected!");
                    printWriters.remove(printWriter);
                    System.out.println("Current online Client number: " + printWriters.size());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }

    public static void main(String[] args){
        // ------   TODO: Constructing Socket Programming PART   ------
//        SimpleServer server = new SimpleServer();
//        System.out.println("port is available");
//
//        server.start();
        // ------   TODO: Constructing Socket Programming PART   ------
    }
}
