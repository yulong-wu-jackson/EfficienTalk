package interface_adapter.save;

import use_case.save.SaveOutputBoundary;
import use_case.save.SaveOutputData;

import javax.swing.*;

/**
 * The SavePresenter is responsible for presenting the save operation's outcome.
 * It implements SaveOutputBoundary and manages the display of success or failure messages.
 */
public class SavePresenter implements SaveOutputBoundary {

    /**
     * Prepares and displays a view to show a successful save operation.
     *
     * @param saveOutputData The data containing the message to be displayed on successful save.
     */
    @Override
    public void prepareSuccessView(SaveOutputData saveOutputData) {
        String savedMessage = saveOutputData.getMessage();
        System.out.println(savedMessage);
        JOptionPane.showMessageDialog(null, savedMessage);
    }

    /**
     * Prepares and displays a view to show a failed save operation.
     *
     * @param error The error message to be displayed on save failure.
     */
    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
        JOptionPane.showMessageDialog(null, error);
    }
}
