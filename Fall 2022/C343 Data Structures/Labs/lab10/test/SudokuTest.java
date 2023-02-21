import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuTest {
    @Test
    void isBlank() throws FileNotFoundException {
        Scanner s = new Scanner(new File("Sudoku_1"));
        Sudoku board = Sudoku.read(s);
        System.out.println(board.toString());

        assertFalse(board.isBlank(0,0));
        assertTrue(board.isBlank(1,0));
        assertTrue(board.isBlank(0,2));
    }

    @Test
    void isValid() throws FileNotFoundException {
        Scanner s = new Scanner(new File("Sudoku_1"));
        Sudoku board = Sudoku.read(s);

        assertFalse(board.isValid(9,3,4));
        assertFalse(board.isValid(3,7,2));
        assertTrue(board.isValid(1,1,0));
        assertTrue(board.isValid(2,2,7));
        assertTrue(board.isValid(1,3,8));
    }

    @Test
    void checkRow() throws FileNotFoundException {
        Scanner s = new Scanner(new File("Sudoku_1"));
        Sudoku board = Sudoku.read(s);

        assertFalse(board.isValid(3,0,2));
        assertTrue(board.isValid(4,0,1));
    }

    @Test
    void checkCol() throws FileNotFoundException {
        Scanner s = new Scanner(new File("Sudoku_1"));
        Sudoku board = Sudoku.read(s);

        assertFalse(board.isValid(1,0,2));
        assertTrue(board.isValid(4,0,2));
    }

    @Test
    void checkBlock() throws FileNotFoundException {
        Scanner s = new Scanner(new File("Sudoku_1"));
        Sudoku board = Sudoku.read(s);

        assertFalse(board.isValid(8,1,1));
        assertFalse(board.isValid(3,2,6));
        assertFalse(board.isValid(6,5,8));
        assertTrue(board.isValid(1, 1, 1));
    }
}
