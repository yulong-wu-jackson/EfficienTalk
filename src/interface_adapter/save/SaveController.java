package interface_adapter.save;

import use_case.save.SaveInputBoundary;
import use_case.save.SaveInputData;

/**
 * The SaveController is responsible for handling requests to save data.
 * It uses the SaveInputBoundary to delegate the actual saving of data.
 */
public class SaveController {
    final private SaveInputBoundary saveInteractor;

    /**
     * Constructs a SaveController with the specified SaveInputBoundary.
     *
     * @param saveInteractor The SaveInputBoundary to be used for save operations.
     */
    public SaveController(SaveInputBoundary saveInteractor) {
        this.saveInteractor = saveInteractor;
    }

    /**
     * Executes the save operation with the given message.
     *
     * @param savedMessage The message to be saved.
     */
    public void execute(String savedMessage) {
        SaveInputData saveInputData = new SaveInputData(savedMessage);
        saveInteractor.execute(saveInputData);
    }
}
