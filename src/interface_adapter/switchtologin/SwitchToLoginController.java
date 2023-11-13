package interface_adapter.switchtologin;

import use_case.switchtologin.SwitchToLoginInputBoundary;

public class SwitchToLoginController {
    final SwitchToLoginInputBoundary switchToLoginInputUseCaseInteractor;
    public SwitchToLoginController(SwitchToLoginInputBoundary switchToLoginInputUseCaseInteractor) {
        this.switchToLoginInputUseCaseInteractor = switchToLoginInputUseCaseInteractor;
    }

    public void execute() {
        switchToLoginInputUseCaseInteractor.execute();
    }
}


