import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class RotateLabel extends JLabel {
    public RotateLabel(ImageIcon icon, int x, int y) {
        super(icon);
        setBounds(x, y, icon.getIconWidth() + 100, icon.getIconHeight() + 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gx = (Graphics2D) g;
        gx.rotate(-0.55, getX() + getWidth() / 2, getY() + getHeight() / 2);
        super.paintComponent(g);
    }
}
