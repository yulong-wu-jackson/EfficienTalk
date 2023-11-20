package use_case.translate;

import javax.swing.*;
import java.awt.*;

public class TranslateOutputData {
    private final TextArea translatedTextArea;
    private final JScrollPane scrollPane;

    public TranslateOutputData(TextArea translatedTextArea, JScrollPane scrollPane) {
        this.translatedTextArea = translatedTextArea;
        this.scrollPane = scrollPane;
    }
    public TextArea getTranslatedTextArea() {return translatedTextArea;}

    public JScrollPane getScrollPane() {return scrollPane;}
}
