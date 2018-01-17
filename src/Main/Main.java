package Main;

import Scene.*;

public class Main
{
	/**
	 * 프로그램의 주 실행 콜백입니다.
	 * @param args 실행 인자
	 */
	public static void main(String[] args) {
		DataManager.loadData(); // 프로그램 구동에 필요한 데이터 로드
		SceneManager.showWindow("Our Chef", 1600, 900); // 윈도우 보여주기
		SceneManager.switchScene(new IntroScene()); // IntroScene으로 씬 전환
		SoundManager.playBackgroud(); // BGM 재생
	}
}
