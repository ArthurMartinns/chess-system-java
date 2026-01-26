package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
	private EColor color;
	
	public ChessPiece(Board board, EColor color) {
		super(board);
		this.color = color;	
	}

	public EColor getColor() {
		return color;
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null & p.getColor() != color;
	}
}
