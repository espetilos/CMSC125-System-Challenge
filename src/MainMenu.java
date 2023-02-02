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
    private JButton exitButton;

    public MainMenu(int width, int height, String panel, Window w) {
        setSize(width, height);
        setLayout(null);
        setOpaque(true);

        window = w;

        if (panel == "gameBundleMain") { // If showing the Game Bundle Main Menu Panel
            setButtons();
            setBackground(Color.BLACK);
        }

        setActionAndMouseListeners();
    }

    public JButton button(String text) {
        JButton theButton = new JButton(text);
        theButton.setForeground(Color.WHITE);
        theButton.setFont(window.useFont(System.getProperty("user.dir") + "/src/resources/Emulogic.ttf", 20));
        theButton.setContentAreaFilled(false);
        theButton.setFocusPainted(false);
        theButton.setBorder(BorderFactory.createEmptyBorder());
        return theButton;
    }

    public void setButtons() {
        creditsButton = button("Credits");
        creditsButton.setBounds(450, 550, 300, 50);
        exitButton = button("Exit");
        exitButton.setBounds(450, 600, 300, 50);

        add(creditsButton);
        add(exitButton);
    }

    public void setActionAndMouseListeners() {
        creditsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showCard("gameBundleCredits");
            }
        });

        creditsButton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                creditsButton.setForeground(Color.ORANGE);
            }

            public void mouseExited(MouseEvent e) {
                creditsButton.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                creditsButton.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        exitButton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(Color.ORANGE);
            }

            public void mouseExited(MouseEvent e) {
                exitButton.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                exitButton.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });
    }
}