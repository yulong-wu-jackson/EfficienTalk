package entity;

import java.util.ArrayList;

public interface Group {
    String getGroupName();
    ArrayList<User> getUsers();
    void addMember(User user);
}
