package Main;

import java.io.*;
import javazoom.jl.player.Player;

public class SoundManager extends Thread
{
	private static SoundManager BGM = new SoundManager("./sounds/background.mp3", true); // 배경음악
	private static boolean isMute; // 음소거 여부
	private Player player; // 사운드 플레이어
	private boolean isLoop; // 반복 여부
	
	/**
	 * 사운드매니저의 생성자입니다.
	 * @param name 사운드 파일명
	 * @param isLoop 반복 여부
	 */
	public SoundManager(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop; // 반복 여부
			// 사운드 파일 읽어 플레이어 객체 생성
			File file = new File(name);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 배경음악을 반환합니다.
	 * @return 배경음악
	 */
	public static SoundManager getBGM() {
		return BGM;
	}
	
	/**
	 * 음소거합니다.
	 */
	public static void mute() {
		isMute = true;
	}
	/**
	 * 음소거를 해제합니다.
	 */
	public static void unmute() {
		isMute = false;
	}
	
	/**
	 * 반복 여부를 설정합니다.
	 * @param toggle 반복 여부
	 */
	public void toggleLoop(boolean toggle) {
		this.isLoop = toggle;
	}
	
	/**
	 * 쓰레드를 중지합니다.
	 */
	public void close() { // 항상 종료할 수 있도록 하는 함수
		isLoop = false;
		player.close();
		this.interrupt(); // 해당 스레드를 중지상태로
	}
	
	/**
	 * 쓰레드 실행 시 호출됩니다.
	 */
	@Override
	public void run() {
		try {
			do {
				if (isMute == false) // 음소거 상태가 아닌 경우 재생
					player.play();
			} while (isLoop); // 무한반복
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
