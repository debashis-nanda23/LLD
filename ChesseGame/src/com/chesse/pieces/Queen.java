package com.chesse.pieces;

import com.chesse.Board;
import com.chesse.Cell;
import com.chesse.Color;

public class Queen extends Piece{

	public Queen(Color color) {
		super(color);
	}

	@Override
	public boolean canMove(Board board, Cell from, Cell to) {
		int rowDiff=Math.abs(to.getRow()-from.getRow());
		int colDiff=Math.abs(to.getCol()-from.getCol());
		return (rowDiff==colDiff) || (from.getRow()==to.getRow()) ||(from.getCol()==to.getCol());
	}

}
