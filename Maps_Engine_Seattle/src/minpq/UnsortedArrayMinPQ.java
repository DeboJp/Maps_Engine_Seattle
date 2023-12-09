package minpq;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Unsorted array (or {@link ArrayList}) implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class UnsortedArrayMinPQ<E> implements MinPQ<E> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the element-priority pairs in no specific order.
     */
    private final List<PriorityNode<E>> elements;

    /**
     * Constructs an empty instance.
     */
    public UnsortedArrayMinPQ() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        // TODO: Replace with your code
        elements.add(new PriorityNode<>(element, priority));
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean contains(E element) {
        // TODO: Replace with your code
//        for (int i=0; i < elements.size(); i++)
//        {
//            if (elements.get(i).getElement().equals(element))
//            {
//                return true;
//            }
//        }
//        return false;

        return elements.contains(new PriorityNode<>(element, 0));
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code

        double min = elements.get(0).getPriority();
        E minElement = elements.get(0).getElement();

        for (int i=1; i < elements.size(); i++)
        {
            E currElement = elements.get(i).getElement();
            double currMin = elements.get(i).getPriority();

            if (currMin < min) {
                min = currMin;
                minElement = currElement;
            }
        }
        return minElement;

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code

//        double min = elements.get(0).getPriority();
//        E minElement = elements.get(0).getElement();
//        int minIndex = 0;
//        for (int i=1; i < elements.size(); i++)
//        {
//            E currElement = elements.get(i).getElement();
//            double currMin = elements.get(i).getPriority();
//
//            if (currMin < min) {
//                min = currMin;
//                minElement = currElement;
//                minIndex = i;
//            }
//        }
//        elements.remove(minIndex);
//        return minElement;

        E element = peekMin();
        elements.remove(new PriorityNode<>(element, 0));
        return element;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        // TODO: Replace with your code

        for (int i=0; i < elements.size(); i++)
        {
            PriorityNode<E> curr = elements.get(i);
            if (curr.getElement().equals(element))
            {
                curr.setPriority(priority);
            }
        }


        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int size() {
        // TODO: Replace with your code
        return elements.size();
        //throw new UnsupportedOperationException("Not implemented yet");
    }
}
