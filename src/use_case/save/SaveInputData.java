package use_case.save;

public class SaveInputData {
    final private String savedMessage;
    public SaveInputData(String savedMessage) {
        this.savedMessage = savedMessage;
    }

    public String getSavedMessage() {return savedMessage;}

}
