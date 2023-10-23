package entity;

public interface Message {

    String getContent();

    String getTimestamp();

    User getSender();

    User getReceiver();

    Boolean getIs_read();

}
