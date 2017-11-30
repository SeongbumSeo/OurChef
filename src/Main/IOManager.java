package Main;

import java.io.*;
import java.util.*;
import com.opencsv.*;

public class IOManager
{
	public static List<String[]> readCSV(String filename) {
		List<String[]> data = new ArrayList<String[]>();
		
		try {
			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			
			String[] str;
			reader.readNext(); // 첫 행은 필드명이므로 스킵
			while ((str = reader.readNext()) != null)
				data.add(str); // 리스트에 적재
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
