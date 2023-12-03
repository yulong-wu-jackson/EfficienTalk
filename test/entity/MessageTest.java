package entity;

import org.junit.Test;

public class MessageTest {
    @Test
    public void testEntity(){
        CommonUser user = new CommonUser("jack","123","123@qq.com");
        Message textmessage = new TextMessage("hello","2003.6.1", user, user, Boolean.FALSE);
        assert (textmessage.getIs_read().equals(Boolean.FALSE));
        assert (textmessage.getContent().equals("hello"));
        assert (textmessage.getReceiver().equals(textmessage.getSender()));
        assert (textmessage.getTimestamp().equals("2003.6.1"));
    }
}
