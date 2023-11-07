package app;

import java.io.IOException;
import java.net.Socket;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        // ------   TODO: Constructing Socket Programming PART   ------
        // Create socket object and assign host location and port
        // Input IPv4 address of the server to host
        Socket socket = new Socket("localhost", 8088);
        System.out.println("Server connected!");

        // ------   TODO: Constructing Socket Programming PART   ------
    }

}
