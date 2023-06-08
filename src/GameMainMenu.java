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

        setActionAndMouseListeners(panel);
    }

    private Font setFont(String panel, int size) {
        if (panel == "quizMain")
            return window.useFont(getClass().getClassLoader().getResourceAsStream("Alyssum-Sans.ttf"), size);
        if (panel == "pvzMain")
            return window.useFont(getClass().getClassLoader().getResourceAsStream("emulogic.ttf"), size);
        if (panel == "sokobanMain")
            return window.useFont(getClass().getClassLoader().getResourceAsStream("Garet-Book.ttf"), size);
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
                    new ImageIcon(getClass()
                            .getClassLoader()
                            .getResource("quiz/quizMainMenu/quizIcon.png")));
            add(quizIcon);

            JLabel gameLabel1 = label("Are you Smarter", setFont(panel, 45), orange,
                    440, 140, 490, 50, null);
            add(gameLabel1);

            JLabel gameLabel2 = label("than my OS?", setFont(panel, 60), Color.WHITE,
                    440, 200, 490, 50, null);
            add(gameLabel2);

            JLabel mainBG = label(null, null, null, 0, 0, 1200, 725,
                    new ImageIcon(getClass()
                            .getClassLoader()
                            .getResource("quiz/quizLaptop.png")));
            add(mainBG);
        }

        if (panel == "pvzMain") {
            JLabel gameLabel1 = label("System Defense", setFont(panel, 45), Color.WHITE,
                    285, 50, 630, 50, null);
            JLabel mainBG = label(null, null, null, 0, 0, 1200, 725,
                    new ImageIcon(getClass()
                            .getClassLoader()
                            .getResource("pvz/pvzGlobe.png")));

            add(gameLabel1);
            add(mainBG);
        }

        if (panel == "sokobanMain") {
            JLabel gameLabel1 = label("SYSTEM CALLS", setFont(panel, 45), Color.WHITE,
                    285, 150, 630, 50, null);
            JLabel gameLabel2 = label("CENTER", setFont(panel, 45), Color.WHITE,
                    285, 200, 630, 50, null);
            JLabel sokobanBar = label(null, null, null, 270, 50, 672, 288,
                    new ImageIcon(
                            getClass()
                                    .getClassLoader()
                                    .getResource("sokoban/sokobanMainMenu/sokobanBar.png")));
            RotateLabel sokobanIcon = new RotateLabel(
                    new ImageIcon(getClass()
                            .getClassLoader()
                            .getResource("sokoban/sokobanIcon.png")),
                    110,
                    -70);
            JLabel button1 = label(null, null, null, 475, 343, 250, 100,
                    new ImageIcon(getClass()
                            .getClassLoader()
                            .getResource("sokoban/sokobanMainMenu/sokobanButton.png")));
            JLabel button2 = label(null, null, null, 475, 443, 250, 100,
                    new ImageIcon(getClass()
                            .getClassLoader()
                            .getResource("sokoban/sokobanMainMenu/sokobanButton.png")));
            JLabel button3 = label(null, null, null, 475, 543, 250, 100,
                    new ImageIcon(getClass()
                            .getClassLoader()
                            .getResource("sokoban/sokobanMainMenu/sokobanButton.png")));
            JLabel mainBG = label(null, null, null, 0, 0, 1200, 725,
                    new ImageIcon(getClass()
                            .getClassLoader()
                            .getResource("sokoban/sokobanStars.png")));

            add(gameLabel1);
            add(gameLabel2);
            add(sokobanBar);
            add(sokobanIcon);
            add(button1);
            add(button2);
            add(button3);
            add(mainBG);
        }
    }

    public void setActionAndMouseListeners(String panel) {
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (panel == "quizMain") {
                    QuizPlay quizPlay = new QuizPlay(window.getWidth(), window.getHeight(), window);
                    window.add("quizPlay", quizPlay);
                    window.stopAudio("quizMain");
                    window.showCard("quizPlay");
                }

                else if (panel == "pvzMain") {
                    PvzPlay pvzPlay = new PvzPlay(window.getWidth(), window.getHeight(), window);
                    window.add("pvzPlay", pvzPlay);
                    window.stopAudio("pvzMain");
                    window.showCard("pvzPlay");
                }

                else if (panel == "sokobanMain") {
                    SokobanPlay sokobanPlay = new SokobanPlay(window.getWidth(), window.getHeight(), window);
                    window.add("sokobanPlay", sokobanPlay);
                    window.showCard("sokobanPlay");
                }
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
                if (panel == "quizMain") {
                    window.showCard("quizHowToPlay");
                }

                if (panel == "pvzMain") {
                    window.showCard("pvzHowToPlay");
                }

                if (panel == "sokobanMain") {
                    window.showCard("sokobanHowToPlay");
                }
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
                if (panel == "quizMain") {
                    window.stopAudio("quizMain");
                }
                if (panel == "pvzMain") {
                    window.stopAudio("pvzMain");
                }
                if (panel == "sokobanMain") {
                    window.stopAudio("sokobanMain");
                }
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