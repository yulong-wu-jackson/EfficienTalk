package use_case;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.clear_users.ClearController;
import interface_adapter.clear_users.ClearPresenter;
import interface_adapter.clear_users.ClearViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.clear_users.ClearInteractor;
import use_case.clear_users.ClearOutputData;
import use_case.clear_users.ClearUserDataAccessInterface;


import java.io.IOException;
import java.util.ArrayList;

public class ClearTest {

    private ClearInteractor clearInteractor;
    private ClearController clearController;
    private ClearPresenter clearPresenter;
    private FileUserDataAccessObject fileUserDataAccessObject;
    private ClearUserDataAccessInterface userDataAccessInterface;
    private ClearPresenter outputBoundary;
    private ClearOutputData clearOutputData;

    @BeforeEach
    public void setUp() throws IOException {
        FileUserDataAccessObject userRepository;
        try {
            userRepository = new FileUserDataAccessObject("./testusers.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("George", "abcdefg",  "sue@mail.utoronto.ca");
        userRepository.save(user);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ClearViewModel clearViewModel = new ClearViewModel();
        clearPresenter = new ClearPresenter(viewManagerModel, clearViewModel);
        outputBoundary = clearPresenter; // Assigning SavePresenter instance to SaveOutputBoundary

        userDataAccessInterface = userRepository;
        clearInteractor = new ClearInteractor(userDataAccessInterface, outputBoundary);
        clearController = new ClearController(clearInteractor);
    }

    @Test
    public void testGetUsernameInteractor() {
        ArrayList<String> usernames = clearInteractor.getUsernames();
        assert(usernames.contains("George"));
    }

    @Test
    public void testClearController() {
        clearController.execute();
        ArrayList<String> usernames = clearInteractor.getUsernames();
        System.out.println(usernames);
        assert(usernames.isEmpty());
    }
}