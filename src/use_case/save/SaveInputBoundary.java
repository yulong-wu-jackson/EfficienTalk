package use_case.save;

public interface SaveInputBoundary {
    void saveMessage(String savedMessage);
    String getMessage(SaveInputData saveInputData) throws  Exception;
}
