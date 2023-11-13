package interface_adapter.send_message;

import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SendMessagePresenter implements SendMessageOutputBoundary {
    @Override
    public void present(SendMessageOutputData sendMessageOutputData) {
        try{
            String message = sendMessageOutputData.getMessage();
            Socket socket = sendMessageOutputData.getSocket();

            OutputStream outputStream =  socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
            // client send its message to server
            printWriter.println(message);

        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "unable to send message");
        }


    }
}
