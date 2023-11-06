package interface_adapter.switchtosignup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.switchtosignup.SwitchToSignUpOutputBoundary;

public class SwitchToSignUpOutputPresenter implements SwitchToSignUpOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;
    public SwitchToSignUpOutputPresenter(ViewManagerModel viewManagerModel,
                                         LoginViewModel loginViewModel,
                                         SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }
    public void prepareSuccessView() {
        SignupState signupState = signupViewModel.getState();
        this.signupViewModel.setState(signupState);
        this.signupViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
