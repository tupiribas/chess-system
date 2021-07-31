package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "C";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRow()][getBoard().getColumn()];

		Position p = new Position(0, 0);

		// "L" movement + above + left quadrant 1 + attack and normal
		p.setValues(position.getRow() - 2, position.getColumn() - 1); 
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// "L" movement + above + left quadrant 1 + attack and normal
		p.setValues(position.getRow() - 1, position.getColumn() - 2); 
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// "L" movement + above + right quadrant 2 + attack and normal
		p.setValues(position.getRow() - 2, position.getColumn() + 1); // ok
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// "L" movement + above + right quadrant 2 + attack and normal
		p.setValues(position.getRow() - 1, position.getColumn() + 2); // ok
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// "L" movement + below + left quadrant 3 + attack and normal
		p.setValues(position.getRow() + 1, position.getColumn() - 2); // ok
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// "L" movement + below + left quadrant 3 + attack and normal
		p.setValues(position.getRow() + 2, position.getColumn() - 1); // ok
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// "L" movement + below + right quadrant 4 + attack and normal
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// "L" movement + below + right quadrant 4 + attack and normal
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

}
