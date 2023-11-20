package use_case.translate;

import javax.swing.*;
import java.awt.*;

public class TranslateOutputData {
    private final JTextArea translatedTextArea;
    private final JScrollPane scrollPane;

    public TranslateOutputData(JTextArea translatedTextArea, JScrollPane scrollPane) {
        this.translatedTextArea = translatedTextArea;
        this.scrollPane = scrollPane;
    }
    public JTextArea getTranslatedTextArea() {return translatedTextArea;}

    public JScrollPane getScrollPane() {return scrollPane;}
}
