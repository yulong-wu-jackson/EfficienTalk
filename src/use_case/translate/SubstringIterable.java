package use_case.translate;

import java.util.Iterator;

public class SubstringIterable implements Iterable<String> {
    private final String translated;
    private final String key;

    public SubstringIterable(String translated, String key) {
        this.translated = translated;
        this.key = key;
    }

    @Override
    public Iterator<String> iterator() {
        return new SubstringIterator(translated, key);
    }
}
