package com.ckenken.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
	
//	public static void main(String[] args) throws FileNotFoundException {
//		
//		Google_Cate_List g = new Google_Cate_List();
//		
//		for (Object key : g.cateMap.keySet()) {
//            System.out.println(key + " : " + g.cateMap.get(key));
//        }
//		
//	}
}
