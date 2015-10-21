package collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;


/**
 * Created by vovkvant on 22.02.2015.
 */
public class BruteCollinearPoints {

    private Point[] pointArray;
    private int last;

    public BruteCollinearPoints(int N) {
        pointArray = new Point[N];
        last = 0;
    }

    public void addPoint(int x, int y) {
        Point p = new Point(x, y);
        p.draw();
        pointArray[last] = p;
        last++;
        p.draw();
        LineSegment ls;
    }

    public void startProcess() {
        //take an array of points
        //process the points
        //p, q, r, and s
        for(int k1 = 0; k1< last; k1++){
            for(int k2 = k1+1; k2< last; k2++){
                for(int k3 = k2+1; k3< last; k3++){
                    for(int k4 = k3+1; k4< last; k4++){
                        Point p = pointArray[k1];
                        Point q = pointArray[k2];
                        Point r = pointArray[k3];
                        Point s = pointArray[k4];
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            // this is line
                            p.drawTo(q);
                            q.drawTo(r);
                            r.drawTo(s);
                            //must be ordered sequence
                            Point[] tempArray = {p, q, r, s};
                            Arrays.sort(tempArray);
                            printPoints(tempArray);
                        }
                    }
                }
            }
        }
    }

    private void printPoints(Point[] tempArray){
        for(int k=0;k<tempArray.length;k++){
            System.out.print(tempArray[k].toString());
            if(k!=tempArray.length-1){
                System.out.print(" -> ");
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
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
    }

}
