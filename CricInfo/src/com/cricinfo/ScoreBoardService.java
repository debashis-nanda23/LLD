package com.cricinfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//It manages score board
public class ScoreBoardService {
	
	private static ScoreBoardService instance;
	private final Map<String,ScoreBoard> scoreCards;
	private final AtomicInteger scoreidCounter;
	
	public static synchronized ScoreBoardService getInstance() {
		if(instance==null) {
			instance=new ScoreBoardService();
		}
		return instance;
	}
	
	private ScoreBoardService() {
		scoreCards=new ConcurrentHashMap<String, ScoreBoard>();
		scoreidCounter=new AtomicInteger(0);
	}
	
	public synchronized void createScoreBoard(Match match) {
		String scoreCardId=generateScoreId(match.getId());
		ScoreBoard scoreBoard=new ScoreBoard(scoreCardId, match);
		scoreCards.put(scoreCardId, scoreBoard);
	}
	
	private String generateScoreId(String matchId) {
		return "SH-"+matchId+"-"+String.format("%04d",scoreidCounter.incrementAndGet());
	}

	public ScoreBoard getScoreBoards(String scoreId) {
		return scoreCards.get(scoreId);
	}
	
	public void updateScore(String scoreId,String teamId,int score) {
		ScoreBoard scoreBoard=scoreCards.get(scoreId);
		scoreBoard.updateTeamScore(teamId,score);
	}
	
	public void addInnings(String scoreId,Innings inning) {
	   scoreCards.get(scoreId).getInnings().add(inning);
	}

}
