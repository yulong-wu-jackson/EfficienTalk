package use_case.connect;

import java.net.Socket;

public class ConnectOutputData {
    private Socket socket;

    public ConnectOutputData(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
}
