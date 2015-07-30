package com.ckenken.implement.sparse;

public class EndPoint {
	
	final public static int PLUS = 0;
	final public static int MINUS = 1;
	
	public int symbolid;
	public int type; // 0 == +, 1 == -
	public int timestamp;
	
	public EndPoint(int inputSymbol, int inputType, int inputTimeStamp)
	{
		this.symbolid = inputSymbol;
		this.type = inputType;
		this.timestamp = inputTimeStamp;
	}
	
	public EndPoint copy()
	{
		EndPoint t = new EndPoint(this.symbolid, this.type, this.timestamp);
		return t;
	}
	
	public EndPoint()
	{
		this.type = PLUS;
		this.timestamp = -1;
	}
}
