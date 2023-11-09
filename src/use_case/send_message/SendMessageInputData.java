package use_case.send_message;

import java.net.Socket;

public class SendMessageInputData {
    final private String message;
    final private Socket socket;

    public SendMessageInputData(String message, Socket socket) {
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
