package Data;

import java.util.*;

import Main.IOManager;

public class Ingredient
{
	private int type;
	private String name;
	private String icon;
	
	public Ingredient(int type, String name, String icon) {
		this.type = type;
		this.name = name;
		this.icon = icon;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * CSV 파일로부터 재료들을 로드합니다.
	 * @param filename 대상 CSV 파일
	 * @return 재료 리스트
	 */
	public static List<Ingredient> load(String filename) {
		List<String[]> data = IOManager.readCSV(filename);
		List<Ingredient> ing = new ArrayList<Ingredient>();
		
		for (String[] row : data)
			ing.add(new Ingredient(Integer.parseInt(row[0])-1, row[1], row[2]));
		
		return ing;
	}
	
	/**
	 * 재료 리스트를 해시맵으로 변환합니다.
	 * @param ingredients 재료 리스트
	 * @return 재료 해시맵
	 */
	public static HashMap<String, Ingredient> parseHashMap(List<Ingredient> ingredients) {
		HashMap<String, Ingredient> map = new HashMap<String, Ingredient>();
		
		for (Ingredient ing : ingredients)
			map.put(ing.getName(), ing);
		
		return map;
	}
}
