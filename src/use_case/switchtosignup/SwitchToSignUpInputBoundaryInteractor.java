package use_case.switchtosignup;

public class SwitchToSignUpInputBoundaryInteractor implements SwitchToSignUpInputBoundary{
    final SwitchToSignUpOutputBoundary switchToSignUpOutputPresenter;
    public SwitchToSignUpInputBoundaryInteractor(SwitchToSignUpOutputBoundary switchToSignUpOutputBoundary) {
        this.switchToSignUpOutputPresenter = switchToSignUpOutputBoundary;
    }

    public void execute() {
        switchToSignUpOutputPresenter.prepareSuccessView();
    }
}
