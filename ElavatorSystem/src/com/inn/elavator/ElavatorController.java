package com.inn.elavator;

import java.util.ArrayList;
import java.util.List;

public class ElavatorController {
	
	private final List<Elavator> elavators;
	
	public ElavatorController(int numElavator,int capacity) {
		elavators=new ArrayList<>();
		for(int i=0;i<numElavator;i++) {
			Elavator elavator=new Elavator(i+1, capacity);
			elavators.add(elavator);
			new Thread(elavator::run).start();
		}
	}
	
	public void requestElavator(int sourceFloor,int destinationFloor) {
	   
		Elavator optimalElavator= findOptimalElavator(sourceFloor,destinationFloor);
		optimalElavator.addRequest(new Request(sourceFloor, destinationFloor));
	}

	private Elavator findOptimalElavator(int sourceFloor, int destinationFloor) {
		Elavator optimalElavator=null;
		int minDistance=Integer.MAX_VALUE;
		
		Direction userDirection=(sourceFloor-destinationFloor)>0 ? Direction.DOWN : Direction.UP;
		List<Elavator> optimalElavators=elavators.stream().anyMatch(s->s.getDirection()==userDirection) ?
				                         elavators.stream().filter(s->s.getDirection()==userDirection).toList() : elavators;
		
		for(Elavator elvator:optimalElavators) {
			int distance=Math.abs(sourceFloor-elvator.getCurrentFloor());
			if(distance<minDistance) {
				minDistance=distance;
				optimalElavator=elvator;
			}
		}
		return optimalElavator;
	}
	
	
}
