package app.factories;

import interface_adapter.clear_users.ClearController;
import interface_adapter.clear_users.ClearPresenter;
import interface_adapter.clear_users.ClearViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.switchtologin.SwitchToLoginController;
import interface_adapter.switchtologin.SwitchToLoginPresenter;
import use_case.clear_users.ClearInputBoundary;
import use_case.clear_users.ClearInteractor;
import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.switchtologin.SwitchToLoginInputBoundary;
import use_case.switchtologin.SwitchToLoginInteractor;
import use_case.switchtologin.SwitchToLoginOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel,
            SignupUserDataAccessInterface userDataAccessObject,
            ClearUserDataAccessInterface userDataAccessObject2,
            ClearViewModel clearViewModel) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel,
                    signupViewModel,
                    loginViewModel,
                    userDataAccessObject);
            ClearController clearController = new ClearController(new ClearInteractor(userDataAccessObject2,
                    new ClearPresenter(viewManagerModel, clearViewModel)));
            SwitchToLoginController switchToLoginController = createSwitchToLoginUseCase(viewManagerModel,
                    loginViewModel, signupViewModel);
            return new SignupView(signupController, signupViewModel, clearController, switchToLoginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

    private static SwitchToLoginController createSwitchToLoginUseCase(ViewManagerModel viewManagerModel,
                                                                      LoginViewModel loginViewModel,
                                                                      SignupViewModel signupViewModel) {
        SwitchToLoginOutputBoundary switchToLoginOutputBoundary = new SwitchToLoginPresenter(viewManagerModel,
                loginViewModel, signupViewModel);
        SwitchToLoginInputBoundary switchToLoginInputBoundaryInteractor = new SwitchToLoginInteractor(
                switchToLoginOutputBoundary);
        return new SwitchToLoginController(switchToLoginInputBoundaryInteractor);
    }
}
