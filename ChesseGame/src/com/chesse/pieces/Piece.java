package com.chesse.pieces;

import com.chesse.Board;
import com.chesse.Cell;
import com.chesse.Color;

public abstract class Piece {
	
	protected final Color color;
	
	public Piece(Color color) {
		this.color=color;
	}
	
	public abstract boolean canMove(Board board,Cell from,Cell to);

	public Color getColor() {
		return color;
	}
	
	

}
