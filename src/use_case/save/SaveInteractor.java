package use_case.save;

/**
 * SaveInteractor handles the business logic for saving data.
 * It interacts with data access objects to save messages and communicates with the presenter
 * to display the outcome of the save operation.
 */
public class SaveInteractor implements SaveInputBoundary {
    final SaveUserDataAccessInterface userDataAccessObject;
    final SaveOutputBoundary userPresenter;

    /**
     * Constructs a SaveInteractor with specified data access and output boundary interfaces.
     *
     * @param saveUserDataAccessInterface The data access object for saving data.
     * @param saveOutputBoundary The output boundary to interact with the presenter.
     */
    public SaveInteractor(SaveUserDataAccessInterface saveUserDataAccessInterface,
                          SaveOutputBoundary saveOutputBoundary) {
        this.userDataAccessObject = saveUserDataAccessInterface;
        this.userPresenter = saveOutputBoundary;
    }

    /**
     * Executes the save operation based on the provided input data.
     * It will save the message if valid, or report an error if the message is not suitable for saving.
     *
     * @param saveInputData The data containing the message to be saved.
     */
    @Override
    public void execute(SaveInputData saveInputData) {
        if(saveInputData.getSavedMessage().equals("Welcome to the chat room!\n")) {
            userPresenter.prepareFailView("Error: The chat room is empty! There are no messages inside.");
        } else {
            String message = saveInputData.getSavedMessage();
            userDataAccessObject.saveMessage(message);

            SaveOutputData saveOutputData = new SaveOutputData("Dialogues have been saved successfully!");
            userPresenter.prepareSuccessView(saveOutputData);
        }
    }
}
