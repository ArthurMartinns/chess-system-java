package chess;

import boardgame.Board;
import boardgame.BoardException;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initSetup();
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int i = 0; i < board.getRows(); i++) {
			for(int j = 0; j < board.getColumns(); j++) {
				matriz[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		
		return matriz;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece chesspiece) {
		board.placePiece(chesspiece, new ChessPosition(column, row).toPosition());
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);
		
		Piece capturedPiece = makeMove(source, target);
		
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		return capturedPiece;
	}

	private void validateSourcePosition(Position source) {
		if(!board.thereIsAPiece(source)) {
			throw new BoardException("There is no piece on source position.");
		}
		
	}

	private void initSetup() {
		placeNewPiece('c', 1, new Rook(board, EColor.WHITE));
        placeNewPiece('c', 2, new Rook(board, EColor.WHITE));
        placeNewPiece('d', 2, new Rook(board, EColor.WHITE));
        placeNewPiece('e', 2, new Rook(board, EColor.WHITE));
        placeNewPiece('e', 1, new Rook(board, EColor.WHITE));
        placeNewPiece('d', 1, new King(board, EColor.WHITE));

        placeNewPiece('c', 7, new Rook(board, EColor.BLACK));
        placeNewPiece('c', 8, new Rook(board, EColor.BLACK));
        placeNewPiece('d', 7, new Rook(board, EColor.BLACK));
        placeNewPiece('e', 7, new Rook(board, EColor.BLACK));
        placeNewPiece('e', 8, new Rook(board, EColor.BLACK));
        placeNewPiece('d', 8, new King(board, EColor.BLACK));
	}
}
