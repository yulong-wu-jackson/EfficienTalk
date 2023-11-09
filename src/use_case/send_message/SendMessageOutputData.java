package use_case.send_message;

import java.net.Socket;

public class SendMessageOutputData {
    String message;
    Socket socket;
    public SendMessageOutputData(String message, Socket socket) {
        this.message = message;
        this.socket = socket;
    }
    public String getMessage() {
        return message;
    }
    public Socket getSocket() {
        return socket;
    }
}
