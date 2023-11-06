package use_case.clear_users;

import java.util.ArrayList;

public interface ClearInputBoundary {
    void execute();

    ArrayList<String> getUsernames();
}
