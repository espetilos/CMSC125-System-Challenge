import javax.swing.JPanel;

public class SokobanQuestion extends JPanel {

    private Window window;

    public SokobanQuestion(int width, int height, Window w) {
        setSize(width, height);
        setLayout(null);
        setOpaque(true);

        window = w;
    }
}