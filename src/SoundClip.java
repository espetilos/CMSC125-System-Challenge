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

	public void start() {
		if (clip != null) {
			clip.setFramePosition(0);
			clip.start();
		}

		else {
			try {
				// Read the audio data into a byte array
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = filename.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, 0, bytesRead);
				}

				// Create an AudioInputStream from the byte array
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
						byteArrayOutputStream.toByteArray());
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

				// Get a Clip object
				clip = AudioSystem.getClip();

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

	public Clip getClip() {
		return clip;
	}
}
