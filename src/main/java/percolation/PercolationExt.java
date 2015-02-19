package percolation;

import api.QuickFindUF;

/**
 * User: vovkvant
 * Date: 10.02.15
 */
public class PercolationExt {

    private QuickFindUF uf;
    private int N;

    private Site[][] siteArray;

    public PercolationExt(int N) {
        this.N = N;
        siteArray = new Site[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                siteArray[i][j] = new Site();
            }
        }

        uf = new QuickFindUF(N * N);
    }


    public void open(int i, int j) {
        if (i == 1) {
            getSite(i, j).isFull = true;
            if (isOpen(i + 1, j)) {

                int p = getIndex(i, j);
                int q = getIndex(i + 1, j);
                uf.union(p, q);
                for (int k = 0; k < (N * N); k++) {
                    if (uf.find(k) == uf.find(q)) {
                        int dj = 1 + k % N;
                        int di = 1 + (k - k % N) / N;
                        getSite(di, dj).isFull = true;
                    }
                }
            }
        }
        //--------------------------------union------------------------
        if (i + 1 <= N) {
            int p = getIndex(i, j);
            checkIsOpenAndFull(p, i + 1, j);
        }
        if (i - 1 > 0) {
            int p = getIndex(i, j);
            checkIsOpenAndFull(p, i - 1, j);
        }
        if (j + 1 <= N) {
            int p = getIndex(i, j);
            checkIsOpenAndFull(p, i, j + 1);
        }
        if (j - 1 > 0) {
            int p = getIndex(i, j);
            checkIsOpenAndFull(p, i, j - 1);
        }

        getSite(i, j).isOpen = true;

    }


    private void checkIsOpenAndFull(int p, int i, int j) {
        if (isOpen(i, j)) {
            int q = getIndex(i, j);
            uf.union(p, q);
            //uf.union(q, p);
            if (isFull(i, j)) {
                for (int k = 0; k < (N * N); k++) {
                    if (uf.find(k) == uf.find(q)) {
                        int dj = k % N + 1;
                        int di = 1 + (k - k % N) / N;
                        getSite(di, dj).isFull = true;
                    }
                }
            }
        }
    }

    private int getIndex(int i, int j) {
        return (i - 1) * N + (j - 1);
    }

    public boolean isOpen(int i, int j) {
        return getSite(i, j).isOpen;
    }


    public boolean isFull(int i, int j) {
        return getSite(i, j).isFull;
    }

    public Site getSite(int i, int j) {
        i--;
        j--;
        return siteArray[i][j];
    }

    public boolean percolates() {
        for (int p = 0; p < N; p++) {
            for (int q = N * N - N; q < N * N; q++) {
                if (uf.find(p) == uf.find(q)) {
                    return true;
                }
            }

        }
        return false;
    }

    class Site {
        public boolean isOpen;
        public boolean isFull;

        public Site() {
            isOpen = false;
            isFull = false;
        }
    }

    public static void main(String args[]) {
    }

}
