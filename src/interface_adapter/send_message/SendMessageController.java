package interface_adapter.send_message;

import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

import java.net.Socket;

public class SendMessageController {
    final SendMessageInputBoundary sendMessageInteractor;
    public SendMessageController(SendMessageInputBoundary sendMessageInteractor) {
        this.sendMessageInteractor = sendMessageInteractor;
    }

    public void execute(String message, Socket socket) {
        SendMessageInputData sendMessageInputData = new SendMessageInputData(message, socket);

        sendMessageInteractor.execute(sendMessageInputData);
    }
}
