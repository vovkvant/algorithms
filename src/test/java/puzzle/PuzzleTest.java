package puzzle;

import api.In;
import api.StdOut;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

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

}
