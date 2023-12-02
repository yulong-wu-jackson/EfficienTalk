package interface_adapter.clear_users;

// TODO Complete me

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearOutputData;

import javax.swing.*;
import java.util.ArrayList;

public class ClearPresenter implements ClearOutputBoundary {
    private final ClearViewModel clearViewModel;
    private ViewManagerModel viewManagerModel;
    public ClearPresenter(ViewManagerModel viewManagerModel, ClearViewModel clearViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.clearViewModel = clearViewModel;
    }
    public void prepareSuccessView(ClearOutputData clearOutputData) {
        System.out.println(clearOutputData.getUsernames());
        ArrayList<String> usernames = clearOutputData.getUsernames();
        String usernamesString = "";
        for (String username : usernames) {
            usernamesString += username + "\n";
        }
        JOptionPane.showMessageDialog(null,
                "These users have been cleared:\n" + usernamesString);
    }
}
