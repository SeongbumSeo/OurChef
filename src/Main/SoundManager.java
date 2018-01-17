package Main;

import java.io.*;
import javazoom.jl.player.Player;

public class SoundManager extends Thread
{
	private Player player; // 사운드 플레이어
	private boolean isLoop; // 반복 여부
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private static SoundManager introMusic;
	private boolean isMute;
	
	/**
	 * 사운드 매니저의 생성자입니다.
	 * @param name 파일의 이름입니다.
	 * @param isLoop 반복여부를 나타냅니다.
	 */
	public SoundManager(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop; // 반복 여부
			file = new File(name);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 배경음악을 재생합니다.
	 */
	public static void playBackgroud() {
		introMusic = new SoundManager("./sounds/background.mp3", true);
		introMusic.start();
		introMusic.isMute = false;
	}
	
	/**
	 * 배경음악을 중지하고 음소거 모드로 변환합니다.
	 */
	public static void stopBackground() {
		introMusic.setIsLoop(false);
		introMusic.close();
		introMusic.interrupt();
		introMusic.isMute = true;
	}
	
	/**
	 * 배경음악의 반복 여부를 지정합니다.
	 * @param bool
	 */
	public void setIsLoop(boolean bool) {
		this.isLoop = bool;
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
				if (introMusic.isMute == false) // 음소거 상태가 아닌 경우 재생
					player.play();
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					player = new Player(bis);
			} while (isLoop); // 무한반복
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
