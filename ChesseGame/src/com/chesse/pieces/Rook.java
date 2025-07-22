package com.chesse.pieces;

import com.chesse.Board;
import com.chesse.Cell;
import com.chesse.Color;

public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
	}

	@Override
	public boolean canMove(Board board, Cell from, Cell to) {
		return (from.getRow()==to.getRow()) || (from.getCol()==to.getCol());
	}

}
