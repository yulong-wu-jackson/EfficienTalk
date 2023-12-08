package use_case.save;

public interface SaveOutputBoundary {
    void prepareSuccessView(SaveOutputData saveOutputData);

    void prepareFailView(String error);
}
