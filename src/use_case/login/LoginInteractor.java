package use_case.login;

import data_access.FileGroupDataAccessObject;
import entity.*;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final FileGroupDataAccessObject groupDataAccessObject;
    final LoginOutputBoundary loginPresenter;
    final GroupFactory commonGroupFactory;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary,
                           FileGroupDataAccessObject groupDataAccessObject,
                           GroupFactory commonGroupFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.groupDataAccessObject = groupDataAccessObject;
        this.commonGroupFactory = commonGroupFactory;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        String groupname = loginInputData.getGroupname();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());
                if (!groupDataAccessObject.existsByName(groupname)) {
                    Group group = commonGroupFactory.create(groupname);
                    group.addMember(user);
                    groupDataAccessObject.save(group);
                }
                else {
                    Group group = groupDataAccessObject.get(groupname);
                    group.addMember(user);
                    groupDataAccessObject.save(group);
                }


                LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false, groupname);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}