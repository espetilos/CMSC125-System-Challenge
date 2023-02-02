import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MainMenu extends JPanel {

    private Window window;
    private JButton creditsButton;
    private JButton exitButton;
    private JLabel gameBundleLabel;

    private Font emulogicFont;

    public MainMenu(int width, int height, String panel, Window w) {
        setSize(width, height);
        setLayout(null);
        setOpaque(true);

        window = w;
        emulogicFont = window.useFont(System.getProperty("user.dir") + "/src/resources/Emulogic.ttf", 20);

        if (panel == "gameBundleMain") { // If showing the Game Bundle Main Menu Panel
            setBackground(Color.BLACK);
            setButtons();
            gameBundleLabel = label("System Challenge", emulogicFont, Color.WHITE);
            gameBundleLabel.setBounds(430, 50, 320, 50);
            add(gameBundleLabel);
        }

        setActionAndMouseListeners();
    }

    private JButton button(String text, Font font, Color color) {
        JButton theButton = new JButton(text);
        theButton.setForeground(color);
        theButton.setFont(font);
        theButton.setContentAreaFilled(false);
        theButton.setFocusPainted(false);
        theButton.setBorder(BorderFactory.createEmptyBorder());
        return theButton;
    }

    private JLabel label(String label, Font font, Color color) {
        JLabel theLabel = new JLabel(label);
        theLabel.setFont(font);
        theLabel.setForeground(color);
        theLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return theLabel;
    }

    public void setButtons() {
        creditsButton = button("Credits", emulogicFont, Color.WHITE);
        creditsButton.setBounds(450, 550, 300, 50);

        exitButton = button("Exit", emulogicFont, Color.WHITE);
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