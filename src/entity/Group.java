package entity;

import java.util.ArrayList;
import entity.User;

public class Group {
    private String Group_name;
    private ArrayList<User> Members;


    public Group(String group_name, ArrayList<User> members){
        this.Group_name = group_name;
        this.Members = members;


    }
    public ArrayList get_User(){
        return this.Members;
    }

    public  String getGroup_name(){
        return this.Group_name;
    }

}
