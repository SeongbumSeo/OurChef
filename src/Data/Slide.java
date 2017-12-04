package Data;

import java.util.*;
import Main.IOManager;

public class Slide
{
	private int sequence;
	private String image;
	private String text;
	
	public Slide(int sequence, String image, String text) {
		this.sequence = sequence;
		this.image = image;
		this.text = text;
	}
	
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * CSV 파일로부터 슬라이드들을 로드합니다.
	 * @param filename 대상 CSV 파일
	 * @param recipes 레시피 리스트
	 */
	public static void load(String filename, List<Recipe> recipes) {
		List<String[]> data = IOManager.readCSV(filename);
		HashMap<String, List<Slide>> map = new HashMap<String, List<Slide>>();
		
		for (String[] row : data) {
			Slide slide = new Slide(Integer.parseInt(row[1]), row[2], row[3]);
			
			if (!map.containsKey(row[0]))
				map.put(row[0], new ArrayList<Slide>());
			map.get(row[0]).add(slide);
		}
		
		for (Recipe rec : recipes)
			if (map.containsKey(rec.getName()))
				rec.setSlides(map.get(rec.getName()));
	}
}
