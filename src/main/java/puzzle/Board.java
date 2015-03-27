package puzzle;

import java.util.Iterator;

/**
 * User: vovkvant
 * Date: 27.03.15
 */
public class Board {

    private int[][] blocks;

    public Board(int[][] blocks) {
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
        return this;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if(y == this)
            return true;

        if(y instanceof Board){
            Board anotherBoard = (Board) y;
            if(anotherBoard.dimension()!=this.dimension()){
                return false;
            }

            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    if(this.blocks[i][j] != anotherBoard.blocks[i][j]){
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
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return null;
            }
        };
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        return "1";
    }

    public static void main(String[] args) {
    }
}
