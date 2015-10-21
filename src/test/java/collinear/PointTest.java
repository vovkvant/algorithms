package collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
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
    public void testBrute(){
        //In in = new In(this.getClass().getResource("/collinear/grid4x4.txt"));      // input file
        In in = new In(this.getClass().getResource("/collinear/horizontal5.txt"));      // input file
        int N = in.readInt();

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(N);
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();
            bruteCollinearPoints.addPoint(x, y);
        }
        bruteCollinearPoints.startProcess();
        System.out.println("BruteCollinearPoints force is finished!");
        try {
            Thread.sleep(100000);
        }catch (Exception e){

        }
    }




    @Test
    public void testFastCollinearPoints(){
        //In in = new In(this.getClass().getResource("/collinear/grid4x4.txt"));      // input file
        In in = new In(this.getClass().getResource("/collinear/rs1423.txt"));      // input file
        int N = in.readInt();

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        System.out.println("BruteCollinearPoints force is finished!");
        try {
            Thread.sleep(100000);
        }catch (Exception e){

        }
    }

    @Test
    public void testPointComparator(){

    }
}
