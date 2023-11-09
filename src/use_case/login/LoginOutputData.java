package use_case.login;

import java.net.Socket;

public class LoginOutputData {

    private final String username;
    private boolean useCaseFailed;
    private String groupname;
    private String ipAddress;
    private String port;

    public LoginOutputData(String username, boolean useCaseFailed, String groupname, String ipAddress, String port) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.groupname = groupname;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }
    public String getGroupname() {
        return groupname;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public String getPort() {
        return port;
    }

}
