package queues;

import api.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * User: vovkvant
 * Date: 16.02.15
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int last = 0;
    private int size = 16;

    public RandomizedQueue() {
        queue = (Item[]) (new Object[size]);
    }

    public boolean isEmpty() {
        return last == 0;
    }

    public int size() {
        return last;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        queue[last] = item;
        last++;
        if (last > (size / 2)) {
            size = size * 2;
            Item[] newQueue = (Item[]) (new Object[size]);
            for (int k = 0; k < size / 2; k++) {
                newQueue[k] = queue[k];
            }
            queue = newQueue;
        }
    }

    public Item dequeue() {
        if (last == 0) {
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniform(0, last);
        Item item = queue[random];

        queue[random] = null;
        Item[] newQueue = (Item[]) (new Object[size]);
        for (int k = 0; k < random; k++) {
            newQueue[k] = queue[k];
        }
        for (int k = random; k < size - 1; k++) {
            newQueue[k] = queue[k + 1];
        }
        queue = newQueue;
        last--;
        return item;
    }

    public Item sample() {
        if (last == 0) {
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniform(0, last);
        return queue[random];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>();
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private int iterator = 0;

        @Override
        public boolean hasNext() {
            return iterator < last;
        }

        @Override
        public Item next() {
            Item item = (Item) queue[iterator];
            iterator++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String args[]) {
    }

}
