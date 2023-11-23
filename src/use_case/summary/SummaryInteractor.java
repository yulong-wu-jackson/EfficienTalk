package use_case.summary;
import app.help.OpenAISummaryAPI;

public class SummaryInteractor implements SummaryInputBoundary{
    final SummaryUserDataAccessInterface userDataAccessObject;
    final SummaryOutputBoundary userPresenter;

    public SummaryInteractor(SummaryUserDataAccessInterface summaryUserDataAccessInterface,
                             SummaryOutputBoundary summaryOutputBoundary) {
        this.userDataAccessObject = summaryUserDataAccessInterface;
        this.userPresenter = summaryOutputBoundary;
    }

    @Override
    public void saveSummary(String summary) {
        userDataAccessObject.saveSummary(summary);
        userPresenter.prepareSuccessView(summary);
    }

    @Override
    public String getSummary(SummaryInputData summaryInputData) {
        try {
            String groupMessage = summaryInputData.getGroupMessage();
            StringBuilder builder = new StringBuilder(groupMessage);
            builder.delete(0,26);
            String modifiedGroupMessage = builder.toString();
            OpenAISummaryAPI summarizer =
                    new OpenAISummaryAPI("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");
            String summary = summarizer.getSummary(modifiedGroupMessage);
            return("Summary: " + summary);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
