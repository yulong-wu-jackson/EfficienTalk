package use_case.summary;

import use_case.signup.SignupInputData;

public interface SummaryInputBoundary {
    void execute(SummaryInputData groupMessage);
}
