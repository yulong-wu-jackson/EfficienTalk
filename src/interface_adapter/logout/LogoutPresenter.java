package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginState;
import use_case.logout.LogoutOutputBoundary;

public class LogoutPresenter implements LogoutOutputBoundary {
    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // On success, switch to the login view.

        LoginState loginState = loginViewModel.getState();
        this.loginViewModel.setState(loginState);
        this.loginViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {

    }
}
