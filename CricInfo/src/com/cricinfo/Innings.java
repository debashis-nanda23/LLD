package com.cricinfo;

import java.util.ArrayList;
import java.util.List;

//It contains Over
public class Innings {
	
	private final String id;
	private final String batingTeamId;
	private final String bowlingTeamId;
	private final List<Over> overs;
	
	public Innings(String id, String batingTeamId, String bowlingTeamId) {
		super();
		this.id = id;
		this.batingTeamId = batingTeamId;
		this.bowlingTeamId = bowlingTeamId;
		this.overs=new ArrayList<>();
	}
	
	public void addOver(Over over) {
		this.overs.add(over);
	}

	public String getId() {
		return id;
	}

	public String getBatingTeamId() {
		return batingTeamId;
	}

	public String getBowlingTeamId() {
		return bowlingTeamId;
	}

	public List<Over> getOvers() {
		return overs;
	}
	
	
	
	

}
