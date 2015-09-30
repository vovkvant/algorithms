package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * User: vovkvant
 * Date: 14.02.15
 */
public class PercolationStats {

    private double[] result;
    private int T;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        result = new double[T];

        for (int k = 0; k < T; k++) {
            Percolation percolation1 = new Percolation(N);
            double count = 0d;
            boolean percolate = false;
            while (!percolate) {
                int num = StdRandom.uniform(0, N * N);
                int i = num / N + 1;
                int j = num % N + 1;
                if(!percolation1.isOpen(i, j)){
                    percolation1.open(i, j);
                    count++;
                    if (percolation1.percolates()) {
                        percolate = true;
                    }
                }
            }
            result[k] = count / ((double) (N * N));
        }
    }

    public double mean() {
        return StdStats.mean(result);
    }

    public double stddev() {
        return StdStats.stddev(result);
    }

    public double confidenceLo() {
        return StdStats.mean(result) - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHi() {
        return StdStats.mean(result) + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        Stopwatch sw = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(N, T);
        System.out.println("mean = " + String.valueOf(percolationStats.mean()));
        System.out.println("stddev = " + String.valueOf(percolationStats.stddev()));
        System.out.println("95% confidence interval = " + String.valueOf(percolationStats.confidenceLo()) + ", " + String.valueOf(percolationStats.confidenceHi()));
        System.out.println(sw.elapsedTime());
    }
}
