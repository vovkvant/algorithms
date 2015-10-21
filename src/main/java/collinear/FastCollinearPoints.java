package collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vovkvant on 22.02.2015.
 */
public class FastCollinearPoints {

    private List<LineSegment> lineSegmentArrayList;

    private class PointWithSlope implements Comparable<PointWithSlope> {
        private Point origin;
        private double slope;

        public PointWithSlope(Point origin, double slope) {
            this.origin = origin;
            this.slope = slope;
        }

        public double getSlope() {
            return slope;
        }

        public Point getPoint() {
            return origin;
        }

        @Override
        public int compareTo(PointWithSlope that) {
            if (this.slope > that.slope) {
                return 1;
            } else if (this.slope < that.slope) {
                return -1;
            }
            return 0;
        }
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        lineSegmentArrayList = new ArrayList<LineSegment>();
        if (points.length > 2) {
            for (int j = 0; j < points.length; j++) {
                PointWithSlope[] slopesArray = new PointWithSlope[points.length];
                for (int k = 0; k < points.length; k++) {
                    slopesArray[k] = new PointWithSlope(points[k], points[k].slopeTo(points[j]));
                }
                Arrays.sort(slopesArray);

                for (int k = 0; k < slopesArray.length - 3; k++) {
                    if (slopesArray[k].getSlope() == slopesArray[k + 1].getSlope() &&
                            slopesArray[k + 1].getSlope() == slopesArray[k + 2].getSlope()) {

                        Point[] pointArray = new Point[4];
                        pointArray[0] = points[j];
                        pointArray[1] = slopesArray[k].getPoint();
                        pointArray[2] = slopesArray[k + 1].getPoint();
                        pointArray[3] = slopesArray[k + 2].getPoint();
                        Arrays.sort(pointArray);
                        LineSegment lineSegment = new LineSegment(pointArray[0], pointArray[3]);
                        lineSegmentArrayList.add(lineSegment);
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
