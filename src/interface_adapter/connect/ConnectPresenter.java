package interface_adapter.connect;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.connect.ConnectOutputBoundary;
import use_case.connect.ConnectOutputData;

import java.net.Socket;

public class ConnectPresenter implements ConnectOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;

    public ConnectPresenter(ViewManagerModel viewManagerModel,
                            SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }

    public void prepareSuccessView(ConnectOutputData response) {
        // On success, switch to the signup view.
        Socket socket = response.getSocket();
        SignupState signupState = signupViewModel.getState();
        signupState.setSocket(socket);

        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
