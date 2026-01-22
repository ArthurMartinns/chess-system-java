package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.EColor;

public class Rook extends ChessPiece{

	public Rook(Board board, EColor color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}
}
