package puzzle;

import api.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * User: vovkvant
 * Date: 27.03.15
 * 8puzzle
 */
public class Solver {

    private boolean isSolvable;
    private MinPQ<BoardNode> boardQueue;
    private MinPQ<BoardNode> swapedBoardQueue;
    private BoardNode boardNode;
    private ArrayList<Board> list = new ArrayList<Board>();

    public Solver(Board initial) {
        boardNode = new BoardNode(initial, null, -1);
        isSolvable = false;
        boardQueue = new MinPQ<BoardNode>(new BoardComparator());
        swapedBoardQueue = new MinPQ<BoardNode>(new BoardComparator());
        findSolution();
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return boardNode.getMoveCounter();
    }

    public Iterable<Board> solution() {
        if (isSolvable) {
            BoardNode currentBoardNode = boardNode;
            ArrayList<Board> list = new ArrayList<Board>();

            while(currentBoardNode.getRootBoardNode()!=null){
                list.add(currentBoardNode.getBoard());
                currentBoardNode = currentBoardNode.getRootBoardNode();
            }

            return list;
        } else {
            return null;
        }
    }


    private void findSolution() {
        Board swapedBoard = boardNode.getBoard().twin();
        while (!boardNode.getBoard().isGoal()) {
            boardNode = solutionStep(boardNode, false);
            //swapedBoard = solutionStep(swapedBoard, true);
        }
        if (boardNode.getBoard().isGoal()) {
            isSolvable = true;
        } else {

        }
    }

    private BoardNode solutionStep(BoardNode boardNode, boolean isSwaped) {
        Iterator<Board> boardIterator = boardNode.getBoard().neighbors().iterator();

        while (boardIterator.hasNext()) {
            Board nextBoard = boardIterator.next();
            if (!boardNode.getBoard().equals(nextBoard)) {
                if (isSwaped) {

                } else {
                    if (!list.contains(nextBoard)) {
                        boardQueue.insert(new BoardNode(nextBoard, boardNode, boardNode.getMoveCounter()));
                    }
                }
            }
        }

        if (isSwaped) {

        } else {
            BoardNode minBoardNode = boardQueue.min();
            //System.out.println(minBoardNode.getBoard().manhattan());
            list.add(minBoardNode.getBoard());
            boardNode = minBoardNode;

            boardQueue.delMin();
        }

        return boardNode;
    }

    private class BoardComparator implements Comparator<BoardNode> {
        @Override
        public int compare(BoardNode o1, BoardNode o2) {
            /*
            if (o1.getBoard().manhattan() > o2.getBoard().manhattan())
                return 1;
            else if (o1.getBoard().manhattan() == o2.getBoard().manhattan())
                return 0;
            else
                return -1;
                */
            if (o1.getPriority() > o2.getPriority())
                return 1;
            else if (o1.getPriority() == o2.getPriority())
                return 0;
            else
                return -1;

        }
    }

    private class BoardNode {
        private Board board;
        private BoardNode rootBoardNode;
        private int moveCounter;

        private BoardNode(Board board, BoardNode rootBoardNode, int moveCounter) {
            this.board = board;
            this.rootBoardNode = rootBoardNode;
            this.moveCounter = moveCounter + 1;
        }

        private Board getBoard() {
            return board;
        }

        private BoardNode getRootBoardNode() {
            return rootBoardNode;
        }

        private int getMoveCounter() {
            return moveCounter;
        }

        public int getPriority(){
            return board.manhattan() + moveCounter;
        }
    }

    public static void main(String[] args) {
    }

}
