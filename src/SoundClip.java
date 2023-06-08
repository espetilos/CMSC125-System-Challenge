import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundClip {
	private Clip clip;
	private final InputStream filename;
	private final int identifier;

	public SoundClip(final InputStream filename, final int identifier) {
		this.filename = filename;
		this.identifier = identifier;
	}

	public void stop() {
		clip.stop();
	}

	private int lastFrame;

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
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(filename);
			clip = AudioSystem.getClip();
			clip.open(audioIn);

			switch (identifier) {
				case 0:
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					break;
				case 1:
					clip.start();
					break;
			}

		} catch (final UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
