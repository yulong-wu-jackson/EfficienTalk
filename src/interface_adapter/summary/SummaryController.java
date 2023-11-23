package interface_adapter.summary;

import use_case.summary.SummaryInputBoundary;
import use_case.summary.SummaryInputData;

public class SummaryController {
    final private SummaryInputBoundary summaryInteractor;
    public SummaryController(SummaryInputBoundary summaryInteractor) {
        this.summaryInteractor = summaryInteractor;
    }

    public void saveSummary(String summary) { summaryInteractor.saveSummary(summary);}
    public String getSummary(String groupMessage) {
        try{
            SummaryInputData summaryInputData = new SummaryInputData(groupMessage);
            return summaryInteractor.getSummary(summaryInputData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
