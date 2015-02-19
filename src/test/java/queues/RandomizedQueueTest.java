package queues;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: vovkvant
 * Date: 20.02.15
 */
public class RandomizedQueueTest {

    @Test
    public void testRandomizedQueue(){
        //Make list
        Matcher[] expectedValuesArray = new Matcher[]{containsString("001"), containsString("002"), containsString("003"), containsString("004")};

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        randomizedQueue.enqueue("001");
        randomizedQueue.enqueue("002");
        randomizedQueue.enqueue("003");
        randomizedQueue.enqueue("004");

        String sample = randomizedQueue.sample();
        System.out.println("Just sample: "+ sample);
        assertThat(sample, anyOf(expectedValuesArray));

        assertEquals(4, randomizedQueue.size());

        System.out.println("Checking iterator:");
        Iterator<String> iterator = randomizedQueue.iterator();
        while(iterator.hasNext()){
            String item = iterator.next();
            System.out.println(item);
        }


        String removed = randomizedQueue.dequeue();
        System.out.println("Removed element:" + removed);
        assertThat(removed, anyOf(expectedValuesArray));
        assertEquals(3, randomizedQueue.size());

        removed = randomizedQueue.dequeue();
        System.out.println("Removed element:" + removed);
        assertThat(removed, anyOf(expectedValuesArray));
        assertEquals(2, randomizedQueue.size());

        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        assertEquals(0, randomizedQueue.size());
    }

}
