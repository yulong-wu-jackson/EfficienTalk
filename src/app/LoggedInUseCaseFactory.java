package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.summary.SummaryController;
import interface_adapter.summary.SummaryPresenter;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInteractor;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.summary.*;
import view.LoggedInView;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class LoggedInUseCaseFactory {

    /** Prevent instantiation. */
    private LoggedInUseCaseFactory() {}

    public static LoggedInView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            SummaryUserDataAccessInterface summaryUserDataAccessObject) {

        try {
            LogoutController logoutController = createLogoutUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
            SendMessageController sendMessageController = createSendMessageUseCase();
            SummaryController summaryController = createSummaryUseCase(summaryUserDataAccessObject);
            return new LoggedInView(loggedInViewModel, logoutController, sendMessageController, summaryController);
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

    private static SummaryController createSummaryUseCase(SummaryUserDataAccessInterface summaryUserDataAccessObject) {
        SummaryOutputBoundary summaryPresenter = new SummaryPresenter();
        SummaryInputBoundary summaryInteractor = new SummaryInteractor(summaryUserDataAccessObject, summaryPresenter);
        return new SummaryController(summaryInteractor);
    }
}