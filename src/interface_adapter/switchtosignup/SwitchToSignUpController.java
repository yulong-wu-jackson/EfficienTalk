package interface_adapter.switchtosignup;

import use_case.switchtosignup.SwitchToSignUpInputBoundary;

public class SwitchToSignUpController {
    final SwitchToSignUpInputBoundary switchToSignUpInputUseCaseInteractor;
    public SwitchToSignUpController(SwitchToSignUpInputBoundary switchToSignUpInputUseCaseInteractor) {
        this.switchToSignUpInputUseCaseInteractor = switchToSignUpInputUseCaseInteractor;
    }

    public void execute() {
        switchToSignUpInputUseCaseInteractor.execute();
    }
}
