package puzzle;

import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: vovkvant
 * Date: 27.03.15
 * Time: 23:44
 * To change this template use File | Settings | File Templates.
 */
public class PuzzleTest {

    private File getInputTestFile(String name) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(name).getPath().replace("%20", " "));
        System.out.println(file.getName() + " is loaded");
        return file;
    }

    private Board getBoardFromFile(String fileName) {
        File file = getInputTestFile(fileName);

        In in = new In(file);

        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        return initial;
    }


    @Test
    public void testIsGoal() {
        Board board = getBoardFromFile("puzzle/puzzle00.txt");
        Assert.assertTrue(board.isGoal());
        int manhattan = board.manhattan();
        Assert.assertEquals("Wrong mmanhattan: " + manhattan, 0, manhattan);
        int hamming = board.hamming();
        Assert.assertEquals("Wrong hamming: " + hamming, 0, hamming);

        board = getBoardFromFile("puzzle/puzzle01.txt");
        Assert.assertFalse(board.isGoal());
        manhattan = board.manhattan();
        Assert.assertEquals("Wrong mmanhattan: " + manhattan, 1, manhattan);
        hamming = board.hamming();
        Assert.assertEquals("Wrong hamming: " + hamming, 1, hamming);

        board = getBoardFromFile("puzzle/puzzle02.txt");
        Assert.assertFalse(board.isGoal());
        manhattan = board.manhattan();
        Assert.assertEquals("Wrong mmanhattan: " + manhattan, 2, manhattan);
        hamming = board.hamming();
        Assert.assertEquals("Wrong hamming: " + hamming, 2, hamming);


        //test for neighbors
        board = getBoardFromFile("puzzle/puzzle08.txt");
        System.out.println(board);
        Iterator<Board> boardIterator = board.neighbors().iterator();
        while (boardIterator.hasNext()) {
            Board next = boardIterator.next();
            System.out.println("Manhattan: " + next.manhattan());
            System.out.println(next.toString());
        }


        /*
        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        */
    }

    @Test
    public void testSolver1() {
        Board board = getBoardFromFile("puzzle/puzzle04.txt");
        Solver solver = new Solver(board);
        Assert.assertTrue(solver.isSolvable());
        Assert.assertEquals(4, solver.moves());

        board = getBoardFromFile("puzzle/puzzle08.txt");
        solver = new Solver(board);
        Assert.assertTrue(solver.isSolvable());
        Assert.assertEquals(9, solver.moves());

    }

    @Test
    public void testSolver() {
        //Board board = getBoardFromFile("puzzle/puzzle3x3-unsolvable.txt");
        Board board = getBoardFromFile("puzzle/puzzle50.txt");
        //board = board.twin();
        Solver solver = new Solver(board);
        if (solver.isSolvable()) {
            System.out.println(solver.moves());

            Iterator<Board> solveIterator = solver.solution().iterator();
            while (solveIterator.hasNext()) {
                Board next = solveIterator.next();
                System.out.println("Manhattan: " + next.manhattan());
                System.out.println(next.toString());
            }
        } else {
            System.out.println("No solution");
        }
    }

}
