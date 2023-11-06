package app;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // ------   TODO: Constructing Socket Programming PART   ------
        // Create socket object and assign host location and port
        Socket socket = new Socket("localhost", 8088);
        System.out.println("Server connected!");

        // ------   TODO: Constructing Socket Programming PART   ------
    }

}
