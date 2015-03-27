package puzzle;

import api.MinPQ;

/**
 * User: vovkvant
 * Date: 27.03.15
 */
public class Solver {

    private Board board;
    private boolean isSolvable;
    private int moveCounter;
    private MinPQ<Board> boardQueue;

    public Solver(Board initial) {
        this.board = initial;
        isSolvable = false;
        moveCounter = 0;
        boardQueue = new MinPQ<Board>();
        findSolution();
    }

    public boolean isSolvable() {
        return isSolvable;
    }            // is the initial board solvable?

    public int moves() {
        return moveCounter;
    }                     // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution() {
        if (isSolvable) {
            return boardQueue;
        } else {
            return null;
        }
    }


    private void findSolution() {
        // находим null
        // вычисляем индексы ближайших возможных позиций
        // смоторим две возможные дальнейшие позиции
        // кладем одну из двух/трех в очередь
    }

    public static void main(String[] args) {
    }

}
