package interface_adapter.summary;

import use_case.summary.SummaryOutputBoundary;

public class SummaryPresenter implements SummaryOutputBoundary {
    @Override
    public void prepareSuccessView(String summaryOutputData) {
        System.out.println(summaryOutputData);
    }
}
