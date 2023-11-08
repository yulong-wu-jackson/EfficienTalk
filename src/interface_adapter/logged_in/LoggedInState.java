package interface_adapter.logged_in;

public class LoggedInState {
    private String username = "";
    private String groupname = "";
    private String groupMessage = "";
    private String clientMessage = "";

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        groupname = copy.groupname;
        groupMessage = copy.groupMessage;
        clientMessage = copy.clientMessage;
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
}