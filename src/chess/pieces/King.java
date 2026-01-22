package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.EColor;

public class King extends ChessPiece{

	public King(Board board, EColor color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}
}
