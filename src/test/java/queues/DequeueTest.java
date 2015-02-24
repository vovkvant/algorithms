package queues;


import org.junit.Assert;
import org.junit.Test;

/**
 * User: vovkvant
 * Date: 20.02.15
 */
public class DequeueTest {

    @Test
    public void testDequeue(){
        Deque<Integer> deque = new Deque<Integer>();

        Assert.assertEquals(true, deque.isEmpty());
        deque.addFirst(1);
        deque.addFirst(2);
        deque.removeFirst();
        deque.removeFirst();
        Assert.assertEquals(true, deque.isEmpty());
        deque.addFirst(5);
        deque.removeFirst();
        Assert.assertEquals(true, deque.isEmpty());

        deque.addFirst(1);
        deque.removeLast();
        Assert.assertEquals(true, deque.isEmpty());

        deque.addLast(1);
        deque.removeLast();
        Assert.assertEquals(true, deque.isEmpty());

        deque.addLast(1);
        deque.removeFirst();
        Assert.assertEquals(true, deque.isEmpty());

        deque.addFirst(2);
        deque.addLast(3);
        deque.removeFirst();
        deque.removeLast();
        Assert.assertEquals(true, deque.isEmpty());
        Assert.assertEquals(0, deque.size());
    }


}
