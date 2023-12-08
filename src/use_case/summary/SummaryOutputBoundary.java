package use_case.summary;

public interface SummaryOutputBoundary {
    void prepareSuccessView(SummaryOutputData summary);

    void prepareFailView(String error);

}
