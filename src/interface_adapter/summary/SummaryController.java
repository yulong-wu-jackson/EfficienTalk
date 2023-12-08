package interface_adapter.summary;

import use_case.summary.SummaryInputBoundary;
import use_case.summary.SummaryInputData;

/**
 * The {@code SummaryController} class acts as a controller in the application,
 * handling the flow of summarization requests.
 */
public class SummaryController {
    private final SummaryInputBoundary summaryInteractor;

    /**
     * Constructs a {@code SummaryController} with the specified {@code SummaryInputBoundary}.
     *
     * @param summaryInteractor The {@code SummaryInputBoundary} that this controller will interact with.
     */
    public SummaryController(SummaryInputBoundary summaryInteractor) {
        this.summaryInteractor = summaryInteractor;
    }

    /**
     * Executes the summarization process for the given group message.
     *
     * @param groupMessage The message to be summarized.
     */
    public void execute(String groupMessage) {
        SummaryInputData summaryInputData = new SummaryInputData(groupMessage);
        summaryInteractor.execute(summaryInputData);
    }
}
