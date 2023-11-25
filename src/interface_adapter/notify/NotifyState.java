package interface_adapter.notify;

import java.util.ArrayList;

public class NotifyState {
    private boolean Notified = false;
    public NotifyState(){}

    public void Notify(){
        this.Notified = true;
    }

    public void unnotify(){
        this.Notified = false;
    }





}
