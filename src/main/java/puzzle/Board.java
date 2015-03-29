package puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 * User: vovkvant
 * Date: 27.03.15
 */
public class Board {

    private final int[][] blocks;

    public Board(final int[][] blocks) {
        this.blocks = blocks;
    }



    // board dimension N
    public int dimension() {
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                int currentIndex = i * dimension() + j + 1;
                //easy case
                if (blocks[i][j] != currentIndex && blocks[i][j] != 0) {
                    hamming++;
                }
            }
        }

        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                int current = blocks[i][j];
                if (current != 0) {
                    int cur_x = getXFromIndex(current);
                    int cur_y = getYFromIndex(current);
                    manhattan += (Math.abs(j - cur_x) + Math.abs(i - cur_y));
                }
            }
        }
        return manhattan;
    }

    private int getXFromIndex(int index) {
        return (index - 1) % dimension();
    }

    private int getYFromIndex(int index) {
        index--;
        return (index - index % dimension()) / dimension();
    }

    public boolean isGoal() {
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                int currentIndex = i * dimension() + j + 1;
                if (currentIndex == dimension() * dimension()) {
                    return true;
                }
                if (blocks[i][j] != currentIndex) {
                    return false;
                }
            }
        }
        return true;
    }

    // a boadr that is obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int row = 0;
        if (blocks[row][0] != 0 && blocks[row][1] != 0) {
            return swap(row);
        } else {
            row++;
            return swap(row);
        }
    }

    private Board swap(int row) {
        int N = dimension();
        int[][] copyBlocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                copyBlocks[i][j] = blocks[i][j];

        int swap = copyBlocks[row][0];
        copyBlocks[row][0] = copyBlocks[row][1];
        copyBlocks[row][1] = swap;

        return new Board(copyBlocks);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this)
            return true;

        if (y instanceof Board) {
            Board anotherBoard = (Board) y;
            if (anotherBoard.dimension() != this.dimension()) {
                return false;
            }

            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    if (this.blocks[i][j] != anotherBoard.blocks[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<Board>();
        int x0 = 0;
        int y0 = 0;
        int size = dimension();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] == 0) {
                    y0 = i;
                    x0 = j;
                }
            }
        }
        if (x0 + 1 < size) {
            neighbors.add(swap(x0, y0, x0 + 1, y0));
        }
        if (x0 - 1 >= 0) {
            neighbors.add(swap(x0, y0, x0 - 1, y0));
        }
        if (y0 + 1 < size) {
            neighbors.add(swap(x0, y0, x0, y0 + 1));
        }
        if (y0 - 1 >= 0) {
            neighbors.add(swap(x0, y0, x0, y0 - 1));
        }

        return neighbors;
    }

    private Board swap(int x0, int y0, int x1, int y1) {
        int N = dimension();
        int[][] copyBlocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                copyBlocks[i][j] = blocks[i][j];

        int swap = copyBlocks[y1][x1];
        copyBlocks[y0][x0] = swap;
        copyBlocks[y1][x1] = 0;

        return new Board(copyBlocks);
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        sb.append(dimension());
        sb.append("\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                sb.append(blocks[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
    }
}
