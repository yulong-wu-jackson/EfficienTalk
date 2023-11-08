package app;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;
    public Client(){
        try {
            socket = new Socket("localhost", 8088);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(){
        // create a thread to handle the data from server
        ServerHandler serverHandler = new ServerHandler(socket);
        Thread thread = new Thread(serverHandler);
        thread.start();


        // send message to server
        try{
            OutputStream outputStream =  socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            // 自动行刷新
            PrintWriter printWriter = new PrintWriter(bufferedWriter, true);


            Scanner scanner = new Scanner(System.in);
            while(true){
                String message = scanner.nextLine();
                // client send its message to server
                printWriter.println(message);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    class ServerHandler implements Runnable{
        Socket socket;
        String host;
        public ServerHandler(Socket socket){
            this.socket = socket;
            this.host = socket.getInetAddress().getHostAddress();
        }
        @Override
        public void run(){
            try {
                // send message to server
                OutputStream outputStream =  socket.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                // 自动行刷新
                PrintWriter printWriter = new PrintWriter(bufferedWriter, true);


                Scanner scanner = new Scanner(System.in);
                // receive message from server
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                while(true){
                    String message = scanner.nextLine();
                    // client send its message to server
                    printWriter.println(message);

                    // receive message from server
                    String line = bufferedReader.readLine();
                    System.out.println("Server said: " + line);
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }



    public static void main(String[] args){
        // ------   TODO: Constructing Socket Programming PART   ------

        Client client = new Client();
        client.start();


        // ------   TODO: Constructing Socket Programming PART   ------
    }
}
