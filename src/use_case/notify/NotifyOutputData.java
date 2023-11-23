package use_case.notify;

public class NotifyOutputData {
    final private String message;

    public NotifyOutputData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
