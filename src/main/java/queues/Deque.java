package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * User: vovkvant
 * Date: 15.02.15
 */
public class Deque<Item> implements Iterable<Item> {

    private DequeueItem first;
    private DequeueItem last;

    public Deque() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        if (first == null && last == null) {
            return true;
        }
        return false;
    }

    public int size() {
        int size = 0;
        DequeueItem current = first;
        while (current != null) {
            current = current.getNext();
            size++;
        }
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        DequeueItem di = new DequeueItem(item, first, null);
        if (first != null) {
            first.previous = di;
        }
        first = di;
        //значит вставили только первый элемент
        if (last == null) {
            last = di;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        DequeueItem di = new DequeueItem(item, null, last);
        if (last != null) {
            last.next = di;
        }
        last = di;
        //значит вставили только первый элемент
        if (first == null) {
            first = di;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        DequeueItem temp = first;
        first = first.getNext();
        if (first != null) {
            first.previous = null;
        } else {
            last = null;
        }
        return (Item) temp.getItem();
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        DequeueItem temp = last;
        last = last.getPrevious();
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        return (Item) temp.getItem();
    }

    public Iterator<Item> iterator() {
        return new DequeueItemIterator(first);
    }

    private class DequeueItemIterator<Item> implements Iterator<Item> {
        private DequeueItem iterator;

        public DequeueItemIterator(DequeueItem first) {
            iterator = first;
        }

        @Override
        public boolean hasNext() {
            return iterator != null;
        }

        @Override
        public Item next() {
            if (iterator.getNext() == null) {
                new NoSuchElementException();
            }
            Item result = (Item) iterator.getItem();
            iterator = iterator.getNext();
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class DequeueItem<Item> {
        private Item item;
        private DequeueItem next;
        private DequeueItem previous;

        public DequeueItem(Item item, DequeueItem next, DequeueItem previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }

        public DequeueItem getNext() {
            return next;
        }

        public DequeueItem getPrevious() {
            return previous;
        }

        public Item getItem() {
            return item;
        }
    }

    public static void main(String args[]) {
        Deque<String> dequeue = new Deque<String>();
        dequeue.addFirst("002");
        dequeue.addFirst("003");
        dequeue.addFirst("004");
        dequeue.addFirst("005");
        dequeue.addFirst("006");
        dequeue.addLast("001");

        Iterator<String> iterator = dequeue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Size = " + String.valueOf(dequeue.size()));

        System.out.println("Remove first: " + dequeue.removeFirst());
        System.out.println("Remove last: " + dequeue.removeLast());

        System.out.println("Size = " + String.valueOf(dequeue.size()));
        iterator = dequeue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
