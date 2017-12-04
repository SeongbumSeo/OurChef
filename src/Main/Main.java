package Main;

import Scene.*;

public class Main
{
	/**
	 * 프로그램의 주 실행 콜백입니다.
	 * @param args 실행 인자
	 */
	public static void main(String[] args) {
		DataManager.loadData();
		SceneManager.showWindow("Our Chef", 1600, 900);
		SceneManager.switchScene(new IntroScene());
		SoundManager.playBackground();
	}
}
