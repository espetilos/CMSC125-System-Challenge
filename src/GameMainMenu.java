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

public class GameMainMenu extends JPanel {

    private Window window;
    private JButton playButton;
    private JButton howToPlayButton;
    private JButton exitButton;

    private Color orange = new Color(226, 161, 101);

    public GameMainMenu(int width, int height, String panel, Window w) {
        setSize(width, height);
        setLayout(null);
        setOpaque(true);

        window = w;

        setBackground(Color.BLACK);
        setButtons(panel);
        setLabels(panel);

        setActionAndMouseListeners();
    }

    private Font setFont(String panel, int size) {
        if (panel == "quizMain")
            return window.useFont(System.getProperty("user.dir") + "/src/resources/Alyssum-Sans.ttf", size);
        if (panel == "pvzMain")
            return window.useFont(System.getProperty("user.dir") + "/src/resources/Emulogic.ttf", size);
        if (panel == "sokobanMain")
            return window.useFont(System.getProperty("user.dir") + "/src/resources/Garet-Book.ttf", size);
        return null;
    }

    private JButton button(String text, Font font, Color color, int x, int y, int width, int height) {
        JButton theButton = new JButton(text);
        theButton.setForeground(color);
        theButton.setFont(font);
        theButton.setBounds(x, y, width, height);
        theButton.setContentAreaFilled(false);
        theButton.setFocusPainted(false);
        theButton.setBorder(BorderFactory.createEmptyBorder());
        return theButton;
    }

    private JLabel label(String label, Font font, Color color, int x, int y, int width, int height,
            ImageIcon icon) {
        JLabel theLabel = new JLabel(label);
        theLabel.setIcon(icon);
        theLabel.setFont(font);
        theLabel.setForeground(color);
        theLabel.setBounds(x, y, width, height);
        theLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return theLabel;
    }

    private void setButtons(String panel) {
        if (panel == "quizMain") {
            playButton = button("Play", setFont(panel, 30), Color.WHITE, 450, 350, 300, 50);
            howToPlayButton = button("How to Play", setFont(panel, 30), Color.WHITE, 450, 400, 300, 50);
            exitButton = button("Exit", setFont(panel, 30), Color.WHITE, 450, 450, 300, 50);
        }

        if (panel == "pvzMain") {
            playButton = button("Play", setFont(panel, 20), Color.WHITE, 750, 350, 300, 50);
            howToPlayButton = button("How to Play", setFont(panel, 20), Color.WHITE, 750, 400, 300, 50);
            exitButton = button("Exit", setFont(panel, 20), Color.WHITE, 750, 450, 300, 50);
        }

        if (panel == "sokobanMain") {
            playButton = button("Play", setFont(panel, 30), Color.WHITE, 465, 350, 270, 80);
            howToPlayButton = button("How to Play", setFont(panel, 30), Color.WHITE, 465, 450, 270, 80);
            exitButton = button("Exit", setFont(panel, 30), Color.WHITE, 465, 550, 270, 80);
        }

        add(playButton);
        add(howToPlayButton);
        add(exitButton);
    }

    private void setLabels(String panel) {
        if (panel == "quizMain") {
            JLabel quizIcon = label(null, null, null, 260, 130, 150, 130,
                    new ImageIcon(System.getProperty("user.dir") + "/src/resources/quizIcon.png"));
            add(quizIcon);

            JLabel gameLabel1 = label("Are you Smarter", setFont(panel, 45), orange,
                    440, 140, 490, 50, null);
            add(gameLabel1);

            JLabel gameLabel2 = label("than my OS?", setFont(panel, 60), Color.WHITE,
                    440, 200, 490, 50, null);
            add(gameLabel2);

            JLabel mainBG = label(null, null, null, 0, 0, 1200, 725,
                    new ImageIcon(System.getProperty("user.dir") + "/src/resources/quizLaptop.png"));
            add(mainBG);
        }

        if (panel == "pvzMain") {
            JLabel gameLabel1 = label("System Defense", setFont(panel, 45), Color.WHITE,
                    285, 50, 630, 50, null);
            add(gameLabel1);

            JLabel mainBG = label(null, null, null, 0, 0, 1200, 725,
                    new ImageIcon(System.getProperty("user.dir") + "/src/resources/pvzGlobe.png"));
            add(mainBG);
        }

        if (panel == "sokobanMain") {
            JLabel gameLabel1 = label("SYSTEM CALLS", setFont(panel, 45), Color.WHITE,
                    285, 150, 630, 50, null);
            add(gameLabel1);

            JLabel gameLabel2 = label("CENTER", setFont(panel, 45), Color.WHITE,
                    285, 200, 630, 50, null);
            add(gameLabel2);


            JLabel mainBG = label(null, null, null, 0, 0, 1200, 725,
                    new ImageIcon(System.getProperty("user.dir") + "/src/resources/sokobanStars.png"));
            add(mainBG);
        }
    }

    public void setActionAndMouseListeners() {
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        playButton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                playButton.setForeground(orange);
            }

            public void mouseExited(MouseEvent e) {
                playButton.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                playButton.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });

        howToPlayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        howToPlayButton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                howToPlayButton.setForeground(orange);
            }

            public void mouseExited(MouseEvent e) {
                howToPlayButton.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                howToPlayButton.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showCard("gameBundleMain");
            }
        });

        exitButton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(orange);
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