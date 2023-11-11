package view;

import javax.swing.*;
import java.awt.*;

/**
 * A panel containing a label and a text field.
 */
class LabelTextPanel extends JPanel {
    LabelTextPanel(JLabel label, JTextField textField) {
        label.setFont(new Font(null, Font.PLAIN, 15));
        textField.setFont(new Font(null, Font.PLAIN, 15));
        this.add(label);
        this.add(textField);
    }
}
