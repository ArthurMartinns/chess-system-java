package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Board board;
	private int turn;
	private EColor currentPlayer;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8,8);
		turn = 1;
		currentPlayer = EColor.WHITE;
		initSetup();
	}
	
	public int getTurn() {
		return turn;
	}

	public EColor getCurrentPlayer() {
		return currentPlayer;
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
		piecesOnTheBoard.add(chesspiece);
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position.");
		}
		
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}

	private void validateSourcePosition(Position source) {
		if(!board.thereIsAPiece(source)) {
			throw new ChessException("There is no piece on source position.");
		}
		if(currentPlayer != ((ChessPiece)board.piece(source)).getColor()) {
			throw new ChessException("The chosen piece is not yours.");
		}
		
		if(!board.piece(source).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece.");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == EColor.WHITE) ? currentPlayer.BLACK : currentPlayer.WHITE;
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
