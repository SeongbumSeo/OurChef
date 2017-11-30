package Data;

import java.util.*;

import Main.IOManager;

public class Recipe
{
	private String name;
	private String[] ingredients;
	private int level;
	private String video;
	private String origin;
	
	public Recipe(String name, String ingredients, int level, String video, String origin) {
		this.name = name;
		this.ingredients = ingredients.split(",");
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
	
	public String[] getIngredients() {
		return ingredients;
	}
	public void setIngredients(String[] ingredients) {
		this.ingredients = ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients.split(",");
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
	 * CSV 파일로부터 레시피들을 로드합니다.
	 * @param filename 대상 CSV 파일
	 * @return 레시피 리스트
	 */
	public static List<Recipe> load(String filename) {
		List<String[]> data = IOManager.readCSV(filename);
		Iterator<String[]> itr = data.iterator();
		String[] row;
		List<Recipe> rec = new ArrayList<Recipe>();
		
		while (itr.hasNext()) {
			row = itr.next();
			rec.add(new Recipe(row[0], row[1], Integer.parseInt(row[2]), row[3], row[4]));
		}
		
		return rec;
	}
}
