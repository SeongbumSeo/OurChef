package Main;

import java.io.*;
import java.util.*;
import com.opencsv.*;

public class DataManager
{
	public static List<String[]> readCSV(String filename) {
		List<String[]> data = new ArrayList<String[]>();
		
		try {
			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			
			String[] str;
			while ((str = reader.readNext()) != null)
				data.add(str);
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
