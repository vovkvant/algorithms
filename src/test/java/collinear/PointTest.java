package collinear;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by vovkvant on 22.02.2015.
 */
public class PointTest {

    @Test
    public void testPointEquals() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 2);
        Assert.assertEquals(0, p1.compareTo(p2));

        p2 = new Point(3, 3);
        Assert.assertEquals(-1, p1.compareTo(p2));

        p2 = new Point(1, 1);
        Assert.assertEquals(1, p1.compareTo(p2));

        p2 = new Point(3, 2);
        Assert.assertEquals(-1, p1.compareTo(p2));

        p2 = new Point(1, 2);
        Assert.assertEquals(1, p1.compareTo(p2));

    }

    @Test
    public void testPointSlope(){
        Point p1 = new Point(2, 2);
        Point p2;

        p2 = new Point(3, 3);
        Assert.assertEquals(1.0, p1.slopeTo(p2), 0.0);

        p2 = new Point(3, 2);
        Assert.assertEquals(0.0, p1.slopeTo(p2), 0.0);

        p2 = new Point(1, 2);
        Assert.assertEquals(0.0, p1.slopeTo(p2), 0.0);

        p2 = new Point(2, 2);
        Assert.assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p2), 0.0);

        p2 = new Point(2, 3);
        Assert.assertEquals(Double.POSITIVE_INFINITY, p1.slopeTo(p2), 0.0);
    }

    @Test
    public void testPointComparator(){

    }
}
