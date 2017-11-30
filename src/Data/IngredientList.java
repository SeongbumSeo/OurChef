package Data;

import java.util.*;
import Main.*;

public class IngredientList {
	private List<Ingredient> ingredients;
	
	public IngredientList() {
		ingredients = new ArrayList<Ingredient>();
	}
	public IngredientList(String filename) {
		ingredients = new ArrayList<Ingredient>();
		load(filename);
	}
	
	public void load(String filename) {
		List<String[]> data = DataManager.readCSV(filename);
		Iterator<String[]> itr = data.iterator();
		String[] row;
		
		while (itr.hasNext()) {
			row = itr.next();
			ingredients.add(new Ingredient(Integer.parseInt(row[0]), row[1], row[2]));
		}
	}
}
