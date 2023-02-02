import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Other extends JPanel {
    private Window window;

    private JButton backButton;

    public Other(int width, int height, String panel, Window g) {
        setSize(width, height);
        setLayout(null);
        setOpaque(false);

        setButtons();

        if (panel == "credits")
            setBackground(Color.black); // PLACEHOLDER

        window = g;
        setActionAndMouseListeners();
    }

    public void setButtons() {
        backButton = new JButton("BACK");
        backButton.setForeground(Color.ORANGE);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setBounds(10, 10, 200, 100);
        add(backButton);
    }

    public void setActionAndMouseListeners() {
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showCard("gameBundleMain");
            }
        });

        backButton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                backButton.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                backButton.setForeground(Color.ORANGE);
            }

            public void mouseReleased(MouseEvent e) {
                backButton.setForeground(Color.ORANGE);
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });
    }
}