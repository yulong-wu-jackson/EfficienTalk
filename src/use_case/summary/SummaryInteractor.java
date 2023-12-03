package use_case.summary;
import app.help.ApiTokens;
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
        String groupMessage = summaryInputData.getGroupMessage();
        StringBuilder builder = new StringBuilder(groupMessage);
        builder.delete(0,26);
        String modifiedGroupMessage = builder.toString();
        String token = ApiTokens.getOPAI_TOKEN();
        OpenAISummaryAPI summarizer =
                new OpenAISummaryAPI(token);
        String summary = summarizer.getSummary(modifiedGroupMessage);
        return("Summary: " + summary);
    }
}
