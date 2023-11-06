package interface_adapter.logged_in;

public class LoggedInState {
    private String username = "";
    private String groupname = "";

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        groupname = copy.groupname;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}