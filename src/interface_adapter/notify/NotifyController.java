package interface_adapter.notify;
import use_case.notify.NotifyInputBoundary;
import use_case.notify.NotifyInputData;

public class NotifyController {
    final NotifyInputBoundary notifyInteractor;

    public NotifyController(NotifyInputBoundary notifyInteractor) {
        this.notifyInteractor = notifyInteractor;

    }

    public void execute(String message){
        NotifyInputData notifyInputData = new NotifyInputData(message);

        notifyInteractor.execute(notifyInputData);

    }
}
