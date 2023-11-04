package entity;

import java.util.ArrayList;

public class CommonGroup implements Group {
    private String GroupName;
    private ArrayList<User> Members;


    public CommonGroup(String group_name){
        this.GroupName = group_name;
        this.Members = new ArrayList<User>();


    }
    public ArrayList<User> getUsers(){
        return this.Members;
    }

    public  String getGroupName(){
        return this.GroupName;
    }
    public void addMember(User user){
        this.Members.add(user);
    }

}
