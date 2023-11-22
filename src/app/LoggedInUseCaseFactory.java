package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslatePresenter;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInteractor;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInteractor;
import use_case.translate.TranslateOutputBoundary;
import view.LoggedInView;
import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCaseFactory {

    /** Prevent instantiation. */
    private LoggedInUseCaseFactory() {}

    public static LoggedInView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        try {
            LogoutController logoutController = createLogoutUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
            SendMessageController sendMessageController = createSendMessageUseCase();
            TranslateController translateController = createTranslateUseCase(loggedInViewModel);
            return new LoggedInView(loggedInViewModel, logoutController, sendMessageController, translateController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LogoutController createLogoutUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutOutputBoundary);

        return new LogoutController(logoutInteractor);
    }

    private static SendMessageController createSendMessageUseCase() {
        SendMessageOutputBoundary sendMessagePresenter = new SendMessagePresenter();
        SendMessageInputBoundary sendMessageInteractor = new SendMessageInteractor(sendMessagePresenter);
        return new SendMessageController(sendMessageInteractor);
    }

    private static TranslateController createTranslateUseCase(LoggedInViewModel loggedInViewModel) {
        TranslateOutputBoundary translatePresenter = new TranslatePresenter(loggedInViewModel);
        LoggedInState loggedInState = loggedInViewModel.getState();
        TranslateInputBoundary translateInteractor = new TranslateInteractor(translatePresenter);
        return new TranslateController(translateInteractor);
    }
}