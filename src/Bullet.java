import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bullet {
    // Timer task for automatic shooting of bullets over time
    // int thisIndex = indexYPos[ctr];
    // int threatXPos = 1210;
    // int thisCtr = ctr;
    public int x = 0;
    public int y = 0;

    public Bullet() {
        Timer bulletTimer = new Timer();
        Fire bulletTask = new Fire();
        bulletTimer.schedule(bulletTask, 0, 10); // Travel of bullets over time
    }

    public JLabel bullet = new JLabel(
            new ImageIcon(resizeImage(getClass().getClassLoader().getResourceAsStream("pvz/pvzBullet.png"), 20, 20)));

    private Image resizeImage(InputStream file, int width, int height) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void setXandY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    class Fire extends TimerTask {
        @Override
        public void run() {
            bullet.setLocation(x, y);
            x += 3;

            if (x >= 1500) {
                bullet = null;
                cancel();
            }
        }
    }
}