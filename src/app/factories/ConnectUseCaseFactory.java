package app.factories;

import interface_adapter.ViewManagerModel;
import interface_adapter.connect.ConnectController;
import interface_adapter.connect.ConnectPresenter;
import interface_adapter.connect.ConnectViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.connect.ConnectInputBoundary;
import use_case.connect.ConnectInteractor;
import use_case.connect.ConnectOutputBoundary;
import view.ConnectView;

public class ConnectUseCaseFactory {
    private ConnectUseCaseFactory() {}

    public static ConnectView create(
            ViewManagerModel viewManagerModel,
            ConnectViewModel connectViewModel,
            SignupViewModel signupViewModel) {
        ConnectController connectController = createConnectUseCase(viewManagerModel, connectViewModel, signupViewModel);
        return new ConnectView(connectViewModel, connectController);
    }

    private static ConnectController createConnectUseCase(
            ViewManagerModel viewManagerModel,
            ConnectViewModel connectViewModel,
            SignupViewModel signupViewModel) {
        ConnectInputBoundary connectInteractor = new ConnectInteractor(new ConnectPresenter(viewManagerModel, signupViewModel));
        return new ConnectController(connectInteractor);
    }
}
