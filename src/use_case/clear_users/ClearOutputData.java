package use_case.clear_users;

// TODO Complete me

import java.util.ArrayList;

public class ClearOutputData {
    private ArrayList<String> usernames;

    public ClearOutputData(ArrayList<String> usernames) {
        this.usernames = usernames;
    }
    public ArrayList<String> getUsernames() {
        return usernames;
    }
}
