package entity;

public class TextMessage implements Message {

    private final String content;
    private final String timestamp;
    private final User sender;
    private final User receiver;
    private final Boolean is_read;

    TextMessage(String content, String timestamp, User sender, User receiver, Boolean is_read) {
        this.content = content;
        this.timestamp = timestamp;
        this.sender = sender;
        this.receiver = receiver;
        this.is_read = is_read;
    }

    public String getContent() {return content;}

    public String getTimestamp() {return timestamp;}

    public User getSender() {return sender;}

    public User getReceiver() {return receiver;}

    public Boolean getIs_read() {return is_read;}
}
