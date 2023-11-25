package use_case.notify;
import java.util.ArrayList;
import java.util.Map;


public interface NotifyUserDataAccessInterface {

    ArrayList<String> getUserEmails();
    ArrayList<String> getUsernames();

    Map<String,String> getUsersAndEmails();

}
