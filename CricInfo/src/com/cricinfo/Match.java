package com.cricinfo;

import java.time.LocalDateTime;
import java.util.List;

public class Match {
	
	private final String id;
	private final String title;
	private final String venue;
	private LocalDateTime dateTime;
	private ScoreBoard scoreBoard;
	private MatchStatus matchStatus;
	private final List<Team> teams;
	
	public Match(String id, String title, String venue, LocalDateTime dateTime, List<Team> teams) {
		super();
		this.id = id;
		this.title = title;
		this.venue = venue;
		this.dateTime = dateTime;
		this.teams = teams;
		this.matchStatus=MatchStatus.SCHEDULED;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public ScoreBoard getScoreBoard() {
		return scoreBoard;
	}

	public void setScoreBoard(ScoreBoard scoreBoard) {
		this.scoreBoard = scoreBoard;
	}

	public MatchStatus getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(MatchStatus matchStatus) {
		this.matchStatus = matchStatus;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getVenue() {
		return venue;
	}

	public List<Team> getTeams() {
		return teams;
	}
	
	public void setStatus(MatchStatus matchStatus) {
		this.matchStatus=matchStatus;
	}
	
	

}
