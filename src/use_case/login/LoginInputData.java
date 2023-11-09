package use_case.login;

public class LoginInputData {

    final private String username;
    final private String password;
    final private String groupname;
    final private String ipAddress;
    final private String port;

    public LoginInputData(String username, String password, String groupname, String ipAddress, String port) {
        this.username = username;
        this.password = password;
        this.groupname = groupname;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
    String getGroupname() {
        return groupname;
    }
    String getIpAddress() {
        return ipAddress;
    }
    String getPort() {
        return port;
    }

}
