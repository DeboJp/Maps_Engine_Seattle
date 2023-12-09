package minpq;

import java.util.*;

/**
 * Optimized binary heap implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class OptimizedHeapMinPQ<E> implements MinPQ<E> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the heap of element-priority pairs.
     */
    private final List<PriorityNode<E>> elements;
    /**
     * {@link Map} of each element to its associated index in the {@code elements} heap.
     */
    private final Map<E, Integer> elementsToIndex;

    /**
     * Constructs an empty instance.
     */
    public OptimizedHeapMinPQ() {
        elements = new ArrayList<>();
        elementsToIndex = new HashMap<>();
    }

    private void swim(int index)
    {
        int parentIndex = (index)/2;
        while (index > 1 && elements.get(parentIndex).getPriority() > elements.get(index).getPriority())
        {
            PriorityNode<E> temp = elements.get(parentIndex);
            elements.set(parentIndex, elements.get(index));
            elements.set(index, temp);

            elementsToIndex.put(elements.get(index).getElement(), index);
            elementsToIndex.put(elements.get(parentIndex).getElement(), parentIndex);

            index = parentIndex;
            parentIndex = (index)/2;
        }

    }


    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        // TODO: Replace with your code

        if (elements.isEmpty()) { elements.add(null); }

        elements.add(new PriorityNode<>(element, priority));
        elementsToIndex.put(element, elements.size() - 1);
        swim(elements.size() - 1);

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean contains(E element) {
        // TODO: Replace with your code

        return elementsToIndex.containsKey(element);

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code

        return elements.get(1).getElement();

        //throw new UnsupportedOperationException("Not implemented yet");
    }


    private void sink(int index)
    {
        while (2*index <= elements.size() - 1)
        {
            int leftChild = 2*index ;
            int rightChild = 2*index + 1;
            int toSwap = 1;
            if ( rightChild > elements.size() - 1 || (elements.get(leftChild).getPriority() <  elements.get(rightChild).getPriority()) )
            {
                toSwap = leftChild;
            }
            else { toSwap = rightChild; }

            if ( elements.get(index).getPriority() > elements.get(toSwap).getPriority())
            {
                PriorityNode<E> temp = elements.get(index);
                elements.set(index, elements.get(toSwap));
                elements.set(toSwap, temp);

                elementsToIndex.put(elements.get(index).getElement(), index);
                elementsToIndex.put(elements.get(toSwap).getElement(), toSwap);

            }

            index = toSwap;
        }
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code

        E min = elements.get(1).getElement();
        elements.set(1, elements.get(elements.size() - 1));
        elements.remove(elements.size() - 1);
        elementsToIndex.remove(min);

        if (elements.size() > 1)
        {
            elementsToIndex.put(elements.get(1).getElement(), 1);
            sink(1);
        }


        return min;

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        // TODO: Replace with your code

        int index = elementsToIndex.get(element);
        double prevPriority = elements.get(index).getPriority();
        elements.get(index).setPriority(priority);

        if (prevPriority < priority)
        {
            sink(index);
        }
        else
        {
            swim(index);
        }

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int size() {
        // TODO: Replace with your code
        return elements.size() - 1;
        //throw new UnsupportedOperationException("Not implemented yet");
    }
}
