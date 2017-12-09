package Main;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class SoundManager extends Thread
{
	private static SoundManager BGM = new SoundManager("./sounds/background.mp3", true);
	private static boolean isMute;
	private Player player;
	private boolean isLoop;
	
	public SoundManager(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			File file = new File(name);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static SoundManager getBGM() {
		return BGM;
	}
	
	public static void mute() {
		isMute = true;
	}
	
	public static void unmute() {
		isMute = false;
	}
	
	public void toggleLoop(boolean bool) {
		this.isLoop = bool;
	}
	
	public void close() { // 항상 종료할 수 있도록 하는 함수
		isLoop = false;
		player.close();
		this.interrupt(); // 해당 스레드를 중지상태로
	}
	
	@Override
	public void run() {
		try {
			do {
				if (isMute == false)
					player.play(); // 실행
			} while (isLoop); // 무한반복
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
