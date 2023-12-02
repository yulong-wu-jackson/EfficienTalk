package interface_adapter.notify;

import java.util.ArrayList;

public class NotifyState {
    private boolean Notified = false;

    private boolean error = false;

    private ArrayList errorUser = new ArrayList<>();
    public NotifyState(){}

    public void Notify(){
        this.Notified = true;
    }

    public void unnotify(){
        this.Notified = false;
    }

    public boolean notifyError(){return this.error;}

    public void addError(){this.error = true;
    }

    public void removeError(){this.error = false;}

    public void addErrorUsers(ArrayList<String> users){this.errorUser = users;}

    public ArrayList getErrorUsers(){return this.errorUser;}
    public void removeErrorUsers(){this.errorUser = new ArrayList();}





}
