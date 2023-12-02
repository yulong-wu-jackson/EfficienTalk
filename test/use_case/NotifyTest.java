package use_case;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.notify.NotifyPresenter;
import interface_adapter.notify.NotifyViewModel;
import use_case.notify.NotifyInputBoundary;
import use_case.notify.NotifyOutputBoundary;
import use_case.notify.NotifyUserDataAccessInterface;
import use_case.notify.NotifyInputData;
import use_case.notify.NotifyOutputData;
import use_case.notify.NotifyInteractor;
import data_access.FileUserDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NotifyInteractorTest {
    private NotifyUserDataAccessInterface userRepository;
    boolean success;

    @Test
    public void successTest() throws IOException {
        // Set up the user data access with a valid email
        NotifyUserDataAccessInterface userRepository = new FileUserDataAccessObject("./testusers.csv", new CommonUserFactory());
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Declan", "12",  "pangtl@126.com");
        ((FileUserDataAccessObject) userRepository).save(user);
        success = false;

        // Create a presenter for testing purposes with overridden methods
        NotifyOutputBoundary notifyPresenter = new NotifyOutputBoundary() {


            @Override
            public void prepareSuccessView() {
                success = true;

            }

            @Override
            public void prepareFailedView(NotifyOutputData data) {
                fail("Notification was expected to succeed, but it failed.");
            }
        };

        // Instantiate the interactor with the presenter and user data access
        NotifyInputBoundary notifyInteractor = new NotifyInteractor(notifyPresenter, userRepository);

        // Prepare input data
        NotifyInputData inputData = new NotifyInputData("Hello!");

        // Execute the notification use case
        notifyInteractor.execute(inputData);
        assertTrue(success);

        // Assertions are done in the overridden methods of the presenter
    }

    @Test
    public void failureTest() throws IOException {
        // The same setup as above, but with an invalid email
        userRepository = new FileUserDataAccessObject("./testusers.csv", new CommonUserFactory());
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Declan", "12",  "invalid_address");
        ((FileUserDataAccessObject) userRepository).save(user);
        NotifyOutputBoundary notifyPresenter = new NotifyOutputBoundary() {


            @Override
            public void prepareSuccessView() {
                fail("Notification was expected to fail, but it succeeded.");
            }

            @Override
            public void prepareFailedView(NotifyOutputData data) {
                assertNotNull(data);

            }
        };

        NotifyInputBoundary notifyInteractor = new NotifyInteractor(notifyPresenter, userRepository);

        NotifyInputData inputData = new NotifyInputData("Hello!");
        notifyInteractor.execute(inputData);
    }
}