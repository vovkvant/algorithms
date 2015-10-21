package collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by vovkvant on 22.02.2015.
 */
public class BruteCollinearPoints {

    private List<LineSegment> lineSegmentArrayList;

    public BruteCollinearPoints(Point[] points) {
        //take an array of points
        //process the points
        //p, q, r, and s
        lineSegmentArrayList = new ArrayList<LineSegment>();
        for (int k1 = 0; k1 < points.length; k1++) {
            for (int k2 = k1 + 1; k2 < points.length; k2++) {
                for (int k3 = k2 + 1; k3 < points.length; k3++) {
                    for (int k4 = k3 + 1; k4 < points.length; k4++) {
                        Point p = points[k1];
                        Point q = points[k2];
                        Point r = points[k3];
                        Point s = points[k4];
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            // this is line
                            p.drawTo(q);
                            q.drawTo(r);
                            r.drawTo(s);
                            //must be ordered sequence
                            Point[] tempArray = {p, q, r, s};
                            Arrays.sort(tempArray);
                            LineSegment lineSegment = new LineSegment(tempArray[0], tempArray[3]);
                            lineSegmentArrayList.add(lineSegment);
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegmentArrayList.size();
    }

    public LineSegment[] segments() {
        return lineSegmentArrayList.toArray(new LineSegment[lineSegmentArrayList.size()]);
    }
}
