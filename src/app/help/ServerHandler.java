package app.help;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerHandler implements Runnable{
    Socket socket;
    String host;
    javax.swing.JTextArea textArea;
    public ServerHandler(Socket socket, javax.swing.JTextArea textArea){
        this.socket = socket;
        this.host = socket.getInetAddress().getHostAddress();
        this.textArea = textArea;
    }
    @Override
    public void run(){
        try {
            // receive message from server
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while(true){
                // receive message from server
                String line = bufferedReader.readLine();
                textArea.append(line + "\n");
                System.out.println(line);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
