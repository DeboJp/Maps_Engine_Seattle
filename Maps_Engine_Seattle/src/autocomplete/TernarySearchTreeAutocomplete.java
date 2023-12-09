package autocomplete;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * Ternary search tree (TST) implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TernarySearchTreeAutocomplete implements Autocomplete {
    /**
     * The overall root of the tree: the first character of the first autocompletion term added to this tree.
     */
    private Node overallRoot;

    /**
     * Constructs an empty instance.
     */
    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Replace with your code
        for (CharSequence term : terms)
        {
            overallRoot = put(term, overallRoot, 0);
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private Node put(CharSequence term, Node curr, int i)
    {
        if (curr == null)
        {
            curr = new Node(term.charAt(i));
        }
        if (i == term.length() - 1 && curr.data == term.charAt(i))
        {
            curr.isTerm = true;
            return curr;
        }

        char c = term.charAt(i);
        if (c == curr.data)
        {
            curr.mid = put(term, curr.mid, i+1);
        }
        else if ( c < curr.data)
        {
            curr.left = put(term, curr.left, i);
        }
        else {
            curr.right = put(term, curr.right, i);
        }
        return curr;
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        // TODO: Replace with your code
        List<CharSequence> matches = new ArrayList<>();
        allMatches(prefix, overallRoot, 0, matches);
        return matches;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private void allMatches(CharSequence prefix, Node curr, int i, List<CharSequence> matches)
    {
        if (curr != null)
        {
            if (i < prefix.length())
            {
                char c = prefix.charAt(i);

                if (i ==prefix.length()-1 && c == curr.data && curr.isTerm)
                {
                    matches.add(prefix);
                }

                if (c == curr.data)
                {
                    allMatches(prefix, curr.mid, i+1, matches);
                }
                else if (c < curr.data)
                {
                    allMatches(prefix, curr.left, i, matches);
                }
                else
                {
                    allMatches(prefix, curr.right, i, matches);
                }
            }
            else
            {
                collect(prefix, curr, matches);
            }
        }
    }

    private void collect(CharSequence prefix, Node curr, List<CharSequence> matches)
    {
        if (curr != null)
        {
            collect(prefix, curr.left, matches);
            if (curr.isTerm)
            {
                matches.add(prefix.toString() + curr.data);
            }
            collect(prefix.toString() + curr.data, curr.mid, matches);
            collect(prefix, curr.right, matches);
        }
    }


    /**
     * A search tree node representing a single character in an autocompletion term.
     */
    private static class Node {
        private final char data;
        private boolean isTerm;
        private Node left;
        private Node mid;
        private Node right;

        public Node(char data) {
            this.data = data;
            this.isTerm = false;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}
