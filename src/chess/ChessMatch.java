package chess;

import boardgame.Board;
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
	
	private void initSetup() {
		board.placePiece(new Rook(board, EColor.BLACK), new Position(2,1));
		board.placePiece(new King(board, EColor.WHITE), new Position(0,4));
		board.placePiece(new King(board, EColor.WHITE), new Position(7,4));
	}
}
