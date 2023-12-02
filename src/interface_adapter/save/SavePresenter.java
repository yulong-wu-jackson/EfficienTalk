package interface_adapter.save;

import use_case.save.SaveOutputBoundary;

import javax.swing.*;

public class SavePresenter implements SaveOutputBoundary {
    @Override
    public void prepareSuccessView(String saveOutputData)  {
        System.out.println(saveOutputData);
        JOptionPane.showMessageDialog(null, "Dialogues have been saved!");
    }
}
