package com.ckenken.implement;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.ckenken.algo.Coarse;
import com.ckenken.io.JDBC;
import com.ckenken.storage.Coarse_pattern;

public class IM_Main {
	
	private static final String OUTPUT = "IM_DEBUG.txt";  
	public static final int DT = 3600000;
	public static final int LENGTH = 8;
	public static final int SIGMA = 15;
	public static final int NOT_SIM = 9999;
	
	public static double SIM_THRESHOLD = 3.0;
	
	public static void main(String args []) throws SQLException, ParseException, FileNotFoundException
	{
//		PrintStream outstream = new PrintStream(new FileOutputStream(OUTPUT));  
//		System.setOut(outstream);		
		
		JDBC jdbc = new JDBC("han");
		
		String sql = "select * from sequence30_training";
		
		ResultSet rs = jdbc.query(sql);
		
		ArrayList<DataPoint> datas = new ArrayList<DataPoint>();
		
//		while(rs.next()) {
//			int seqid = rs.getInt("seqid");
//			int sameid = rs.getInt("sameid");
//			double lat = rs.getDouble("lat");
//			double lng = rs.getDouble("lng");
//			int G = rs.getInt("G");
//			String cate = rs.getString("cate");
//			String startTime = rs.getString("time");
//			String endTime = rs.getString("endtime");
//			
//			DataPoint temp = new DataPoint(seqid,sameid, lat, lng, G, cate, startTime, endTime);
//			
//			datas.add(temp);
//		}
//		
//		int mina = 0;
//		int minb = 0;
//		double min = 20000000;
//		
//		for(int i = 0; i<datas.size(); i++) {
//			for(int j = 0; j<datas.size(); j++) {
//				if(i != j) {
//					DataPoint a = datas.get(i);
//					DataPoint b = datas.get(j);
//					
//					double point = DataPoint.similarity(a, b);
//					
//					if(point != NOT_SIM && point < SIM_THRESHOLD) {
//						System.out.println(i + "->" + j + ": " + point);
//						
//						if(point < min) {
//							min = point;
//							mina = i;
//							minb = j;
//						}
//					}
//					
//				}
//			}
//		}
//		System.out.println("min = " + mina + "->" + minb + ": " + min);
//		
//		boolean modified = false;
//		do
//		{
//			modified = false;
//			min = 2000000;
//			for(int i = 0; i<datas.size(); i++) {
//				for(int j = 0; j<datas.size(); j++) {
//					if(i != j) {
//						DataPoint a = datas.get(i);
//						DataPoint b = datas.get(j);
//						
//						if(a.symbol == b.symbol && a.symbol != -1) {
//							continue;
//						}
//						
//						double score = DataPoint.similarity(a, b);
//						
//						if(score != NOT_SIM && score < SIM_THRESHOLD) {
//							
//							if(score < min) {
//								min = score;
//								mina = i;
//								minb = j;
//								modified = true;
//							}
//						}
//						
//					}
//				}
//			}
//			if(modified) {
//				DataPoint temp = DataPoint.merge(datas.get(mina), datas.get(minb));
//				DataPoint temp2 = DataPoint.copy(temp);
//				
//				temp.seqid = datas.get(mina).seqid;
//				temp2.seqid = datas.get(minb).seqid;
//				
//				temp.sameid = datas.get(mina).sameid;
//				temp2.sameid = datas.get(minb).sameid;
//				
//				temp.G = datas.get(mina).G;
//				temp2.G = datas.get(minb).G;
//				
//				temp.cate = datas.get(mina).cate;
//				temp2.cate = datas.get(minb).cate;
//				
//				temp.startTime = datas.get(mina).startTime;
//				temp2.startTime = datas.get(minb).startTime;
//	
//				temp.endTime = datas.get(mina).endTime;
//				temp2.endTime = datas.get(minb).endTime;
//				
//				datas.set(mina, temp);
//				datas.set(minb, temp2);
//			
//				int symbol = temp.symbol;
//				for(int i = 0; i<datas.size(); i++) {
//					if(datas.get(i).symbol == symbol) {
//						datas.get(i).extendTheSame(temp);
//					}
//				}
//			}			
//		}while(modified);
//		
//		for(int i = 0; i<datas.size(); i++) {
//			if(datas.get(i).symbol == -1)
//				datas.get(i).symbol = DataPoint.max_symbol++;
//		}
//		
//		for(int i = 0; i<datas.size(); i++) {
//			System.out.println(datas.get(i).seqid + ": " + datas.get(i).symbol);
//			
//			sql = "update sequence30 set symbol = " + datas.get(i).symbol + " where seqid = " + datas.get(i).seqid;
//			jdbc.insertQuery(sql);
//			
//		}
//		
//		for(int i = 0; i<datas.size(); i++) {
//			DataPoint d = datas.get(i);
//			
//			StringBuilder SB = new StringBuilder();
//			for(int j = 0; j<24; j++) {
//				SB.append(j + ":" + d.timeDistribution[j] + "\n");
//			}
//			
//			String timeD = SB.toString();
//			
//			SB = new StringBuilder();
//			
//			for(int j = 0; j<DataPoint.MAX_G_NUMBER; j++) {
//				SB.append(j + ":" + d.gDistribution[j] + "\n");
//			}			
//			
//			String gD = SB.toString();
//			
//			sql = "insert into prefixcenter values(" + d.symbol + "," + d.lat + "," + d.lng + ",'" + gD + "','" + timeD + "')";
//			
//		//	sql = "update prefixCenter_training set timeDistribution='" + timeD + "', gDistribution='" + gD +"' where symbolid=" + d.symbol;
//			
//			jdbc.insertQuery(sql);
//
//		}

		////
		
		sql = "select * from merged_sequence30_training";
		
		rs = jdbc.query(sql);
		
		datas = new ArrayList<DataPoint>();
		
		while(rs.next()) {
			int seqid = rs.getInt("seqid");
			int sameid = rs.getInt("sameid");
			double lat = rs.getDouble("lat");
			double lng = rs.getDouble("lng");
			int G = rs.getInt("G");
			String cate = rs.getString("cate");
			String startTime = rs.getString("time");
			String endTime = rs.getString("endtime");
			
			DataPoint temp = new DataPoint(seqid,sameid, lat, lng, G, cate, startTime, endTime);
			
			temp.symbol = rs.getInt("symbol");
			
			datas.add(temp);
		}
//		DataPoint p1 = null;
//		DataPoint p2 = null;
//		
//		for(int i = 0; i<datas.size(); i++ ) {
//			
//			if (datas.get(i).symbol == 82) {
//				p1 = datas.get(i);
//				sql = "select * from prefixcenter where symbolid = " + p1.symbol;
//				ResultSet rs6 = jdbc.query(sql);
//				rs6.next();
//				p1.setDistributions(rs6.getString("gDistribution"), rs6.getString("timeDistribution"));
//			}
//			else if (datas.get(i).symbol == 153) {
//				p2 = datas.get(i);
//				sql = "select * from prefixcenter where symbolid = " + p2.symbol;
//				ResultSet rs6 = jdbc.query(sql);
//				rs6.next();
//				p2.setDistributions(rs6.getString("gDistribution"), rs6.getString("timeDistribution"));
//			}
//		}
//		
//		double sc = DataPoint.similarity(p1, p2);
//		
//		System.out.println(sc);
		
		
		
		sql = "select max(symbol) from merged_sequence30_training";
		
		rs = jdbc.query(sql);
		
		rs.next();
		
		DataPoint.max_symbol = rs.getInt("max(symbol)");
		
		//DataPoint.max_symbol = 315;
				
		double startTime = 0,endTime = 0, totTime = 0;
		startTime = System.currentTimeMillis();
		
		ArrayList<ArrayList<Integer>> old_symbol_sequence = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> new_symbol_sequence = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i<=DataPoint.max_symbol; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(i);
			old_symbol_sequence.add(temp);
		}
		
