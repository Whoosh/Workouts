package coursera.algs_1.que;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Element element;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        final Element upperElement = prepareAdd(item);
        if (size == 0) {
            upperElement.last = upperElement;
        } else {
            element.prev = upperElement;
            upperElement.last = size == 1 ? element : element.last;
            upperElement.next = element;
        }
        element = upperElement;
        size++;
    }

    public void addLast(Item item) {
        final Element bottomElement = prepareAdd(item);
        if (size == 0) {
            addFirst(item);
        } else if (element.next == null) {
            bottomElement.prev = element;
            element.next = bottomElement;
            element.last = bottomElement;
            size++;
        } else {
            bottomElement.prev = element.last;
            element.last.next = bottomElement;
            element.last = bottomElement;
            size++;
        }
    }

    public Item removeFirst() {
        prepareRemove();
        final Element res = element;
        if (size == 1) {
            element = null;
        } else {
            element.next.last = element.last;
            element = element.next;
            element.prev = null;
        }
        size--;
        return res.item;
    }

    public Item removeLast() {
        final Element last = element.last;
        prepareRemove();
        if (size == 1) {
            return removeFirst();
        } else {
            element.last = element.last.prev;
            element.last.next = null;
            size--;
        }
        return last.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Element point = element;

            @Override
            public boolean hasNext() {
                return point != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                final Element next = point;
                point = point.next;
                return next.item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void prepareRemove() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private Element prepareAdd(Item item) {
        if (item == null) throw new NullPointerException();
        return new Element(item);
    }

    private class Element {
        private Element next;
        private Element prev;
        private Element last;
        private Item item;

        public Element(Item item) {
            this.item = item;
        }
    }
}