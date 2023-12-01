package use_case;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import use_case.signup.SignupInputData;
import use_case.signup.SignupOutputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.signup.SignupInteractor;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @Test
    void successTest() throws IOException {
        SignupInputData inputData = new SignupInputData("Declan", "12", "12","pangtl@126.com");
        SignupUserDataAccessInterface userRepository = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals("Declan", user.getUsername());
                assertEquals("pangtl@126.com",user.getEmail());
                assertTrue(userRepository.existsByName("Declan"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() throws IOException {
        SignupInputData inputData = new SignupInputData("Declan", "12", "3","p");
        SignupUserDataAccessInterface userRepository = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() throws IOException {
        SignupInputData inputData = new SignupInputData("Declan", "1", "1","a");
        SignupUserDataAccessInterface userRepository = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());;

        // Add Declan to the repo so that when we check later they already exist
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Declan", "000",  "a");
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }
}