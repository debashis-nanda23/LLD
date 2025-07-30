package com.cricinfo;

import java.util.List;

//It uses match service and score board service
public class CricInfoSystem {
	
	private  final MatchService matchService;
	private final ScoreBoardService scoreBoardService;
	
	public CricInfoSystem() {
		matchService=MatchService.getInstance();
		scoreBoardService=ScoreBoardService.getInstance();
	}
	
	public void addMatch(Match match) {
		matchService.addMatch(match);
	}
	
	public Match getMatch(String matchId) {
		return matchService.getMatch(matchId);
	}
	
	public List<Match> getAllMatches(){
		return matchService.getMatches();
	}
	
	public void updateMatchStatus(String matchId,MatchStatus matchStatus) {
		matchService.updateMatchStatus(matchId, matchStatus);
	}
	
	public void createSocreBoard(Match match) {
		scoreBoardService.createScoreBoard(match);
	}
	
	public ScoreBoard getScoreBoard(String scoreId) {
	     return scoreBoardService.getScoreBoards(scoreId);
	}
	
	public void updateScore(String scoreId,String teamId,int score) {
		scoreBoardService.updateScore(scoreId, teamId, score);
	}
	
	public void addInnings(String scoreId,Innings inning) {
		scoreBoardService.addInnings(scoreId, inning);
	}
	

}
