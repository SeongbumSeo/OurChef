package Main;

import java.io.*;
import javazoom.jl.player.Player;

<<<<<<< HEAD
public class SoundManager extends Thread
{
	private static SoundManager BGM = new SoundManager("./sounds/background.mp3", true); // 배경음악
	private static boolean isMute; // 음소거 여부
	private Player player; // 사운드 플레이어
	private boolean isLoop; // 반복 여부
=======
public class SoundManager extends Thread {
	
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private static SoundManager introMusic;
	private boolean isMute;
>>>>>>> parent of 7629f44... SoundManager 최적화 및 수정
	
	/**
	 * 사운드매니저의 생성자입니다.
	 * @param name 사운드 파일명
	 * @param isLoop 반복 여부
	 */
	public SoundManager(String name, boolean isLoop) {
		try {
<<<<<<< HEAD
			this.isLoop = isLoop; // 반복 여부
			// 사운드 파일 읽어 플레이어 객체 생성
			File file = new File(name);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
=======
			this.isLoop = isLoop;
			file = new File(name);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
>>>>>>> parent of 7629f44... SoundManager 최적화 및 수정
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
<<<<<<< HEAD
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
=======
	public static void playBackground() {
		introMusic = new SoundManager("./sounds/background.mp3", true);
		introMusic.start();
		introMusic.isMute = false;
	}
	
	public static void stopBackground() {
		introMusic.setIsLoop(false);
		introMusic.close();
		introMusic.interrupt();
		introMusic.isMute = true;
	}
	
	public void setIsLoop(boolean bool) {
		this.isLoop = bool;
	}
	
	public void close() { // 항상 종료할 수 있도록 하는 함수.
>>>>>>> parent of 7629f44... SoundManager 최적화 및 수정
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
<<<<<<< HEAD
				if (isMute == false) // 음소거 상태가 아닌 경우 재생
					player.play();
=======
				if (introMusic.isMute == false) {
					player.play();//실행
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					player = new Player(bis);
				}
>>>>>>> parent of 7629f44... SoundManager 최적화 및 수정
			} while (isLoop); // 무한반복
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
