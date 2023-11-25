package interface_adapter.notify;
import use_case.notify.NotifyOutputBoundary;
import use_case.notify.NotifyOutputData;

public class NotifyPresenter implements NotifyOutputBoundary{
    private final NotifyViewModel notifyViewModel;

    public NotifyPresenter(NotifyViewModel notifyViewModel) {

        this.notifyViewModel = notifyViewModel;
    }


    @Override
    public void prepareSuccessView() {
        NotifyState notifyState = notifyViewModel.getState();
        notifyState.Notify();
        this.notifyViewModel.setState(notifyState);
        notifyViewModel.firePropertyChanged();
        notifyState.unnotify();


    }

    @Override
    public void prepareFailedView(NotifyOutputData users) {
        NotifyState notifyState = notifyViewModel.getState();
        notifyState.addError();
        notifyState.addErrorUsers(users.getUsers());
        this.notifyViewModel.setState(notifyState);
        notifyViewModel.firePropertyChanged();
        notifyState.removeErrorUsers();
        notifyState.removeError();


    }
}



