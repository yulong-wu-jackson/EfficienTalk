package use_case.send_message;

import java.net.Socket;

public interface SendMessageOutputBoundary {
    void present(SendMessageOutputData sendMessageOutputData);
}
