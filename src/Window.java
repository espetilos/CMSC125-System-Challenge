import java.awt.CardLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;

public class Window extends JFrame {

    private CardLayout layout = new CardLayout();

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

        add("gameBundleMain", gameBundleMain);
        add("gameBundleCredits", gameBundleCredits);
        add("quizMain", quizMain);
        add("pvzMain", pvzMain);
    }

    public void showCard(String card) { // The Card Layout; Method in switching cards
        layout.show(this.getContentPane(), card);
    }

    public Font useFont(String path, int size) { // Method in using a font provided in resources
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}