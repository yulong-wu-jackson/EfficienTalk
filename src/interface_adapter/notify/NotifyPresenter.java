package interface_adapter.notify;
import use_case.notify.NotifyOutputBoundary;

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
}

