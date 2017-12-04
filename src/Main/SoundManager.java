package Main;

import Sound.Sound;
import java.io.File;

public class SoundManager {
	private static Sound mSound;
	
	public static void playBackground() {
		File clap = new File("./sounds/background.mp3");
		mSound = new Sound(clap);
		mSound.playLoop();
	}
	
}
