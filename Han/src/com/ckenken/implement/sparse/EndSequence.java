package com.ckenken.implement.sparse;

import java.util.ArrayList;

public class EndSequence {
	public ArrayList<EndPoint> endPoints;
	
	public EndSequence()
	{
		endPoints = new ArrayList<EndPoint>();
	}
	
	public EndSequence(ArrayList<EndPoint> input)
	{
		endPoints = new ArrayList<EndPoint>();
		
		for(int i = 0; i<input.size(); i++) {
			endPoints.add(input.get(i));
		}
	}
	
	public EndSequence copy()
	{
		EndSequence seq = new EndSequence();
		
		for(int i = 0; i<this.endPoints.size(); i++) {
			seq.endPoints.add(this.endPoints.get(i).copy());
		}
		
		return seq;
	}
	
	public void release()
	{
		this.endPoints.clear();
	}
	
	public ArrayList<EndPoint> getSubSequence(int index)
	{
		ArrayList<EndPoint> temp = new ArrayList<EndPoint>();
		
		for(int i = index; i<endPoints.size(); i++) {
			temp.add(endPoints.get(i));
		}
		
//		EndSequence output = new EndSequence(temp);
		
		return temp;
	}
}
