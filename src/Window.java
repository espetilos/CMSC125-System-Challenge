import javax.swing.JFrame;

public class Window extends JFrame {

    public Window() {
        super("System Challenge");
        setFrame(1200, 725);
        setVisible(true);
    }

    private void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

}
