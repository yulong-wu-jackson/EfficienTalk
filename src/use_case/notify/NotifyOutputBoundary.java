package use_case.notify;

public interface NotifyOutputBoundary {
    void prepareSuccessView();

    void prepareFailedView(NotifyOutputData users);
}