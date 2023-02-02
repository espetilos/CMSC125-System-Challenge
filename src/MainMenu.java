import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MainMenu extends JPanel {

    private Window window;
    private JButton creditsButton;

    public MainMenu(int width, int height, String panel, Window w) {
        setSize(width, height);
        setLayout(null);
        setOpaque(false);

        window = w;

        if (panel == "gameBundleMain") {
            setBackground(Color.RED);
        }

        setButtons();
        setActionAndMouseListeners();
    }

    public JButton button(String text) {
        JButton theButton = new JButton(text);
        theButton.setForeground(Color.ORANGE);
        theButton.setContentAreaFilled(true);
        theButton.setFocusPainted(false);
        theButton.setBorder(BorderFactory.createEmptyBorder());
        return theButton;
    }

    public void setButtons() {
        creditsButton = button("Credits");
        creditsButton.setBounds(670, 480, 350, 50);

        add(creditsButton);
    }

    public void setActionAndMouseListeners() {
        creditsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showCard("gameBundleCredits");
            }
        });

        creditsButton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                creditsButton.setForeground(Color.BLACK);
            }

            public void mouseExited(MouseEvent e) {
                creditsButton.setForeground(Color.ORANGE);
            }

            public void mouseReleased(MouseEvent e) {
                creditsButton.setForeground(Color.ORANGE);
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });

    }
}