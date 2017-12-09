package Data;

import java.util.*;
import Main.*;

public class Recipe
{
	private String name; // 레시피 이름
	private String icon; // 레시피 아이콘 이미지 경로
	private List<Ingredient> ingredients; // 필요한 재료 리스트
	private int level; // 난이도
	private String video; // 동영상 URL
	private String origin; // 출처
	private List<Slide> slides; // 조리법 슬라이드 리스트
	
	/**
	 * 레시피의 생성자입니다.
	 * @param name 레시피 이름
	 * @param icon 레시피 아이콘 이미지
	 * @param ingredients 필요한 재료 리스트
	 * @param level 난이도
	 * @param video 동영상 URL
	 * @param origin 출처
	 */
	public Recipe(String name, String icon, List<Ingredient> ingredients, int level, String video, String origin) {
		this.name = name;
		this.icon = icon;
		this.ingredients = ingredients;
		this.level = level;
		this.video = video;
		this.origin = origin;
	}
	
	/**
	 * 레시피의 이름을 반환합니다.
	 * @return 레시피 이름
	 */
	public String getName() {
		return name;
	}
	/**
	 * 레시피의 이름을 설정합니다.
	 * @param name 레시피 이름
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 레시피의 아이콘 이미지 경로를 반환합니다.
	 * @return 레시피 아이콘 이미지 경로
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 레시피 아이콘 이미지 경로를 설정합니다.
	 * @param icon 레시피 아이콘 이미지 경로
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	/**
	 * 레시피에 필요한 재료 리스트를 반환합니다.
	 * @return 필요한 재료 리스트
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	/**
	 * 레시피에 필요한 재료 리스트를 설정합니다.
	 * @param ingredients 필요한 재료 리스트
	 */
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	/**
	 * 난이도를 반환합니다.
	 * @return 난이도
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * 난이도를 설정합니다.
	 * @param level 난이도
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * 동영상 URL을 반환합니다.
	 * @return 동영상 URL
	 */
	public String getVideo() {
		return video;
	}
	/**
	 * 동영상 URL을 설정합니다.
	 * @param video 동영상 URL
	 */
	public void setVideo(String video) {
		this.video = video;
	}
	
	/**
	 * 출처를 반환합니다.
	 * @return 출처
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * 출처를 설정합니다.
	 * @param origin 출처
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	/**
	 * 조리법 슬라이드 리스트를 반환합니다.
	 * @return 조리법 슬라이드 리스트
	 */
	public List<Slide> getSlides() {
		return slides;
	}
	/**
	 * 조리법 슬라이드 리스트를 설정합니다.
	 * @param slides 조리법 슬라이드 리스트
	 */
	public void setSlides(List<Slide> slides) {
		this.slides = slides;
	}
	
	/**
	 * 재료 리스트에 포함된 재료들로 만들 수 있는 레시피를 반환합니다.
	 * @param dest 탐색 대상 레시피 리스트
	 * @param ingredients 재료 리스트
	 * @return 탐색 결과 레시피 리스트
	 */
	public static List<Recipe> searchRecipes(List<Recipe> dest, List<Ingredient> ingredients) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		// 레시피가 요구하는 모든 재료들이 재료 리스트에 포함되어 있을 시 탐색 결과에 추가
		for (Recipe rec : dest)
			if (ingredients.containsAll(rec.getIngredients()))
				recipes.add(rec);
		
		return recipes;
	}

	/**
	 * CSV 파일로부터 레시피들을 로드합니다.
	 * @param filename 대상 CSV 파일
	 * @param ingredients 전체 재료 리스트
	 * @return 레시피 리스트
	 */
	public static List<Recipe> load(String filename, List<Ingredient> ingredients) {
		List<String[]> data = IOManager.readCSV(filename); // CSV 파일의 데이터
		HashMap<String, Ingredient> ingMap = Ingredient.parseHashMap(ingredients); // 전채 재료의 해시맵
		List<Recipe> rec = new ArrayList<Recipe>(); // 레시피 리스트
		
		for (String[] row : data) {
			// 전체 재료에서 레시피에 사용되는 재료 탐색
			String[] ingArray = row[2].split(",");
			List<Ingredient> ingList = new ArrayList<Ingredient>();
			for (String ingName : ingArray)
				ingList.add(ingMap.get(ingName));
			
			// 레시피 리스트에 추가
			rec.add(new Recipe(row[0], row[1], ingList, Integer.parseInt(row[3]), row[4], row[5]));
		}
		
		return rec;
	}
	
	/**
	 * 레시피 리스트를 해시맵으로 변환합니다.
	 * @param ingredients 레시피 리스트
	 * @return 레시피 해시맵
	 */
	public static HashMap<String, Recipe> parseHashMap(List<Recipe> recipes) {
		HashMap<String, Recipe> map = new HashMap<String, Recipe>();
		
		// 키: 레시피 이름, 값: 레시피 객체
		for (Recipe rec : recipes)
			map.put(rec.getName(), rec);
		
		return map;
	}
}
