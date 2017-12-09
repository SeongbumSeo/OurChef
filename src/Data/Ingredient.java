package Data;

import java.util.*;
import Main.IOManager;

public class Ingredient
{
	private int type; // 재료 종류
	private String name; // 재료 이름
	private String icon; // 재료 아이콘 이미지
	private String iconBlack; // 재료 아이콘 이미지 (선택시)
	private String iconLight; // 재료 아이콘 이미지 (마우스오버)
	
	/**
	 * 재료의 생성자입니다.
	 * @param type 재료 종류
	 * @param name 재료 이름
	 * @param icon 재료 아이콘 이미지
	 * @param iconBlack 재료 아이콘 이미지 (선택시)
	 * @param iconLight 재료 아이콘 이미지 (마우스오버)
	 */
	public Ingredient(int type, String name, String icon, String iconBlack, String iconLight) {
		this.type = type;
		this.name = name;
		this.icon = icon;
		this.iconBlack = iconBlack;
		this.iconLight = iconLight;
	}
	
	
	/**
	 * 재료의 종류을 반환합니다.
	 * @return 재료 종류
	 */
	public int getType() {
		return type;
	}
	/**
	 * 재료의 종류를 설정합니다.
	 * @param type 재료 종류
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * 재료의 이름을 반환합니다.
	 * @return 재료 이름
	 */
	public String getName() {
		return name;
	}
	/**
	 * 재료의 이름을 설정합니다.
	 * @param name 재료 이름
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 재료의 아이콘 이미지를 반환합니다.
	 * @return 재료 아이콘 이미지
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 선택됐을 시의 재료 아이콘 이미지를 반환합니다.
	 * @return 재료 아이콘 이미지 (선택시)
	 */
	public String getIconBlack( ) {
		return iconBlack; 
	}
	/**
	 * 마우스오버 시의 재료 아이콘 이미지를 반환합니다.
	 * @return 재료 아이콘 이미지 (마우스오버)
	 */
	public String getIconLight() {
		return iconLight;
	}
	/**
	 * 재료의 아이콘 이미지를 설정합니다.
	 * @param icon 재료 아이콘 이미지
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	

	/**
	 * CSV 파일로부터 재료들을 로드합니다.
	 * @param filename 대상 CSV 파일
	 * @return 재료 리스트
	 */
	public static List<Ingredient> load(String filename) {
		List<String[]> data = IOManager.readCSV(filename); // CSV 파일의 데이터
		List<Ingredient> ing = new ArrayList<Ingredient>(); // 재료 리스트
		
		// CSV 파일의 모든 데이터를 재료 리스트에 추가
		for (String[] row : data)
			ing.add(new Ingredient(Integer.parseInt(row[0])-1, row[1], row[2], row[3], row[4]));
		
		return ing;
	}
	
	/**
	 * 재료 리스트를 해시맵으로 변환합니다.
	 * @param ingredients 재료 리스트
	 * @return 재료 해시맵
	 */
	public static HashMap<String, Ingredient> parseHashMap(List<Ingredient> ingredients) {
		HashMap<String, Ingredient> map = new HashMap<String, Ingredient>();
		
		// 키: 재료 이름, 값: 재료 객체
		for (Ingredient ing : ingredients)
			map.put(ing.getName(), ing);
		
		return map;
	}
}
