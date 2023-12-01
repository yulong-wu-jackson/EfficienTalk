package use_case.connect;

import javax.swing.*;
import java.net.Socket;

public class ConnectInteractor implements ConnectInputBoundary{

    final ConnectOutputBoundary connectPresenter;
    public ConnectInteractor(ConnectOutputBoundary connectPresenter){
        this.connectPresenter = connectPresenter;
    }
    @Override
    public void execute(ConnectInputData connectInputData) {
        String ipAddress = connectInputData.getIpAddress();
        String port = connectInputData.getPort();
        String flag = connectInputData.getFlag();


        try {
            Socket socket = new Socket(ipAddress, Integer.parseInt(port));

            ConnectOutputData connectOutputData = new ConnectOutputData(socket);
            connectPresenter.prepareSuccessView(connectOutputData);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot connect to server.");
            System.out.println("Error: " + e);
        }
    }
}
