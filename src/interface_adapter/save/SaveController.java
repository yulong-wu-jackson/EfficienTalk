package interface_adapter.save;

import use_case.save.SaveInputBoundary;
import use_case.save.SaveInputData;

public class SaveController {
    final private SaveInputBoundary saveInteractor;
    public SaveController(SaveInputBoundary saveInteractor) { this.saveInteractor = saveInteractor;}

    public void saveMessage(String savedMessage) {saveInteractor.saveMessage(savedMessage);}
    public  String getMessage(String groupMessage) {
        try{
            SaveInputData saveInputData = new SaveInputData(groupMessage);
            return saveInteractor.getMessage(saveInputData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

