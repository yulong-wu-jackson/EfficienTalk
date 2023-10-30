package entity;

import java.util.ArrayList;

public class GroupCreation {
    /**
     * Requires: password is valid.
     * @param Group_Name
     * @param Members
     * @return
     */
    public Group create(String Group_Name, ArrayList<User> Members) {
        return new Group(Group_Name, Members);
    }
}