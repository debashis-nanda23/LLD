package com.chesse;

import java.util.Scanner;

import com.chesse.pieces.Piece;

public class ChesseGame {
	
	private final Board board;
	private  Player whitePlayer;
	private  Player blackPlayer;
	private  Player curretnPlayer;
	
	public ChesseGame() {
		board=new Board();
	}
	
	public void setPlayers(String whitePlayerName,String blackPlayerName) {
		whitePlayer=new Player(whitePlayerName, Color.WHITE);
		blackPlayer=new Player(blackPlayerName, Color.BLACK);
		curretnPlayer=whitePlayer;
	}
	
	public void start() {
		while(!isGameOver()) {
			Player playe=curretnPlayer;
			System.out.println(playe.getName()+"'s turn");
			Move move=getPlayerMove(playe);
			try {
				board.movePice(move);
			}catch(InvalidMoveException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Try Again!");
				continue;
			}
			switchTurn();
		}
		diplayeResult();
	}
	
	private boolean isGameOver() {
		return board.isCheckMate(whitePlayer.getColor())|| board.isCheckMate(blackPlayer.getColor())
			|| board.isStaleMate(whitePlayer.getColor()) || board.isStaleMate(blackPlayer.getColor());	 
	}
	
	private void switchTurn() {
		curretnPlayer= curretnPlayer==whitePlayer ? blackPlayer : whitePlayer;
	}
	
	private Move getPlayerMove(Player player) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Source Row:");
		int soureRow=sc.nextInt();
		System.out.println("Enter Source Column:");
		int sourceColumn=sc.nextInt();
		System.out.println("Enter Destination Row:");
		int destRow=sc.nextInt();
		System.out.println("Enter Destination Column:");
		int destColumn=sc.nextInt();
		
		Piece piece=board.getPiece(soureRow,sourceColumn);
		if(piece==null || piece.getColor()!=player.getColor()) {
			throw new IllegalArgumentException("Invalid Piece Selection");
		}
		
		return new Move(board.getCell(soureRow, sourceColumn),board.getCell(destRow, destColumn));
	}
	
	private  void diplayeResult() {
		if(board.isCheckMate(Color.WHITE)) {
			System.out.println("Black wins by checkmate");
		}else if(board.isCheckMate(Color.BLACK)) {
			System.out.println("White wins by checkmate");
		}else if(board.isStaleMate(Color.WHITE) || board.isStaleMate(Color.BLACK)) {
			System.out.println("Game over by stalemate");
		}
	}
	

}
