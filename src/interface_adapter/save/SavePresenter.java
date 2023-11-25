package interface_adapter.save;

import use_case.save.SaveOutputBoundary;

public class SavePresenter implements SaveOutputBoundary {
    @Override
    public void prepareSuccessView(String saveOutputData)  {
        System.out.println(saveOutputData);
    }
}
