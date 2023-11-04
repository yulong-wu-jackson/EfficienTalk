package use_case.login;

public class LoginOutputData {

    private final String username;
    private boolean useCaseFailed;
    private String groupname;

    public LoginOutputData(String username, boolean useCaseFailed, String groupname) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.groupname = groupname;
    }

    public String getUsername() {
        return username;
    }
    public String getGroupname() {
        return groupname;
    }

}
