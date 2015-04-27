package com.ckenken.implement;

import java.util.ArrayList;

public class DataSequence {
	
	ArrayList<DataPoint> dataPoints;
	
	public DataSequence()
	{
		dataPoints = new ArrayList<DataPoint>();
	}
	
	public DataSequence(ArrayList<DataPoint> input)
	{
		dataPoints = new ArrayList<DataPoint>();
		
		for(int i = 0; i<input.size(); i++) {
			dataPoints.add(input.get(i));
		}
	}
	
	public ArrayList<DataPoint> getSubDataSequence(int index) 
	{
		ArrayList<DataPoint> temp = new ArrayList<DataPoint>();
		
		for(int i = index; i<dataPoints.size(); i++) {
			temp.add(dataPoints.get(i));
		}
		return temp;
	}	
	
}
