package Main;

import java.io.*;
import java.util.*;
import com.opencsv.*;

public class IOManager
{
	/**
	 * CSV 파일을 읽어 문자열 배열의 리스트로 반환합니다.
	 * @param filename 파일명
	 * @return 문자열 배열의 리스트
	 */
	public static List<String[]> readCSV(String filename) {
		List<String[]> data = new ArrayList<String[]>();
		
		try {
			// CSV 파일을 UTF-8 인코딩으로 오픈합니다.
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
