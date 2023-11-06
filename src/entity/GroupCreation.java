package entity;

import java.util.ArrayList;

public class GroupCreation {
    /**
     * Requires: password is valid.
     * @param Group_Name
     * @param Members
     * @return
     */
    public CommonGroup create(String Group_Name, ArrayList<User> Members) {
        return new CommonGroup(Group_Name, Members);
    }
}