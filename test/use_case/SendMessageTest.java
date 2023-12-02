package use_case;

import app.ClientApp;
import app.help.SimpleServer;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInteractor;
import use_case.send_message.SendMessageOutputBoundary;
import view.LoggedInView;
import view.LoggedInViewTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class SendMessageTest {
    Thread thread;
    SimpleServer server;
    SendMessageController sendMessageController;
    @Before
    public void setUp(){
        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                server = new SimpleServer(8088);
                server.start();
            }
        });
        thread.start();

        sendMessageController = createSendMessageUseCase();
    }

    @Test
    public void testSendMessage() throws IOException {
        Socket clientSocket = new Socket("localhost", 8088);
        LoggedInViewTest view = new LoggedInViewTest();
        LoggedInState loggedInState = new LoggedInState();
        loggedInState.setSocket(clientSocket);
        sendMessageController.execute("Hello", loggedInState.getSocket());
        // get data (stream of bytes) from client
        InputStream inputStream = clientSocket.getInputStream();

        // convert bytes to string (utf-8)
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String message = bufferedReader.readLine();
        assertEquals("Hello", message);
        clientSocket.close();
    }

    private SendMessageController createSendMessageUseCase() {
        SendMessageOutputBoundary sendMessagePresenter = new SendMessagePresenter();
        SendMessageInputBoundary sendMessageInteractor = new SendMessageInteractor(sendMessagePresenter);
        return new SendMessageController(sendMessageInteractor);
    }
}
