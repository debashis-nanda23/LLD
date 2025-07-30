package com.cricinfo;

import java.util.ArrayList;
import java.util.List;

//It contains ball
public class Over {

	private final int overNumber;
	private final List<Ball> balls;
	
	public Over(int overNumber) {
		this.overNumber = overNumber;
		balls=new ArrayList<Ball>();
	}

	public int getOverNumber() {
		return overNumber;
	}

	public List<Ball> getBalls() {
		return balls;
	}
	
	public void addBall(Ball ball) {
		this.balls.add(ball);
	}
	
	
}
