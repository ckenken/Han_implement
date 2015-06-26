package com.ckenken.implement.sparse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import lab.adsl.optics.Haversine;

import com.ckenken.Main.Main_v2;
import com.ckenken.implement.storage.DataPoint;
import com.ckenken.implement.storage.DataSequence;
import com.ckenken.io.JDBC;

public class BuildTrajectory {
	
	final private static String OUTPUT = "buildTrajectory2.txt";  
	public static int max_seqid = 0;
	
	public static ArrayList<ArrayList<Integer>> getDayArray(String date) throws SQLException
	{
		ArrayList<ArrayList<Integer>> day = new ArrayList<ArrayList<Integer>>();
	
		for(int i = 0; i<24; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			day.add(temp);
		}
		
		String sql = "select * from raw2 where day = '" + date + "' and same != -1";
		
		ResultSet rs = Gcenter.jdbc.query(sql);
		
		while(rs.next()) {
			int gid = rs.getInt("g");
			int timestamp = rs.getInt("timestamp");
			
			if (!day.get(timestamp).contains(gid)) {
				day.get(timestamp).add(gid);
			}
		}
		rs.close();
		
		return day;
	}
	
	public static ArrayList<DataSequence> createOrigin() throws SQLException 
	{
		ArrayList<DataSequence> origin = new ArrayList<DataSequence>();
		
		
		String sql = "select distinct day from raw2 where same != -1";
		
		ResultSet rs = Gcenter.jdbc.query(sql);
		
		while(rs.next()) {
			
			String date = rs.getString("day");
			
			ArrayList<ArrayList<Integer>> day = getDayArray(date);
			
			
			
		}
		rs.close();
		
		return origin;
	}
	
	public static void createSequence22() throws SQLException, ParseException
	{
		String sql = "select distinct day from raw2 where same != -1";
		
		ResultSet rs = Gcenter.jdbc.query(sql);
		
		ArrayList<DataPoint> datas = new ArrayList<DataPoint>();
		
		while(rs.next()) {
			
			String date = rs.getString("day");
			
			ArrayList<ArrayList<Integer>> day = getDayArray(date);
		
			for(int i = 0; i<day.size(); i++) { // day size = 24
				ArrayList<Integer> hour = day.get(i);
				
				for(int j = 0; j<hour.size(); j++) {
					DataPoint data = recursiveGetItem(day, hour.get(j), i, i, date);
					
			//		data.seqid = BuildTrajectory.max_seqid++;
					data.sameid = -1;
					data.G = hour.get(j);
					data.day = date;
					
					sql = "select * from gcenter where Gid = " + data.G;
					
					ResultSet rs2 = Gcenter.jdbc.query(sql);
					
					if (rs2.next()) {
						double lat = rs2.getDouble("lat");
						double lng = rs2.getDouble("lng");
						String cate = rs2.getString("cate");
						data.cate = cate;
						data.lat = lat;
						data.lng = lng;
					}
					else {
						System.out.println("Error!!!");
						System.exit(1);
					}
					rs2.close();
					
					datas.add(data);
					
					int start = i;
					int end = data.endTime.getHours();
					
					for(int k = start; k<=end; k++) {
						day.get(k).remove(new Integer(data.G));
					} 
				}
			}
			
			for(int i = 0; i<datas.size(); i++) {
				DataPoint d = DataPoint.copy(datas.get(i));
				sql = "insert into sequence22 values(" + BuildTrajectory.max_seqid + ",-1," + d.lat + "," + d.lng +"," + d.G + ",'" + d.cate + "','" + Main_v2.sdFormat.format(d.startTime) + "','" + Main_v2.sdFormat.format(d.endTime) + "',0,'" + d.day + "')";
				BuildTrajectory.max_seqid++;
				Gcenter.jdbc.insertQuery(sql);
			}
			datas.clear();
		}
		rs.close();
		
	}
	
	public static DataPoint recursiveGetItem(ArrayList<ArrayList<Integer>> day, int gid, int startTimestamp, int nowTimestamp, String date) throws ParseException
	{
		DataPoint temp = new DataPoint();
		
		// 2011-12-08T20:53:52
		
		String start = "2011-" + date + "T" + startTimestamp + ":22:22";
		String end = "2011-" + date + "T" + nowTimestamp + ":22:22";
		
		Date d = Main_v2.parseDate(start);
		temp.startTime = d;
		
		d = Main_v2.parseDate(end);
		temp.endTime = d;
		
		if (nowTimestamp == 23) {
			return temp;
		}
		
		if (day.get(nowTimestamp+1).contains(gid)) { // have this gid in next hour
			temp = recursiveGetItem(day, gid, startTimestamp, nowTimestamp+1, date);
		}
		
		return temp;
	}
	
	public static void printDistance() throws SQLException
	{
		String sql = "select * from gcenter";
		
		ResultSet rs = Gcenter.jdbc.query(sql);
		
		ArrayList<Gcenter> gcenters = new ArrayList<Gcenter>();
		
		while(rs.next()) {
			Gcenter g = new Gcenter(rs.getInt("Gid"), 0, rs.getDouble("lat"), rs.getDouble("lng")); 
			gcenters.add(g);
		}
		
		for(int i = 0; i<gcenters.size(); i++) {
			for(int j = i+1; j<gcenters.size(); j++) {
				if (Gcenter.distance(gcenters.get(i), gcenters.get(j)) < 1000) {
					System.out.println(i + "<->" + j + ": " + Gcenter.distance(gcenters.get(i), gcenters.get(j)));	
				}
			}
		}
	}
	
	public static void main(String[] args) throws SQLException, FileNotFoundException, ParseException 
	{
		
//		PrintStream outstream = new PrintStream(new FileOutputStream(OUTPUT));  
//		System.setOut(outstream);		
		
		BuildTrajectory.max_seqid = 0;
		
		Gcenter.jdbc = new JDBC("han");		
		
//		day = new ArrayList<ArrayList<Integer>>();
		
//		createSequences();
		
	//	printDistance();
			
	//	createSequence22();
		
		
		
	}
}
