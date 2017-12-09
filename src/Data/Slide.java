package Data;

import java.util.*;
import Main.IOManager;

public class Slide
{
	private int sequence; // 슬라이드 순서
	private String image; // 슬라이드 이미지
	private String text; // 슬라이드 텍스트
	
	/**
	 * 슬라이드의 생성자입니다.
	 * @param sequence 슬라이드 순서
	 * @param image 슬라이드 이미지
	 * @param text 슬라이드 텍스트
	 */
	public Slide(int sequence, String image, String text) {
		this.sequence = sequence;
		this.image = image;
		this.text = text;
	}
	
	/**
	 * 슬라이드 순서를 반환합니다.
	 * @return 슬라이드 순서
	 */
	public int getSequence() {
		return sequence;
	}
	/**
	 * 슬라이드 순서를 설정합니다.
	 * @param sequence 슬라이드 순서
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * 슬라이드 이미지를 반환합니다.
	 * @return 슬라이드 이미지
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 슬라이드 이미지를 설정합니다.
	 * @param image 슬라이드 이미지
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * 슬라이드 텍스트를 반환합니다.
	 * @return 슬라이드 텍스트
	 */
	public String getText() {
		return text;
	}
	/**
	 * 슬라이드 텍스트를 설정합니다.
	 * @param text 슬라이드 텍스트
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * CSV 파일로부터 슬라이드들을 로드합니다.
	 * @param filename 대상 CSV 파일
	 * @param recipes 레시피 리스트
	 */
	public static void load(String filename, List<Recipe> recipes) {
		List<String[]> data = IOManager.readCSV(filename); // CSV 파일의 데이터
		HashMap<String, List<Slide>> map = new HashMap<String, List<Slide>>(); // <레시피, 슬라이드 리스트> 해시맵
		
		for (String[] row : data) {
			Slide slide = new Slide(Integer.parseInt(row[1]), row[2], row[3]);
			
			// 키: 레시피 이름, 값: 슬라이드 리스트
			// 슬라이드 객체를 슬라이드 리스트에 추가함
			if (!map.containsKey(row[0]))
				map.put(row[0], new ArrayList<Slide>());
			map.get(row[0]).add(slide);
		}
		
		// 해시맵을 이용해 각 레시피에 슬라이드 리스트들을 설정함
		for (Recipe rec : recipes)
			if (map.containsKey(rec.getName()))
				rec.setSlides(map.get(rec.getName()));
	}
}
