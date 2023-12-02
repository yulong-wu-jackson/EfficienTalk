package use_case;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.switchtosignup.SwitchToSignUpController;
import interface_adapter.switchtosignup.SwitchToSignUpOutputPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.switchtosignup.SwitchToSignUpInputBoundary;
import use_case.switchtosignup.SwitchToSignUpInputBoundaryInteractor;
import use_case.switchtosignup.SwitchToSignUpOutputBoundary;

public class SwitchToSignUpTest {
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;
    private SwitchToSignUpController switchToSignUpController;

    @Before
    public void setUp() {
        viewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel();
        signupViewModel = new SignupViewModel();
        switchToSignUpController = createSwitchToSignUpUseCase(viewManagerModel, loginViewModel, signupViewModel);
    }

    @Test
    public void testSwitchToSignUp() {
        switchToSignUpController.execute();
        assert(viewManagerModel.getActiveView().equals(signupViewModel.getViewName()));
    }


    private SwitchToSignUpController createSwitchToSignUpUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel) {
        SwitchToSignUpOutputBoundary switchToSignUpOutputBoundary = new SwitchToSignUpOutputPresenter(viewManagerModel,
                loginViewModel, signupViewModel);
        SwitchToSignUpInputBoundary switchToSignUpInputBoundaryInteractor = new SwitchToSignUpInputBoundaryInteractor(switchToSignUpOutputBoundary);
        return new SwitchToSignUpController(switchToSignUpInputBoundaryInteractor);
    }
}
