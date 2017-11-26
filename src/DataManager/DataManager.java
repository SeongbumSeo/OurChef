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
						
						s += Data[i] + ",";  // �� �������͸� ��ǥ�� ���� 
						
						if(i == 4) s += "\n"; // �ο��� ���� ��� �ٹٲ޻���  
						
						System.out.print(Data[i] + "      ");  
					} 
					else { 
						if(i==0) data.setType(Integer.parseInt(Data[i]));
						if(i==1) data.setId(Integer.parseInt(Data[i]));
						if(i==2) data.setName(Data[i]);
						else if(i==3) data.setCheck(Integer.parseInt(Data[i]));
						else if(i==4) data.setFavorite(Integer.parseInt(Data[i]));
					} 
					
					/*if(Data[3].equals("1")) { // check�� �Ǿ��ٸ� 
						s += Data[i] + ",";  // �� �������͸� ��ǥ�� ���� 
						
						if(i == 4) s += "\n"; // �ο��� ���� ��� �ٹٲ޻��� 
					} */ 
					
				} // for
				
				if(x == 1) {  
					System.out.println("");  
					System.out.println("---------------------");  
				} 
				
				dataList.add(new Ingredient(data));
				
			} // while
			
			for(Ingredient saved:dataList) System.out.println(saved.toString());
			
			generateCsvFile("data/kart.csv",s); // ����� ���ϸ�� üũ���ΰ� 1 �� �����͸� �Ķ���ͷ� ���� 
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

	// cart �����(������ ���)
	private static void generateCsvFile(String sFileName, String s) // csv ������ ������Ű�� �Լ� 
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
