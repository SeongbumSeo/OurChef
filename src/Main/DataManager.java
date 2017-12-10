package Main;

import java.util.*;
import java.util.List;
import Data.*;

public class DataManager
{
	private static List<Ingredient> ingredients; // 전체 재료
	private static List<Ingredient> cart; // 카트 내 재료
	private static List<Recipe> recipes; // 전체 레시피
	
	/**
	 * 프로그램을 구동하는데 필요한 모든 데이터를 불러오는 메소드입니다.
	 */
	public static void loadData() {
		ingredients = Data.Ingredient.load("data/ingredients.csv"); // 전체 재료 데이터 로드
		recipes = Data.Recipe.load("data/recipes.csv", ingredients); // 전체 레시피 데이터 로드
		Data.Slide.load("data/slides.csv", recipes); // 레시피 별 슬라이드 데이터 로드
		cart = new ArrayList<Ingredient>(); // 카트 리스트 선언
	}
	
	public static List<Ingredient> getIngredients() { return ingredients; }
	public static List<Ingredient> getCart() { return cart; }
	public static List<Recipe> getRecipes() { return recipes; }
}
