package app.factories;

import data_access.FileGroupDataAccessObject;
import entity.CommonGroupFactory;
import entity.CommonUserFactory;
import entity.GroupFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.switchtosignup.SwitchToSignUpController;
import interface_adapter.switchtosignup.SwitchToSignUpOutputPresenter;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.switchtosignup.SwitchToSignUpInputBoundary;
import use_case.switchtosignup.SwitchToSignUpInputBoundaryInteractor;
import use_case.switchtosignup.SwitchToSignUpOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            SignupViewModel signupViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            FileGroupDataAccessObject groupDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,
                    userDataAccessObject,
                    groupDataAccessObject);
            SwitchToSignUpController switchToSignUpController = createSwitchToSignUpUseCase(viewManagerModel, loginViewModel, signupViewModel);
            return new LoginView(loginViewModel, loginController, switchToSignUpController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            FileGroupDataAccessObject groupDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();
        GroupFactory commonGroupFactory = new CommonGroupFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, groupDataAccessObject, commonGroupFactory);

        return new LoginController(loginInteractor);
    }

    private static SwitchToSignUpController createSwitchToSignUpUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel) {
        SwitchToSignUpOutputBoundary switchToSignUpOutputBoundary = new SwitchToSignUpOutputPresenter(viewManagerModel,
                loginViewModel, signupViewModel);
        SwitchToSignUpInputBoundary switchToSignUpInputBoundaryInteractor = new SwitchToSignUpInputBoundaryInteractor(switchToSignUpOutputBoundary);
        return new SwitchToSignUpController(switchToSignUpInputBoundaryInteractor);
    }
}
