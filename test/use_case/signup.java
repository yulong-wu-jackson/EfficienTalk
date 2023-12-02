package use_case;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {
    private SignupUserDataAccessInterface userRepository;
    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        userRepository = new FileUserDataAccessObject("./testusers.csv", new CommonUserFactory());
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Declan", "12",  "pangtl@126.com");
        userRepository.save(user);
    }

    @Test
    void successTest() throws IOException {
        userRepository.delete();
        SignupInputData inputData = new SignupInputData("Declan", "12", "12","pangtl@126.com");

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
        userRepository.delete();
        SignupInputData inputData = new SignupInputData("Declan", "12", "3","p");

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

    @AfterEach
    public void tearDown() throws IOException {
        userRepository.delete();
    }
}