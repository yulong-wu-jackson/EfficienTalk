package use_case.clear_users;


import java.util.ArrayList;

public class ClearInteractor implements ClearInputBoundary {
    final ClearUserDataAccessInterface userDataAccessObject;
    final ClearOutputBoundary userPresenter;

    public ClearInteractor(ClearUserDataAccessInterface clearUserDataAccessInterface,
                           ClearOutputBoundary clearOutputBoundary) {
        this.userDataAccessObject = clearUserDataAccessInterface;
        this.userPresenter = clearOutputBoundary;
    }
    public void execute() {
        ArrayList<String> usernames = userDataAccessObject.getUsernames();
        ClearOutputData clearOutputData = new ClearOutputData(usernames);
        userDataAccessObject.clearUsers();
        userPresenter.prepareSuccessView(clearOutputData);
    }
    public ArrayList<String> getUsernames() {
        return userDataAccessObject.getUsernames();
    }
}
