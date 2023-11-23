package use_case.summary;

public class SummaryInputData {
    final private String groupMessage;

    public SummaryInputData(String groupMessage) {
        this.groupMessage = groupMessage;
    }

    public String getGroupMessage() {return groupMessage;}
}
