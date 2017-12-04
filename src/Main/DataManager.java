package Main;

import java.util.*;
import java.util.List;
import Data.*;

public class DataManager
{
	private static List<Ingredient> ingredients; // 전체 재료
	private static List<Ingredient> cart; // 카트 내 재료
	private static List<Recipe> recipes; // 전체 레시피
	private static List<Recipe> searchedRecipes; // 탐색된 레시피
	private static List<Recipe> favorites; // 즐겨찾기 레시피
	
	/**
	 * 프로그램을 구동하는데 필요한 모든 데이터를 불러오는 메소드입니다.
	 */
	public static void loadData() {
		ingredients = Data.Ingredient.load("data/ingredients.csv");
		recipes = Data.Recipe.load("data/recipes.csv", ingredients);
		Data.Slide.load("data/slides.csv", recipes);
		cart = new ArrayList<Ingredient>();
		favorites = new ArrayList<Recipe>();
	}
	
	public static List<Ingredient> getIngredients() { return ingredients; }
	public static List<Ingredient> getCart() { return cart; }
	public static List<Recipe> getRecipes() { return recipes; }
	public static List<Recipe> getSearchedRecipes() { return searchedRecipes; }
	public static List<Recipe> getFavorites() { return favorites; }
}
