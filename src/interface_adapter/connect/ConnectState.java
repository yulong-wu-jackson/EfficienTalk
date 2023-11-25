package interface_adapter.connect;

import interface_adapter.login.LoginState;

public class ConnectState {
    private String ipAddress = "";
    private String port = "";

    public ConnectState(ConnectState copy) {
        ipAddress = copy.ipAddress;
        port = copy.port;
    }
    public ConnectState() {}

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
