package com.cricinfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

//It is linked to Match
//It maintains innings
public class ScoreBoard {
	
	private final String id;
	private final Match match;
	private final Map<String,Integer> teamScores;
	private final List<Innings> innings;
	
	public ScoreBoard(String id, Match match) {
		super();
		this.id = id;
		this.match = match;
		this.teamScores=new ConcurrentHashMap<String, Integer>();
		this.innings=new CopyOnWriteArrayList<Innings>();
	}
	
	public void addInnings(Innings inning) {
		this.innings.add(inning);
	}
	
	public void updateTeamScore(String teamId,Integer score) {
		this.teamScores.put(teamId, teamScores.getOrDefault(teamId,0)+score);
	}

	public String getId() {
		return id;
	}

	public Match getMatch() {
		return match;
	}

	public Map<String, Integer> getTeamScores() {
		return teamScores;
	}

	public List<Innings> getInnings() {
		return innings;
	}
	
	
	
	

}
