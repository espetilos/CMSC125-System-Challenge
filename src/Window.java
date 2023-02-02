import javax.swing.JFrame;
import java.awt.CardLayout;

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
}