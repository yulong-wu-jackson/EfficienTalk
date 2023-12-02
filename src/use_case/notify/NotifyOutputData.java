package use_case.notify;
import java.util.ArrayList;
public class NotifyOutputData {

    final private ArrayList<String> users;

    public NotifyOutputData( ArrayList<String> users) {
        this.users = users;
    }

    public ArrayList getUsers() {
        return users;
    }
}
