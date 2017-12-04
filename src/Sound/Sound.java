package Sound;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	private Clip clip;
	private File clap;
	
	public Sound(File sound) {
		clap = sound;
		
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(clap));
		}
		catch(Exception e) {
			
		}
	}
	
	public void playLoop() {
		clip.setFramePosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}
	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}
	public void stop() {
		clip.stop();
	}
}
