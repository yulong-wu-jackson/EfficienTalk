package interface_adapter.switchtologin;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.switchtologin.SwitchToLoginOutputBoundary;

public class SwitchToLoginPresenter implements SwitchToLoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;

    public SwitchToLoginPresenter(ViewManagerModel viewManagerModel,
                                  LoginViewModel loginViewModel,
                                  SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;

    }
    public void prepareSuccessView() {
        LoginState loginState = loginViewModel.getState();
        loginState.setSocket(signupViewModel.getState().getSocket());
        this.loginViewModel.setState(loginState);
        this.loginViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
