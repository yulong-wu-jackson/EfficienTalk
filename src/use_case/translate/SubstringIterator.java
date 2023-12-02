package use_case.translate;

import java.util.Iterator;
import java.util.NoSuchElementException;

class SubstringIterator implements Iterator<String> {
    private final String translated;
    private final String key;
    private int currentIndex = 0;
    private String result = "";

    public SubstringIterator(String translated, String key) {
        this.translated = translated;
        this.key = key;
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
