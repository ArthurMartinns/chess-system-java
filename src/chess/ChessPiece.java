package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece{
	private EColor color;
	
	public ChessPiece(Board board, EColor color) {
		super(board);
		this.color = color;	
	}

	public EColor getColor() {
		return color;
	}
	
	
}
