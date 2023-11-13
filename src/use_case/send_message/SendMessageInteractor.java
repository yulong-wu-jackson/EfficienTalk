package use_case.send_message;

import java.net.Socket;

public class SendMessageInteractor implements SendMessageInputBoundary{
    final SendMessageOutputBoundary sendMessagePresenter;

    public SendMessageInteractor(SendMessageOutputBoundary sendMessagePresenter) {
        this.sendMessagePresenter = sendMessagePresenter;
    }

    @Override
    public void execute(SendMessageInputData sendMessageInputData) {
        String message = sendMessageInputData.getMessage();
        Socket socket = sendMessageInputData.getSocket();
        SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(message, socket);

        sendMessagePresenter.present(sendMessageOutputData);
    }
}
