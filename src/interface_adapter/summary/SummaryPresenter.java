package interface_adapter.summary;

import use_case.summary.SummaryOutputBoundary;
import use_case.summary.SummaryOutputData;
import javax.swing.JOptionPane;

/**
 * The {@code SummaryPresenter} class implements {@code SummaryOutputBoundary}
 * and is responsible for presenting the summarization results to the user.
 */
public class SummaryPresenter implements SummaryOutputBoundary {

    /**
     * Prepares and displays the success view with the summary output.
     *
     * @param summaryOutputData The data containing the summary to be displayed.
     */
    @Override
    public void prepareSuccessView(SummaryOutputData summaryOutputData) {
        String summary = summaryOutputData.getSummary();
        System.out.println(summary); // Consider replacing with a logging framework
        JOptionPane.showMessageDialog(null, "Summary: " + summary);
    }

    /**
     * Prepares and displays the failure view with an error message.
     *
     * @param error The error message to be displayed.
     */
    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
    }
}
