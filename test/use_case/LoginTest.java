package use_case;

import app.factories.LoginUseCaseFactory;
import data_access.FileGroupDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private FileUserDataAccessObject userRepository;
    private FileGroupDataAccessObject groupRepository;
    private ViewManagerModel viewManagerModel;
    private LoginController loginController;
    private Thread popupThread;
    @Before
    public void setUp(){
        try {
            userRepository = new FileUserDataAccessObject("./testusers.csv", new CommonUserFactory());
            groupRepository = new FileGroupDataAccessObject("./testgroups.csv", new CommonGroupFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Jackson", "11",  "jiaran@mail.utoronto.ca");
        userRepository.save(user);
        viewManagerModel = new ViewManagerModel();
        try {
            loginController = createLoginUseCase(viewManagerModel, new LoginViewModel(), new LoggedInViewModel(),
                    userRepository, groupRepository);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Thread popupThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        SwingUtilities.invokeLater(() -> {
                            Window[] windows = Window.getWindows();
                            for (Window window : windows) {
                                if (window instanceof JDialog && window.isVisible()) {
                                    JDialog dialog = (JDialog) window;
                                    JButton okButton = findJOptionPaneOkButton(dialog);
                                    if (okButton != null) {
                                        okButton.doClick();
                                        break; // Break after clicking the button
                                    }
                                }
                            }
                        });
                        Thread.sleep(500); // A short delay to avoid high CPU usage
                    }
                } catch (InterruptedException e) {
                    // Thread was interrupted, safely exit the loop
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                }
            }
        });
        popupThread.start();


    }

    private JButton findJOptionPaneOkButton(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                return (JButton) component;
            } else if (component instanceof Container) {
                JButton button = findJOptionPaneOkButton((Container) component);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }

    private LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            FileGroupDataAccessObject groupDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();
        GroupFactory commonGroupFactory = new CommonGroupFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, groupDataAccessObject, commonGroupFactory);

        return new LoginController(loginInteractor);
    }

    @Test
    public void testSuccessLogin(){
        loginController.execute("Jackson", "11", "csc207", "", "");
        loginController.execute("Jackson", "11", "csc207", "", "");
        assertEquals("Jackson", userRepository.get("Jackson").getName());

        assertEquals("logged in", viewManagerModel.getActiveView());
    }

    @Test
    public void testPasswordFailLogin(){
        loginController.execute("Jackson", "12", "csc207", "", "");
        assertTrue(!Objects.equals(viewManagerModel.getActiveView(), "logged in"));
    }

    @Test
    public void testUserNameFailLogin(){
        loginController.execute("Jackson1", "11", "csc208", "", "");
        assertTrue(!Objects.equals(viewManagerModel.getActiveView(), "logged in"));
        groupRepository.clearGroups();
    }

    @After
    public void tearDown(){
        userRepository.delete();
        //groupRepository.clearGroups();
        if (popupThread != null){
            popupThread.interrupt();
        }
    }
}
