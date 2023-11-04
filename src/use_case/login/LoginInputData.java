package use_case.login;

public class LoginInputData {

    final private String username;
    final private String password;
    final private String groupname;

    public LoginInputData(String username, String password, String groupname) {
        this.username = username;
        this.password = password;
        this.groupname = groupname;
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

}
