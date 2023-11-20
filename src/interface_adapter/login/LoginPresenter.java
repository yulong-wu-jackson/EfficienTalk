package interface_adapter.login;

import app.ClientApp;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import view.LoggedInView;
import view.ServerHandler;

import javax.swing.*;
import java.net.Socket;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        // TODO: add feature to loggedInState (didn't implemented yet)
        loggedInState.setGroupname(response.getGroupname());
        loggedInState.setIpAddress(response.getIpAddress());
        loggedInState.setPort(response.getPort());
        System.out.println("IP: "+response.getIpAddress());
        System.out.println("PORT: "+response.getPort());


        try {
            //Socket socket = new Socket(response.getIpAddress(), Integer.parseInt(response.getPort()));
            Socket socket = loginViewModel.getState().getSocket();
            loggedInState.setSocket(socket);

            ServerHandler serverHandler = new ServerHandler(socket, LoggedInView.textArea);
            Thread thread = new Thread(serverHandler);
            thread.start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot connect to server.");
            System.out.println("Error: " + e);
        }

        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();


        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
