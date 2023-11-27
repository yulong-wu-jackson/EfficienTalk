package use_case.translate;

import java.util.NoSuchElementException;

public class KeySubstringIterator implements SubstringIterator{
    private String translated;
    private String key;
    private int currentIndex;
    private String result;

    public KeySubstringIterator(String translated, String key) {
        this.translated = translated;
        this.key = key;
        this.currentIndex = 0;
        this.result = "";
    }

    @Override
    public boolean hasNext() {
        return translated.indexOf(key, currentIndex) != -1;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentIndex = translated.indexOf(key, currentIndex) + key.length();
        int endIndex = translated.indexOf("\"", currentIndex);
        result += translated.substring(currentIndex, endIndex) + "\n";
        currentIndex = endIndex;
        return result;
    }
}
