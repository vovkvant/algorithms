package collinear;

/**
 * Created by vovkvant on 22.02.2015.
 */
public class Brute {

    private Point[] pointArray;
    private int last;

    public Brute(int N){
        pointArray = new Point[N];
        last = 0;
    }

    public void addPoint(int x, int y){
        Point p = new Point(x, y);
        pointArray[last] = p;
        last++;
        p.draw();
    }

    public void startProcess(){
        //take an array of ppoints
        //process the points
        //p, q, r, and s
        for (int k = 0; k < last-4; k++) {
            Point p = pointArray[k];
            Point q = pointArray[k+1];
            Point r = pointArray[k+2];
            Point s = pointArray[k+4];
            if(p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)){
                // this is line
                p.drawTo(q);
                q.drawTo(r);
                r.drawTo(s);
                //must be ordered sequence
                Point[] tempArray = {p, q, r, s};
                sortTempArray(tempArray);
                //etc
                System.out.println();
            }
        }
    }

    private void sortTempArray(Point[] tempArray){

    }

    public static void main(String[] args){

    }

}
