package com.cricinfo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CricInfoSystemDemo {
	
	public static void main(String[] args) {
		
		//create Teams
		List<Player> team1Players=Arrays.asList(new Player("P101","Player 1", "Batsman"),
				                                new Player("P102","Player 2","Bowler"),
				                                new Player("P103","Player 3","All Rounder"));
		
		List<Player> team2Players=Arrays.asList(new Player("P201","Player 4", "Batsman"),
                                                new Player("P202","Player 5","Bowler"),
                                                new Player("P203","Player 6","All Rounder"));
		Team team1=new Team("T101","Team 1", team1Players);
		Team team2=new Team("T102","Team 2", team2Players);
		List<Team> teams=Arrays.asList(team1,team2);
		
		//create a match
		Match match=new Match("M101","Match 1","Venue 1", LocalDateTime.now().plusDays(2), teams);
		
		//create cric info system
		CricInfoSystem cricInfoSysytem=new CricInfoSystem();
		
		//Add match to the system
		cricInfoSysytem.addMatch(match);
		
		//create score board for the match
		cricInfoSysytem.createSocreBoard(match);
		
		//Get the score board
		ScoreBoard scorBoard=cricInfoSysytem.getScoreBoard("SH-M101-0001");
		
		//update score
		cricInfoSysytem.updateScore("SH-M101-0001", "T101", 100);
		cricInfoSysytem.updateScore("SH-M101-0001", "T102", 60);
		
		//create Innings
		Innings inning1=new Innings("I1", "T101", "T102");
		Innings inning2=new Innings("I2", "T102", "T101");
		
		//add over to innings
		Over over1=new Over(1);
		over1.addBall(new Ball(1,"Player 1", "Player 4","4"));
		over1.addBall(new Ball(2, "Player 1", "Player 4", "6"));
		over1.addBall(new Ball(3, "Player 2", "Player 4", "3"));
		inning1.addOver(over1);
		
		Over over2=new Over(2);
		over2.addBall(new Ball(1,"Player 4", "Player 3","4"));
		over2.addBall(new Ball(2, "Player 4", "Player 3", "3"));
		over2.addBall(new Ball(3, "Player 5", "Player 3", "2"));
		inning2.addOver(over2);
		
		//add innings to the score board
		cricInfoSysytem.addInnings("SH-M101-0001", inning1);
		cricInfoSysytem.addInnings("SH-M101-0001", inning2);
		
		//Get the updated score board
		ScoreBoard updatedScorBoard= cricInfoSysytem.getScoreBoard("SH-M101-0001");
		
		//Display the score board
		System.out.println("Score Card Id:"+updatedScorBoard.getId());
		System.out.println("Match :"+updatedScorBoard.getMatch().getTitle());
		System.out.println("team scores::");
	    for(Map.Entry<String,Integer> entry:updatedScorBoard.getTeamScores().entrySet()) {
	    	System.out.println(entry.getKey()+":"+entry.getValue());
	    }
	    System.out.println("Innings::");
	    for(Innings inning:updatedScorBoard.getInnings()) {
	    	System.out.println("Innings id is::"+inning.getId());
	    	System.out.println("Bating Team::"+inning.getBatingTeamId());
	    	System.out.println("Bowlong Team::"+inning.getBowlingTeamId());
	    	System.out.println("Overs::");
	    	for(Over over:inning.getOvers()) {
	    		System.out.println("Over::"+over.getOverNumber());
	    		for(Ball ball:over.getBalls()) {
	    			System.out.println("Ball "+ball.getBallNumber()+":"+ball.getBowler()+" to "+ball.getBatsman()+"-"+ball.getResult());
	    		}
	    	}
	    }
		
		
		
	}

}
