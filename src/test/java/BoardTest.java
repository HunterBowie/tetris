import com.hunterbowie.core.Board;
import com.hunterbowie.core.Piece;
import com.hunterbowie.util.PieceColor;
import com.hunterbowie.util.PieceShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.hunterbowie.util.Constants.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    Piece illegalPieceOutOfBounds;
    Board illegalBoardOutOfBounds;
    Piece illegalPieceOnBlocks;
    Board illegalBoardOnBlocks;
    Piece legalPieceNotOnBlocks;
    Board legalBoardNotOnBlocks;
    private Piece legalPieceNotOutOfBounds;
    private Board legalBoardNotOutOfBounds;
    private Piece pieceNotGrounded;
    private Board boardNotGrounded;
    private Piece pieceGrounded;
    private Board boardGrounded;
    private Piece pieceNotGroundedAgain;
    private Board boardNotGroundedAgain;

    @Test
    void illegalPieceOutOfBoundsIsFalse() {
        illegalPieceOutOfBounds = new Piece(PieceShape.L_PIECE, PieceColor.BLUE);
        illegalBoardOutOfBounds = Board.makeCustom(0, 0, illegalPieceOutOfBounds);
        illegalPieceOutOfBounds.placeInsideBoard(ROWS+GHOST_ROWS-3, COLUMNS-5, 0, 0);
        assertFalse(illegalBoardOutOfBounds.isPieceLegal());
    }

    @Test
    void illegalPieceOnBlocksIsFalse() {
        illegalPieceOnBlocks = new Piece(PieceShape.L_PIECE, PieceColor.BLUE);
        illegalBoardOnBlocks = Board.makeCustom(0, 0, illegalPieceOnBlocks);
        illegalBoardOnBlocks.blocks[ROWS-2][COLUMNS-3] = PieceColor.RED.color;
        illegalPieceOnBlocks.placeInsideBoard(ROWS+GHOST_ROWS-5, COLUMNS-5, 0, 0);
        assertFalse(illegalBoardOnBlocks.isPieceLegal());
    }

    @Test
    void legalPieceNotOutOfBoundsIsTrue() {
        legalPieceNotOutOfBounds = new Piece(PieceShape.L_PIECE, PieceColor.BLUE);
        legalBoardNotOutOfBounds = Board.makeCustom(0, 0, legalPieceNotOutOfBounds);
        legalPieceNotOutOfBounds.placeInsideBoard(-1, -1, 0, 0);
        assertTrue(legalBoardNotOutOfBounds.isPieceLegal());
    }

    @Test
    void legalPieceNotOnBlocksIsTrue() {
        legalPieceNotOnBlocks = new Piece(PieceShape.L_PIECE, PieceColor.BLUE);
        legalBoardNotOnBlocks = Board.makeCustom(0, 0, legalPieceNotOnBlocks);
        legalBoardNotOnBlocks.blocks[ROWS-2][COLUMNS-2] = PieceColor.RED.color;
        legalPieceNotOnBlocks.placeInsideBoard(ROWS+GHOST_ROWS-5, COLUMNS-5, 0, 0);
        assertTrue(legalBoardNotOnBlocks.isPieceLegal());
    }

    @Test
    void pieceNotGroundedIsFalse() {
        pieceNotGrounded = new Piece(PieceShape.L_PIECE, PieceColor.BLUE);
        boardNotGrounded = Board.makeCustom(0, 0, pieceNotGrounded);
        pieceNotGrounded.placeInsideBoard(ROWS+GHOST_ROWS-5, COLUMNS-5, 0, 0);
        assertFalse(boardNotGrounded.isPieceGrounded());
    }

    @Test
    void pieceGroundedIsTrue() {
        pieceGrounded = new Piece(PieceShape.L_PIECE, PieceColor.BLUE);
        boardGrounded = Board.makeCustom(0, 0, pieceGrounded);
        pieceGrounded.placeInsideBoard(ROWS+GHOST_ROWS-4, COLUMNS-5, 0, 0);
        assertTrue(boardGrounded.isPieceGrounded());
    }

    @Test
    void pieceNotGroundedAgainIsFalse() {
        pieceNotGroundedAgain = new Piece(PieceShape.L_PIECE, PieceColor.BLUE);
        boardNotGroundedAgain = Board.makeCustom(0, 0, pieceNotGroundedAgain);
        pieceNotGroundedAgain.placeInsideBoard(16, 2, 0, 0);
        assertFalse(boardNotGroundedAgain.isPieceGrounded());

    }
}
