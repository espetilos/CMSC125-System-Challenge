import java.awt.CardLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;

public class Window extends JFrame {

    private CardLayout layout = new CardLayout();
    private MainMenu gameBundleMain;
    private Other gameBundleCredits;

    public Window() {
        super("System Challenge");
        setFrame(1200, 725);
        setPanels(1200, 725);
        setVisible(true);
    }

    private void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(layout);
    }

    private void setPanels(int width, int height) {
        gameBundleMain = new MainMenu(width, height, "gameBundleMain", this);
        gameBundleCredits = new Other(width, height, "gameBundleCredits", this);

        add("gameBundleMain", gameBundleMain);
        add("gameBundleCredits", gameBundleCredits);
    }

    public void showCard(String card) {
        layout.show(this.getContentPane(), card);
    }

    public Font useFont(String path, int size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}