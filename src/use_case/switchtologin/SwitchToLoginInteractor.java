package use_case.switchtologin;

public class SwitchToLoginInteractor implements SwitchToLoginInputBoundary{
    final SwitchToLoginOutputBoundary switchToLoginPresenter;

    public SwitchToLoginInteractor (SwitchToLoginOutputBoundary switchToLoginOutputBoundary) {
        this.switchToLoginPresenter = switchToLoginOutputBoundary;
    }

    public void execute() {
        switchToLoginPresenter.prepareSuccessView();
    }
}
