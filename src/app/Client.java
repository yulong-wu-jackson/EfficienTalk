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
        try {
            // send message to server
            OutputStream outputStream =  socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            // 自动行刷新
            PrintWriter printWriter = new PrintWriter(bufferedWriter, true);

            // receive message from server
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            Scanner scanner = new Scanner(System.in);
            while(true){
                String message = scanner.nextLine();
                // 输出客户端的内容
                printWriter.println(message);

                // receive message from server
                String line = bufferedReader.readLine();
                System.out.println("Server said: " + line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args){
        // ------   TODO: Constructing Socket Programming PART   ------

        Client client = new Client();
        client.start();


        // ------   TODO: Constructing Socket Programming PART   ------
    }
}
