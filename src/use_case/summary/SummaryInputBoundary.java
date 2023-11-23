package use_case.summary;

public interface SummaryInputBoundary {
    void saveSummary(String summary);
    String getSummary(SummaryInputData summaryInputData) throws Exception;
}
