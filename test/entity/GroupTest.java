package entity;

import org.junit.Test;

public class GroupTest {
    @Test
    public void testEntity(){
        CommonGroup group = new CommonGroup("group1");
        CommonUser user = new CommonUser("jack","123","123@qq.com");
        group.addMember(user);
        assert (group.getUsers().contains(user));
    }
}
