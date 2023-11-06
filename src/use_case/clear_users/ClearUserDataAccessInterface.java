package use_case.clear_users;
import java.util.ArrayList;

public interface ClearUserDataAccessInterface {
    void clearUsers();

    ArrayList<String> getUsernames();
}