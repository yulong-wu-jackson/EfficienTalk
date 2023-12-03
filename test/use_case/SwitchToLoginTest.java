package use_case;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.switchtologin.SwitchToLoginController;
import interface_adapter.switchtologin.SwitchToLoginPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.switchtologin.SwitchToLoginInputBoundary;
import use_case.switchtologin.SwitchToLoginInteractor;
import use_case.switchtologin.SwitchToLoginOutputBoundary;

public class SwitchToLoginTest {
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;
    private SwitchToLoginController switchToLoginController;

    @Before
    public void setUp() {
        viewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel();
        signupViewModel = new SignupViewModel();
        switchToLoginController = createSwitchToLoginUseCase(viewManagerModel, loginViewModel, signupViewModel);
    }

    @Test
    public void testSwitchToSignUp() {
        switchToLoginController.execute();
        assert(viewManagerModel.getActiveView().equals(loginViewModel.getViewName()));
    }


    private SwitchToLoginController createSwitchToLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel) {
        SwitchToLoginOutputBoundary switchToLoginOutptBoundary = new SwitchToLoginPresenter(viewManagerModel, loginViewModel, signupViewModel);
        SwitchToLoginInputBoundary switchToLoginInteractor = new SwitchToLoginInteractor(switchToLoginOutptBoundary);
        return new SwitchToLoginController(switchToLoginInteractor);
    }
}
