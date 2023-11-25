package interface_adapter.connect;

import use_case.connect.ConnectInputBoundary;
import use_case.connect.ConnectInputData;

public class ConnectController {
    final ConnectInputBoundary connectUseCaseInteractor;

    public ConnectController(ConnectInputBoundary connectUseCaseInteractor) {
        this.connectUseCaseInteractor = connectUseCaseInteractor;
    }

    public void execute(String ipAddress, String port) {
        String flag = "connect";
        ConnectInputData connectInputData = new ConnectInputData(ipAddress, port, flag);
        connectUseCaseInteractor.execute(connectInputData);
    }
}
