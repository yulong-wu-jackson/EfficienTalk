package use_case.clear_users;


import java.util.ArrayList;

public class ClearInteractor implements ClearInputBoundary {
    final ClearUserDataAccessInterface clearUserDataAccessInterface;

    final ClearOutputBoundary userPresenter;

    public ClearInteractor(ClearUserDataAccessInterface clearUserDataAccessInterface,
                           ClearOutputBoundary clearOutputBoundary){
        this.clearUserDataAccessInterface = clearUserDataAccessInterface;
        this.userPresenter = clearOutputBoundary;
    }

    @Override
    public ArrayList<String> clearAllUsers() {
        ArrayList<String> users = null;
        try {
            users = clearUserDataAccessInterface.clearAllUsers();
            ClearOutputData outputData = new ClearOutputData(users);
            return userPresenter.SuccessView(outputData);
        } catch (Exception e) {
            userPresenter.FailView("Error!");
            return null;
        }

    }
}
