package percolation;

import api.WeightedQuickUnionUF;

/**
 * User: vovkvant
 * Date: 10.02.15
 */
public class Percolation {

    private WeightedQuickUnionUF uf;
    private int N;

    private boolean[][] siteArray;
    private boolean percolates = false;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        siteArray = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                siteArray[i][j] = false;
            }
        }

        uf = new WeightedQuickUnionUF(N * N);

        for (int k = 0; k < N - 1; k++) {
            uf.union(k, k + 1);
        }
        /*
        for (int k = N * N - 1; k > N * N - N; k--) {
            uf.union(k, k - 1);
        }
        */

    }


    public void open(int i, int j) {
        setSiteOpen(i, j);
        if (i + 1 <= N) {
            int p = getIndex(i, j);
            checkIsOpen(p, i + 1, j);
        }
        if (i - 1 > 0) {
            int p = getIndex(i, j);
            checkIsOpen(p, i - 1, j);
        }
        if (j + 1 <= N) {
            int p = getIndex(i, j);
            checkIsOpen(p, i, j + 1);
        }
        if (j - 1 > 0) {
            int p = getIndex(i, j);
            checkIsOpen(p, i, j - 1);
        }

        if (i == N) {
            int value = uf.find(0);
            int p = getIndex(i, j);
            if (uf.find(p) == value)
                percolates = true;
        }
    }

    public boolean isOpen(int i, int j) {
        return getSite(i, j);
    }

    public boolean isFull(int i, int j) {
        int value = uf.find(0);
        if (isOpen(i, j)) {
            int q = getIndex(i, j);
            if (uf.find(q) == value) {
                return true;
            }
        }
        return false;
    }

    public boolean percolates() {
        if(percolates){
            return true;
        }
        else{
            int value = uf.find(0);
            int i = N;
            for (int j = 1; j <= N; j++) {
                if (getSite(i, j)) {
                    int p = getIndex(i, j);
                    if (uf.find(p) == value){
                        percolates = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean getSite(int i, int j) {
        return siteArray[i - 1][j - 1];
    }

    private void setSiteOpen(int i, int j) {
        siteArray[i - 1][j - 1] = true;
    }

    private int getIndex(int i, int j) {
        return (i - 1) * N + (j - 1);
    }

    private void checkIsOpen(int p, int i, int j) {
        if (isOpen(i, j)) {
            int q = getIndex(i, j);
            uf.union(p, q);
        }
    }


    public static void main(String[] args) {
    }

}
