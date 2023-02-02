import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
    private JButton quizButton;
    private JButton pvzButton;
    private JButton sokobanButton;
    private JLabel gameBundleLabel;

    private Font emulogicFont;

    public MainMenu(int width, int height, String panel, Window w) {
        setSize(width, height);
        setLayout(null);
        setOpaque(true);

        window = w;
        emulogicFont = window.useFont(System.getProperty("user.dir") + "/src/resources/Emulogic.ttf", 30);

        if (panel == "gameBundleMain") { // If showing the Game Bundle Main Menu Panel
            setBackground(Color.BLACK);
            setButtons();

            gameBundleLabel = label("System Challenge", emulogicFont, Color.WHITE); // Game Bundle Label
            gameBundleLabel.setBounds(355, 20, 490, 50);
            add(gameBundleLabel);
        }

        setActionAndMouseListeners();
    }

    private JButton button(String text, Font font, Color color) {
        JButton theButton = new JButton();

        if (text == "game1" || text == "game2" || text == "game3") {
            if (text == "game1")
                theButton.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/resources/game1.png"));
            else if (text == "game2")
                theButton.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/resources/game2.png"));
            else if (text == "game3")
                theButton.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/resources/game3.png"));
        }

        else {
            theButton.setText(text);
            theButton.setForeground(color);
            theButton.setFont(font);
        }

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
        quizButton = button("game1", null, null);
        pvzButton = button("game2", null, null);
        sokobanButton = button("game3", null, null);

        quizButton.setBounds(55, 100, 313, 438);
        pvzButton.setBounds(435, 100, 313, 438);
        sokobanButton.setBounds(815, 100, 313, 438);

        creditsButton = button("Credits", emulogicFont, Color.WHITE);
        creditsButton.setBounds(450, 560, 300, 50);

        exitButton = button("Exit", emulogicFont, Color.WHITE);
        exitButton.setBounds(450, 610, 300, 50);

        add(creditsButton);
        add(exitButton);
        add(quizButton);
        add(pvzButton);
        add(sokobanButton);
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