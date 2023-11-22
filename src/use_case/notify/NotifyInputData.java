package use_case.notify;

public class NotifyInputData {
    final private String message;

    public NotifyInputData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
