package Main;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class SoundManager extends Thread {
	
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private static SoundManager introMusic;
	
	public SoundManager(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(name);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void playBackground() {
		introMusic = new SoundManager("./sounds/background.mp3", true);
		introMusic.start();
	}
	
	public static void stopBackground() {
		introMusic.setIsLoop(false);
		introMusic.close();
		introMusic.interrupt();
	}
	
	public void setIsLoop(boolean bool) {
		this.isLoop = bool;
	}
	
	public void close() { // 항상 종료할 수 있도록 하는 함수.
		isLoop = false;
		player.close();
		this.interrupt(); // 해당 스레드를 중지상태로
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();//실행
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop); // 무한반복
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
