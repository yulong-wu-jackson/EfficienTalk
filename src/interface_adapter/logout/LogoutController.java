package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;

public class LogoutController {
    final LogoutInputBoundary userLogoutUseCaseInteractor;
    public LogoutController(LogoutInputBoundary userLogoutUseCaseInteractor) {
        this.userLogoutUseCaseInteractor = userLogoutUseCaseInteractor;
    }

    public void execute() {
        userLogoutUseCaseInteractor.execute();
    }
}
