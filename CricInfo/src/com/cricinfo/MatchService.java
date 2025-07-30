package com.cricinfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//It manages Match
public class MatchService {
	
	private static MatchService instance;
	private final Map<String,Match> matches;
	
	public static synchronized MatchService getInstance() {
		if(instance==null) {
			instance=new MatchService();
		}
		return instance;
	}
	
	private MatchService() {
		matches=new ConcurrentHashMap<String, Match>();
	}
	
	public void addMatch(Match match) {
		matches.put(match.getId(), match);
	}
	
	public Match getMatch(String matchId) {
		return matches.get(matchId);
	}

	public List<Match> getMatches(){
		return matches.values().stream().toList();
	}
	
	public void updateMatchStatus(String matchId,MatchStatus matchStatus) {
		Match match=matches.get(matchId);
		match.setStatus(matchStatus);
	}
}
