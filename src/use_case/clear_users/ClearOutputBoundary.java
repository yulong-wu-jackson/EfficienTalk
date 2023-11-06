package use_case.clear_users;

// TODO Complete me

import use_case.login.LoginOutputData;

import java.util.ArrayList;

public interface ClearOutputBoundary {
    ArrayList<String> SuccessView(ClearOutputData users);

    void FailView(String error);
}
