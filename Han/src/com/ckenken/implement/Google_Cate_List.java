package com.ckenken.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import com.ckenken.io.JDBC;

public class Google_Cate_List {
	
	public HashMap<String, Integer> cateMap = new HashMap<String, Integer>();
	
	public Google_Cate_List() throws FileNotFoundException
	{
		File f = new File("google_cates.txt");
		
		FileInputStream FIS = new FileInputStream(f);
		
		Scanner scanner = new Scanner(FIS);
		int index = 0;
		while(scanner.hasNext()) 
		{
			String line = scanner.nextLine();
			
			cateMap.put(line, index);
			index++;
		}
		scanner.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
	
		JDBC jdbc = new JDBC("han");
		
		String sql = "select * from sequence30 where symbol = -22";
		
		ResultSet rs = jdbc.query(sql);
		
		try {
			
			
			if (rs.next()) {
				System.out.println("12345");
			}
			else 
			{
				System.out.println("23456");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		Google_Cate_List g = new Google_Cate_List();
//		
//		for (Object key : g.cateMap.keySet()) {
//            System.out.println(key + " : " + g.cateMap.get(key));
//        }
//		
	}
}