		DataSequence D = new DataSequence(datas);
		
		ArrayList<DataSequence> origin = new ArrayList<DataSequence>();
		origin.add(D);
		
		
		for(int k = 2; k<=LENGTH; k++) {
			int counter = 0;
			for(int i = 0; i<old_symbol_sequence.size(); i++) {
				Prefix.extended = new ArrayList<DataSequence>();
				ArrayList<DataSequence> extended = Prefix.extendByDataSequence(origin, old_symbol_sequence.get(i), DT);
		
				if(extended.size() == 0) {
					continue;
				}
//				if(old_symbol_sequence.get(i).size() > 1) {
//					for(int q = 0; q<old_symbol_sequence.get(i).size(); q++) {
//						System.out.print(old_symbol_sequence.get(i).get(q)+",");
//					}
//					System.out.println();
//					
//					for(int q = 0; q<extended.size(); q++) {
//						for(int w = 0; w<extended.get(q).dataPoints.size(); w++) {
//							if(w == 0)
//								System.out.print(extended.get(q).dataPoints.get(w).symbol);
//							else 
//								System.out.print("->" + extended.get(q).dataPoints.get(w).symbol);
//						}
//						System.out.println();
//					}
//				}				
				
				for(int j = 0; j<=DataPoint.max_symbol; j++) {

					if(old_symbol_sequence.get(i).get(0) == 14 && j == 49) {
						System.out.print("");
					}
					
					Coarse_data_pattern cp = Prefix.testDataCoarse(extended, k, old_symbol_sequence.get(i).get(k-2), j, DT, SIGMA);
					if(cp != null) {
						cp.show();
						cp.insertDataPattern("dataPattern_training");
						counter++;
						new_symbol_sequence.add(cp.symbol_sequence);	
					}				
				
				}
				
			}
			
			System.out.println("counter = " + counter);
			System.out.println("======================");
			//  ============== ending initialize ===============
			old_symbol_sequence.clear();
			old_symbol_sequence = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i<new_symbol_sequence.size(); i++) {
				ArrayList<Integer> tempGseq = new ArrayList<Integer>();
				
				for(int j = 0; j<new_symbol_sequence.get(i).size(); j++) {
					int temp = new_symbol_sequence.get(i).get(j);
					tempGseq.add(temp);
				}
				
				old_symbol_sequence.add(tempGseq);
			}
			new_symbol_sequence.clear();
			new_symbol_sequence = new ArrayList<ArrayList<Integer>>();
			
		}
		
		endTime = System.currentTimeMillis();
		
		totTime = endTime - startTime;		
		
		System.out.println("time = " + totTime + "ms");
		
	}	
}
