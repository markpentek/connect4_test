import model.GameBoard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testBoardInitialization() {
        GameBoard board = new GameBoard();
        assertFalse(board.isFull());
    }

    @Test
    public void testPlacePieceValidMove() {
        GameBoard board = new GameBoard();
        assertTrue(board.placePiece(3, 'X'));
    }

    @Test
    public void testPlacePieceInvalidMove() {
        GameBoard board = new GameBoard();
        for (int i = 0; i < GameBoard.ROWS; i++) {
            board.placePiece(0, 'X');
        }
        assertFalse(board.placePiece(0, 'O'));
    }

    @Test
    public void testHorizontalWin() {
        GameBoard board = new GameBoard();
        for (int i = 0; i < 4; i++) {
            board.placePiece(i, 'X');
        }
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testVerticalWin() {
        GameBoard board = new GameBoard();
        for (int i = 0; i < 4; i++) {
            board.placePiece(2, 'O');
        }
        assertTrue(board.checkWin('O'));
    }

    @Test
    public void testDiagonalWin() {
        GameBoard board = new GameBoard();
        board.placePiece(0, 'X');
        board.placePiece(1, 'O'); board.placePiece(1, 'X');
        board.placePiece(2, 'O'); board.placePiece(2, 'O'); board.placePiece(2, 'X');
        board.placePiece(3, 'O'); board.placePiece(3, 'O'); board.placePiece(3, 'O'); board.placePiece(3, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testBoardFull() {
        GameBoard board = new GameBoard();
        for (int col = 0; col < GameBoard.COLUMNS; col++) {
            for (int row = 0; row < GameBoard.ROWS; row++) {
                board.placePiece(col, 'X');
            }
        }
        assertTrue(board.isFull());
    }
}
