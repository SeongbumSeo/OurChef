package Data;

import java.util.*;

import Main.IOManager;

public class Recipe
{
	private String name;
	private String icon;
	private List<Ingredient> ingredients;
	private int level;
	private String video;
	private String origin;
	
	public Recipe(String name, String icon, List<Ingredient> ingredients, int level, String video, String origin) {
		this.name = name;
		this.icon = icon;
		this.ingredients = ingredients;
		this.level = level;
		this.video = video;
		this.origin = origin;
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
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	/**
	 * 재료 리스트에 포함된 재료들로 만들 수 있는 레시피를 반환합니다.
	 * @param dest 탐색 대상 레시피 리스트
	 * @param ingredients 재료 리스트
	 * @return 탐색 결과 레시피 리스트
	 */
	public static List<Recipe> searchRecipes(List<Recipe> dest, List<Ingredient> ingredients) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		
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
		List<String[]> data = IOManager.readCSV(filename);
		HashMap<String, Ingredient> ingMap = Ingredient.parseHashMap(ingredients);
		List<Recipe> rec = new ArrayList<Recipe>();
		
		for (String[] row : data) {
			// 전체 재료에서 레시피에 사용되는 재료 탐색
			String[] ingArray = row[2].split(",");
			List<Ingredient> ingList = new ArrayList<Ingredient>();
			for (String ingName : ingArray)
				ingList.add(ingMap.get(ingName));
			
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
		
		for (Recipe rec : recipes)
			map.put(rec.getName(), rec);
		
		return map;
	}
}
