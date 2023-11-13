package interface_adapter.logged_in;

import java.net.Socket;

public class LoggedInState {
    private String username = "";
    private String groupname = "";
    private String groupMessage = "";
    private String clientMessage = "";
    private String ipAddress = "";
    private String port = "";
    private Socket socket;

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        groupname = copy.groupname;
        groupMessage = copy.groupMessage;
        clientMessage = copy.clientMessage;
        ipAddress = copy.ipAddress;
        port = copy.port;
        socket = copy.socket;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }

    public String getGroupname() {
        return groupname;
    }
    public String getGroupMessage() {
        return groupMessage;
    }
    public String getClientMessage() {
        return clientMessage;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public String getPort() {
        return port;
    }
    public Socket getSocket() {
        return socket;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGroupMessage(String groupMessage) {
        this.groupMessage = groupMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}