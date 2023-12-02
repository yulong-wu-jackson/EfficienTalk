package app.help;

import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;

/**
 * !!!AWARE!!!
 * This class is used for testing purpose, it is not used in the final client-server program.
 */
public class SimulateTyping {
    public static void simulateTyping(JTextComponent component, String text) throws InterruptedException {
        for (char c : text.toCharArray()) {
            component.setCaretPosition(component.getDocument().getLength());

            KeyEvent keyEvent = new KeyEvent(
                    component, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c
            );
            KeyEvent keyEvent2 = new KeyEvent(
                    component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c
            );
            KeyEvent keyEvent3 = new KeyEvent(
                    component, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c
            );
            component.dispatchEvent(keyEvent);
            component.dispatchEvent(keyEvent2);
            component.dispatchEvent(keyEvent3);
            sleep(50);
        }

    }
}

