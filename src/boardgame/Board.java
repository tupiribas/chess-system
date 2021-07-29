package boardgame;

public class Board {
	private int row;
	private int column;
	private Piece[][] pieces;

	public Board(int row, int column) {
		if (row < 1 || column < 1) {

			throw new BoardException("ERROR CREATING BOARD 001>>> "
					+ "There most be at least row 1 and 1 column.");

			throw new BoardException("ERROR CREATING BOARD 001>>> there most be at least row 1 and 1 column.");

		}
		this.row = row;
		this.column = column;
		pieces = new Piece[row][column];
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Error 331>>> Position not on the board. \n");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Error 332>>> Position not on the board. \n");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && row < this.row && column >= 0 && column < this.column;
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("Error 10>>> There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Error 333>>> Position not on the board. \n");
		}
		return piece(position) != null;
	}

}
