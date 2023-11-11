package use_case.signup;

import entity.User;
import entity.UserFactory;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
            // found bug that if username is empty, it will still create a user
        } else if (signupInputData.getUsername().equals("")) {
            userPresenter.prepareFailView("Username cannot be empty.");
        } else if (signupInputData.getPassword().equals("")) {
            userPresenter.prepareFailView("Password cannot be empty.");
        } else if (signupInputData.getEmail().equals("")) {
            userPresenter.prepareFailView("Email cannot be empty.");
        } else {

            User user = userFactory.create(signupInputData.getUsername(),
                    signupInputData.getPassword(),
                    signupInputData.getEmail());

            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), user.getEmail(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}