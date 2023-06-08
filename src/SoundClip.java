import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundClip {
	private Clip clip;
	private InputStream filename;

	public SoundClip(String sound) {
		filename = getClass().getClassLoader().getResourceAsStream(sound);
	}

	private int lastFrame;

	public void stop() {
		clip.stop();
	}

	public void pause() {
		if (clip != null && clip.isRunning()) {
			lastFrame = clip.getFramePosition();
			clip.stop();
		}
	}

	public void resume() {
		if (clip != null && !clip.isRunning()) {
			if (lastFrame < clip.getFrameLength()) {
				clip.setFramePosition(lastFrame);
			} else {
				clip.setFramePosition(0);
			}
			clip.start();
		}
	}

	public void start() {
		try {
			// Read the audio data into a byte array
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = filename.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, bytesRead);
			}

			// Create an AudioInputStream from the byte array
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

			// Get a Clip object
			Clip clip = AudioSystem.getClip();

			// Open the audioInputStream with the Clip
			clip.open(audioInputStream);
			// Set the loop count to indefinitely
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			// Play the audio
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
