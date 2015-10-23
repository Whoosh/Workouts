package coursera.algs_1.que;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int head;
    private int end;

    public RandomizedQueue() {
        items = (Item[]) new Object[0];
    }

    public boolean isEmpty() {
        return items.length == 0;
    }

    public int size() {
        return head + end;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (items.length == size()) increaseSize();
        if (flipCoin()) items[items.length - 1 - end++] = item;
        else items[head++] = item;
    }

    public Item dequeue() {
        checkBeforeGet();
        if (size() <= items.length / 4) decreaseSize();
        if (flipCoin()) return end != 0 ? getFromEnd() : getFromHead();
        return head == 0 ? getFromEnd() : getFromHead();
    }

    public Item sample() {
        checkBeforeGet();
        if (flipCoin() || end == 0) return items[StdRandom.uniform(head)];
        return items[items.length - 1 - StdRandom.uniform(0, end)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Item[] iItems = makeTrimCopy();
            private int iEnd = StdRandom.uniform(iItems.length);
            private int iHead = iItems.length - iEnd;

            @Override
            public boolean hasNext() {
                return iHead + iEnd != 0;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                return iHead > 0 ? iItems[--iHead] : iItems[iItems.length - 1 - --iEnd];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Item[] makeTrimCopy() {
        final Item[] iteratorItems = (Item[]) new Object[size()];
        for (int i = 0, k = 0; i < items.length; i++)
            if (items[i] != null) iteratorItems[k++] = items[i];
        return iteratorItems;
    }

    private void decreaseSize() {
        final Item[] newItems = (Item[]) new Object[size() * 2];
        end = head = 0;
        for (Item item : items)
            if (item != null) newItems[head++] = item;
        items = newItems;
    }

    private void increaseSize() {
        final int newSize = items.length == 0 ? 1 : items.length * 2;
        final Item[] newItems = (Item[]) new Object[newSize];
        for (end = head = 0; head < items.length; head++)
            newItems[head] = items[head];
        items = newItems;
    }

    private Item getFromHead() {
        final Item buffer = items[--head];
        items[head] = null;
        return buffer;
    }

    private Item getFromEnd() {
        final int index = items.length - 1 - --end;
        final Item buffer = items[index];
        items[index] = null;
        return buffer;
    }

    private void checkBeforeGet() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private boolean flipCoin() {
        return (StdRandom.uniform(items.length) & 1) == 0;
    }
}
