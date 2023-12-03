package use_case.logout;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoginUserDataAccessInterface userDataAccessInterface;
    private LogoutController logoutController;


    @Test
    void successTest() throws IOException {
        viewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel();
        loggedInViewModel = new LoggedInViewModel();
        userDataAccessInterface = new LoginUserDataAccessInterface() {
            @Override
            public boolean existsByName(String identifier) {
                return false;
            }

            @Override
            public void save(User user) {

            }

            @Override
            public User get(String username) {
                return null;
            }
        };
        logoutController = createLogoutUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessInterface);
        logoutController.execute();
        assertEquals("log in", viewManagerModel.getActiveView());

    }

    private LogoutController createLogoutUseCase(
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
}