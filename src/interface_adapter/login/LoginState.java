package interface_adapter.login;

public class LoginState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    // TODO: private String groupname
    private String groupname = "";
    private String ipAddress = "";
    private String port = "";

    public LoginState(LoginState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        groupname = copy.groupname;
        ipAddress = copy.ipAddress;
        port = copy.port;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public void setPort(String port) {
        this.port = port;
    }
}
