package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Binary search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class BinarySearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> elements;

    /**
     * Constructs an empty instance.
     */
    public BinarySearchAutocomplete() {
        elements = new ArrayList<>();
    }
    /**
     * Adds all terms to the autocomplete list and sorts them.
     *
     * @param terms Collection of CharSequence terms to add
     */
    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        elements.addAll(terms);
        Collections.sort(elements, CharSequence::compare);

//        throw new UnsupportedOperationException("Not implemented yet");
    }
    /**
     * Finds all terms that start with the given prefix.
     *
     * @param prefix CharSequence prefix to search for
     * @return List of matching CharSequence terms
     */

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        if (prefix == null || prefix.length() == 0) {
            return result;
        }

        // Perform binary search to find the index of the prefix
        int index = Collections.binarySearch(elements, prefix, CharSequence::compare);
        int startIndex = index >= 0 ? index : -index - 1;

        // Iterate through the list from the found index and add matching terms
        for (int i = startIndex; i < elements.size(); i++) {
            CharSequence term = elements.get(i);
            if (term.length() < prefix.length() || !term.subSequence(0, prefix.length()).equals(prefix)) {
                break;
            }
            result.add(term);
        }

        return result;
//        throw new UnsupportedOperationException("Not implemented yet");
    }
}
