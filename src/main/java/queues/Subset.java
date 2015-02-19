package queues;

import api.StdIn;

/**
 * User: vovkvant
 * Date: 16.02.15
 */
public class Subset {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        String element = null;
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        for (int k = 0; k < N; k++) {
            System.out.println(queue.dequeue());
        }

    }

}