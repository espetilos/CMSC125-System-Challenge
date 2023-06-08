import java.awt.CardLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;

public class Window extends JFrame {

    private CardLayout layout = new CardLayout();
    private SoundClip quizsoundmain = new SoundClip("quiz/quizAudio/quizMainAudio.wav");
    private SoundClip pvzsoundmain = new SoundClip("pvz/pvzAudio/pvzMainAudio.wav");
    private SoundClip sokobansoundmain = new SoundClip("sokoban/sokobanAudio.wav");

    public Window() { // Set up the Window of the bundle
        super("System Challenge"); // Set up the title of the bumble
        setFrame(1200, 725); // Set up the JFrame
        setPanels(1200, 725); // Set up the JPanels
        setVisible(true); // Makes the whole window visible
    }

    private void setFrame(int width, int height) { // Set up the JFrame
        setSize(width, height); // Set up the size of the JFrame
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop Program after Close
        setResizable(false); // Avoid resizing JFrame
        setLayout(layout); // Use Card Layout
    }

    private void setPanels(int width, int height) { // Set up the JPanels
        MainMenu gameBundleMain = new MainMenu(width, height, "gameBundleMain", this); // The Game Bundle Main Menu
                                                                                       // Panel
        Other gameBundleCredits = new Other(width, height, "gameBundleCredits", this); // The Credits Panel

        GameMainMenu quizMain = new GameMainMenu(width, height, "quizMain", this); // The Game1 Main Menu Panel
        GameMainMenu pvzMain = new GameMainMenu(width, height, "pvzMain", this); // The Game2 Main Menu Panel
        GameMainMenu sokobanMain = new GameMainMenu(width, height, "sokobanMain", this); // The Game3 Main Menu Panel

        GameHowToPlay quizHowToPlay = new GameHowToPlay(width, height, "quizHowToPlay", this);
        GameHowToPlay quizHowToPlay1 = new GameHowToPlay(width, height, "quizHowToPlay1", this);
        GameHowToPlay quizHowToPlay2 = new GameHowToPlay(width, height, "quizHowToPlay2", this);

        GameHowToPlay pvzHowToPlay = new GameHowToPlay(width, height, "pvzHowToPlay", this);
        GameHowToPlay pvzHowToPlay1 = new GameHowToPlay(width, height, "pvzHowToPlay1", this);
        GameHowToPlay pvzHowToPlay2 = new GameHowToPlay(width, height, "pvzHowToPlay2", this);
        GameHowToPlay pvzHowToPlay3 = new GameHowToPlay(width, height, "pvzHowToPlay3", this);
        GameHowToPlay pvzHowToPlay4 = new GameHowToPlay(width, height, "pvzHowToPlay4", this);

        GameHowToPlay sokobanHowToPlay = new GameHowToPlay(width, height, "sokobanHowToPlay", this);
        GameHowToPlay sokobanHowToPlay1 = new GameHowToPlay(width, height, "sokobanHowToPlay1", this);
        GameHowToPlay sokobanHowToPlay2 = new GameHowToPlay(width, height, "sokobanHowToPlay2", this);
        GameHowToPlay sokobanHowToPlay3 = new GameHowToPlay(width, height, "sokobanHowToPlay3", this);
        GameHowToPlay sokobanHowToPlay4 = new GameHowToPlay(width, height, "sokobanHowToPlay4", this);
        GameHowToPlay sokobanHowToPlay5 = new GameHowToPlay(width, height, "sokobanHowToPlay5", this);
        GameHowToPlay sokobanHowToPlay6 = new GameHowToPlay(width, height, "sokobanHowToPlay6", this);

        add("gameBundleMain", gameBundleMain);
        add("gameBundleCredits", gameBundleCredits);
        add("quizMain", quizMain);
        add("pvzMain", pvzMain);
        add("sokobanMain", sokobanMain);
        add("quizHowToPlay", quizHowToPlay);
        add("quizHowToPlay1", quizHowToPlay1);
        add("quizHowToPlay2", quizHowToPlay2);
        add("pvzHowToPlay", pvzHowToPlay);
        add("pvzHowToPlay1", pvzHowToPlay1);
        add("pvzHowToPlay2", pvzHowToPlay2);
        add("pvzHowToPlay3", pvzHowToPlay3);
        add("pvzHowToPlay4", pvzHowToPlay4);
        add("sokobanHowToPlay", sokobanHowToPlay);
        add("sokobanHowToPlay1", sokobanHowToPlay1);
        add("sokobanHowToPlay2", sokobanHowToPlay2);
        add("sokobanHowToPlay3", sokobanHowToPlay3);
        add("sokobanHowToPlay4", sokobanHowToPlay4);
        add("sokobanHowToPlay5", sokobanHowToPlay5);
        add("sokobanHowToPlay6", sokobanHowToPlay6);
    }

    public void showCard(String card) { // The Card Layout; Method in switching cards
        layout.show(this.getContentPane(), card);
    }

    public void startAudio(String card) {
        if (card == "quizMain") {
            quizsoundmain.start();
        }
        if (card == "pvzMain") {
            pvzsoundmain.start();
        }
        if (card == "sokobanMain") {
            sokobansoundmain.start();
        }
    }

    public void pauseAudio(String card) {
        if (card == "quizMain") {
            quizsoundmain.pause();
        }
        if (card == "pvzMain") {
            pvzsoundmain.pause();
        }
        if (card == "sokobanMain") {
            sokobansoundmain.pause();
        }
    }

    public void stopAudio(String card) {
        if (card == "quizMain") {
            quizsoundmain.stop();
        }
        if (card == "pvzMain") {
            pvzsoundmain.stop();
        }
        if (card == "sokobanMain") {
            sokobansoundmain.stop();
        }
    }

    public void resumeAudio(String card) {
        if (card == "quizMain") {
            quizsoundmain.resume();
        }
        if (card == "pvzMain") {
            pvzsoundmain.resume();
        }
        if (card == "sokobanMain") {
            sokobansoundmain.resume();
        }
    }

    public Font useFont(InputStream path, int size) { // Method in using a font provided in resources
        try {
            return Font.createFont(Font.TRUETYPE_FONT, path).deriveFont(Font.PLAIN, size);
            // return Font.createFont(size, path);
            // return Font.createFont(Font.TRUETYPE_FONT, new
            // File(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}