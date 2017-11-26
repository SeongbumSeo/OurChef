package DataManager;
import java.io.*;  
import java.util.*; 

public class DataManager {
	public void run() {  
		String csvFile = "data/data.csv";  
		BufferedReader br = null;
		String line = "";  
		String cvsSplitBy = ","; 		
		
		ArrayList<Ingredient> dataList;
		Ingredient data;
		data=new Ingredient();
		dataList= new ArrayList<Ingredient>();
		
		int x = 0;
		
		try {
			br = new BufferedReader(new InputStreamReader (new FileInputStream(csvFile), "MS949")); 
			String s = ""; 
			while ((line = br.readLine()) != null) { 	
				x ++; 
				String[] Data = line.split(cvsSplitBy); 
				
				for(int i=0; i<Data.length; i++) {  
					if(x == 1) { 
						if(i==0) data.setString("Type");
						if(i==1) data.setString("Id");
						if(i==2) data.setString("Name");
						if(i==3) data.setString("Check");
						if(i==4) data.setString("Favorite");
						
						s += Data[i] + ",";  // 각 셀데이터를 쉼표로 구분 
						
						if(i == 4) s += "\n"; // 로우의 끝일 경우 줄바꿈삽입  
						
						System.out.print(Data[i] + "      ");  
					} 
					else { 
						if(i==0) data.setType(Integer.parseInt(Data[i]));
						if(i==1) data.setId(Integer.parseInt(Data[i]));
						if(i==2) data.setName(Data[i]);
						else if(i==3) data.setCheck(Integer.parseInt(Data[i]));
						else if(i==4) data.setFavorite(Integer.parseInt(Data[i]));
					} 
					
					/*if(Data[3].equals("1")) { // check가 되었다면 
						s += Data[i] + ",";  // 각 셀데이터를 쉼표로 구분 
						
						if(i == 4) s += "\n"; // 로우의 끝일 경우 줄바꿈삽입 
					} */ 
					
				} // for
				
				if(x == 1) {  
					System.out.println("");  
					System.out.println("---------------------");  
				} 
				
				dataList.add(new Ingredient(data));
				
			} // while
			
			for(Ingredient saved:dataList) System.out.println(saved.toString());
			
			generateCsvFile("data/kart.csv",s); // 저장될 파일명과 체크여부가 1 인 데이터를 파라미터로 보냄 
		} // try
		
		catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} 
		catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			if (br != null) { 
				try {  
					br.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		} // catch..finally
	} 

	// cart 만들기(선택한 재료)
	private static void generateCsvFile(String sFileName, String s) // csv 파일을 생성시키는 함수 
	{ 
		try { 
			FileWriter writer = new FileWriter(sFileName); 
			writer.append(s); 
			writer.flush(); 
			writer.close(); 
		}
		
		catch(IOException e) 
		{ 
			e.printStackTrace(); 
		}  
	} // generateCsvFile()
} // DataManager class
