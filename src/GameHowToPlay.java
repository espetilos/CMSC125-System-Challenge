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

public class GameHowToPlay extends JPanel {

    private Window window;
    private JButton backButton;
    private JButton previousButton;
    private JButton nextButton;

    private Color orange = new Color(226, 161, 101);

    public GameHowToPlay(int width, int height, String panel, Window w) {
        setSize(width, height);
        setLayout(null);
        setOpaque(true);

        window = w;

        setBackground(Color.BLACK);
        setButtons(panel);
        setLabels(panel);
        setActionAndMouseListeners(panel);
    }

    // Font Object
    private Font setFont(String panel, int size) {
        if (panel == "quizHowToPlay" || panel == "quizHowToPlay1" || panel == "quizHowToPlay2")
            return window.useFont(System.getProperty("user.dir") + "/src/resources/Alyssum-Sans.ttf", size);
        if (panel == "pvzHowToPlay" || panel == "pvzHowToPlay1" || panel == "pvzHowToPlay2" || panel == "pvzHowToPlay3"
                || panel == "pvzHowToPlay4")
            return window.useFont(System.getProperty("user.dir") + "/src/resources/Emulogic.ttf", size);
        if (panel == "sokobanHowToPlay")
            return window.useFont(System.getProperty("user.dir") + "/src/resources/Garet-Book.ttf", size);
        return null;
    }

    // Button Object
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

    // Label Object
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

    // Buttons on the screen
    private void setButtons(String panel) {
        // Game 1 How to Play Buttons
        if (panel == "quizHowToPlay" || panel == "quizHowToPlay1" || panel == "quizHowToPlay2") {
            backButton = button("Main Menu", setFont(panel, 20), Color.WHITE, 200, 470, 120, 100);
            previousButton = button("Prev", setFont(panel, 20), Color.WHITE, 850, 470, 60, 100);
            nextButton = button("Next", setFont(panel, 20), Color.WHITE, 940, 470, 60, 100);
        }

        // Game 2 How to Play Buttons
        if (panel == "pvzHowToPlay" || panel == "pvzHowToPlay1" || panel == "pvzHowToPlay2"
                || panel == "pvzHowToPlay3" || panel == "pvzHowToPlay4") {
            backButton = button("Main Menu", setFont(panel, 10), Color.WHITE, 200, 600, 120, 100);
            previousButton = button("Prev", setFont(panel, 10), Color.WHITE, 850, 600, 60, 100);
            nextButton = button("Next", setFont(panel, 10), Color.WHITE, 940, 600, 60, 100);
        }

        // Game 3 How to Play Buttons
        if (panel == "sokobanHowToPlay") {
        }

        // Disabled Previous Buttons
        if (panel == "quizHowToPlay" || panel == "pvzHowToPlay") {
            previousButton.setEnabled(false);
        }

        // Disabled Next Buttons
        if (panel == "quizHowToPlay2" || panel == "pvzHowToPlay4") {
            nextButton.setEnabled(false);
        }

        add(backButton);
        add(previousButton);
        add(nextButton);
    }

    // Elements on the screen
    private void setLabels(String panel) {
        // Game 1 How to Play Elements
        if (panel == "quizHowToPlay" || panel == "quizHowToPlay1" || panel == "quizHowToPlay2") {
            JLabel gameLabel1 = label("How to Play", setFont(panel, 45), orange,
                    355, 100, 490, 50, null);
            add(gameLabel1);

            // Page 1/3 of Game 1 How To Play
            if (panel == "quizHowToPlay") {
                JLabel gameLabel2 = label(
                        "<html><center>Choose an Operating System (O.S.) related category and answer ten (10) multiple- <br> choice questions in a row to get a chance <br> to answer</center> </html>",
                        setFont(panel, 25), Color.WHITE, 260, 200, 680, 120, null);

                JLabel gameLabel3 = label("The Million Dollar Question!", setFont(panel, 35), orange,
                        290, 370, 620, 50, null);

                add(gameLabel2);
                add(gameLabel3);
            }

            // Page 2/3 of Game 1 How To Play
            if (panel == "quizHowToPlay1") {
                JLabel gameLabel2 = label(
                        "<html><center>Each question is equivalent to  <font color = 'rgb(226, 161, 101)'>one (1) point</font>. If you get a chance to answer the million dollar question, you can <font color = 'rgb(226, 161, 101)'>wager your points</font> and get them back <font color = 'rgb(226, 161, 101)'>triple</font> if you get the <font color = 'rgb(126, 217, 87)'>correct</font> answer or lose points if you get the <font color = 'rgb(255, 87, 87)'>wrong<font> answer.</center></html>",
                        setFont(panel, 25), Color.WHITE, 260, 200, 680, 170, null);

                add(gameLabel2);
            }

            // Page 3/3 of Game 1 How To Play
            if (panel == "quizHowToPlay2") {
                JLabel gameLabel2 = label(
                        "<html><center><font color = 'rgb(226, 161, 101)'>Cheats</font> are available that you can each use once:</center></html>",
                        setFont(panel, 25), Color.WHITE, 260, 200, 680, 50, null);

                JLabel gameLabel3 = label("Peek", setFont(panel, 25), orange,
                        400, 270, 90, 50, null);

                JLabel gameLabel4 = label("Copy", setFont(panel, 25), orange,
                        405, 350, 90, 50, null);

                JLabel gameLabel5 = label(
                        "<html><p>Computer will highlight an answer on the screen and you are given the opportunity to either choose the same answer or not.</p></html>",
                        setFont(panel, 12), Color.WHITE,
                        412, 300, 510, 50, null);

                JLabel gameLabel6 = label(
                        "<html><p>You lock in whatever answer the computer will highlight without knowing what its answer will be.</p></html>",
                        setFont(panel, 12), Color.WHITE,
                        412, 380, 510, 50, null);

                JLabel gameLabel7 = label(
                        "Note: Cheats are not allowed during the million dollar question.",
                        setFont(panel, 12), Color.WHITE,
                        350, 430, 500, 50, null);
                JLabel gameLabel8 = label(null, null, null, 320, 290, 50, 50,
                        new ImageIcon(System.getProperty("user.dir") + "/src/resources/Peek.png"));
                JLabel gameLabel9 = label(null, null, null, 320, 370, 50, 50,
                        new ImageIcon(System.getProperty("user.dir") + "/src/resources/Copy.png"));

                add(gameLabel2);
                add(gameLabel3);
                add(gameLabel4);
                add(gameLabel5);
                add(gameLabel6);
                add(gameLabel7);
                add(gameLabel8);
                add(gameLabel9);
            }

            JLabel mainBG = label(null, null, null, 0, 0, 1200, 725,
                    new ImageIcon(System.getProperty("user.dir") + "/src/resources/quizLaptop.png"));
            add(mainBG);
        }

        // Game 2 How to Play Elements
        if (panel == "pvzHowToPlay" || panel == "pvzHowToPlay1" || panel == "pvzHowToPlay2"
                || panel == "pvzHowToPlay3" || panel == "pvzHowToPlay4") {
            JLabel gameLabel1 = label("How To Play", setFont(panel, 45), Color.WHITE,
                    285, 50, 630, 50, null);
            add(gameLabel1);

            // Page 1/n of Game 2 How To Play
            if (panel == "pvzHowToPlay") {
                JLabel gameLabel2 = label(
                        "<html><center>The system has a 5 x 8 board for its own protection.</center></html>",
                        setFont(panel, 15), Color.WHITE, 200, 100, 800, 120, null);

                int y = 200;
                for (int i = 0; i < 5; i++) {
                    int x = 280;
                    for (int j = 0; j < 8; j++) {
                        JLabel pvzTile = label(null,
                                setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                new ImageIcon(System.getProperty("user.dir") + "/src/resources/pvzTile.png"));
                        add(pvzTile);
                        x = x + 80;
                    }
                    y = y + 80;
                }

                add(gameLabel2);
            }

            // Page 2/n of Game 2 How To Play
            if (panel == "pvzHowToPlay1") {
                JLabel gameLabel2 = label(
                        "<html><center><font color = 'rgb(255, 87, 87)'>Threats</font> can access your system protection board:</center></html>",
                        setFont(panel, 15), Color.WHITE, 200, 100, 800, 120, null);
                add(gameLabel2);

                JLabel gameLabel3 = label(
                        "<html><center>If three (3) threats reach your system, you lose.</center></html>",
                        setFont(panel, 15), Color.WHITE, 200, 500, 800, 120, null);
                add(gameLabel3);
            }

            // Page 3/n of Game 2 How To Play
            if (panel == "pvzHowToPlay2") {
                JLabel gameLabel2 = label(
                        "<html><center>Secure your system by installing software programs called <font color = 'rgb(159, 211, 199)'>Defenders</font>:</center></html>",
                        setFont(panel, 15), Color.WHITE, 200, 100, 800, 120, null);
                add(gameLabel2);

                JLabel gameLabel3 = label(
                        "<html><center>If you manage to secure your system after two (2) waves of threats, you win.</center></html>",
                        setFont(panel, 15), Color.WHITE, 200, 500, 800, 120, null);
                add(gameLabel3);
            }

            // Page 4/n of Game 2 How To Play
            if (panel == "pvzHowToPlay3") {
                JLabel gameLabel2 = label(
                        "<html><center>Install a Defender anywhere on the board by spending bitcoins. Bitcoins will increase over time so you can install more defenders.</center></html>",
                        setFont(panel, 15), Color.WHITE, 200, 100, 800, 120, null);
                add(gameLabel2);

                JLabel gameLabel3 = label(
                        "<html><center>Use the Pliers Tool to remove a Defender from the board.</html>",
                        setFont(panel, 15), Color.WHITE, 200, 400, 800, 120, null);
                add(gameLabel3);
            }

            // Page 5/n of Game 2 How To Play
            if (panel == "pvzHowToPlay4") {
                JLabel gameLabel2 = label(
                        "<html><center>Upgrade your Defenders with Security Policy Upgrade to enhance their Attack DMG.</html>",
                        setFont(panel, 15), Color.WHITE, 200, 100, 800, 120, null);
                add(gameLabel2);
            }

            JLabel mainBG = label(null, null, null, 0, 0, 1200, 725,
                    new ImageIcon(System.getProperty("user.dir") + "/src/resources/pvzGlobe.png"));
            add(mainBG);
        }

        // Game 3 How to Play Elements
        if (panel == "sokobanMain")

        {
        }
    }

    // Action and Mouse Listeners for Buttons
    public void setActionAndMouseListeners(String panel) {
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Game 1 Back Button
                if (panel == "quizHowToPlay" || panel == "quizHowToPlay1" || panel == "quizHowToPlay2") {
                    window.showCard("quizMain");
                }

                // Game 2 Back Button
                if (panel == "pvzHowToPlay" || panel == "pvzHowToPlay1" || panel == "pvzHowToPlay2"
                        || panel == "pvzHowToPlay3" || panel == "pvzHowToPlay4") {
                    window.showCard("pvzMain");
                }

                // Game 3 Back Button
                if (panel == "sokobanHowToPlay") {
                    window.showCard("sokobanMain");
                }
            }
        });

        backButton.addMouseListener(new MouseListener() {

            public void mouseEntered(MouseEvent e) {
                backButton.setForeground(orange);
            }

            public void mouseExited(MouseEvent e) {
                backButton.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                backButton.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
                backButton.setForeground(orange);
            }

            public void mousePressed(MouseEvent e) {
            }
        });

        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Game 1 Previous Button (From Page 2 to Page 1)
                if (panel == "quizHowToPlay1") {
                    window.showCard("quizHowToPlay");
                }

                // Game 1 Previous Button (From Page 3 to Page 2)
                if (panel == "quizHowToPlay2") {
                    window.showCard("quizHowToPlay1");
                }

                // Game 2 Previous Button (From Page 2 to Page 1)
                if (panel == "pvzHowToPlay1") {
                    window.showCard("pvzHowToPlay");
                }

                // Game 2 Previous Button (From Page 3 to Page 2)
                if (panel == "pvzHowToPlay2") {
                    window.showCard("pvzHowToPlay1");
                }

                // Game 2 Previous Button (From Page 4 to Page 3)
                if (panel == "pvzHowToPlay3") {
                    window.showCard("pvzHowToPlay2");
                }

                // Game 2 Previous Button (From Page 5 to Page 4)
                if (panel == "pvzHowToPlay4") {
                    window.showCard("pvzHowToPlay3");
                }
            }
        });

        previousButton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                previousButton.setForeground(orange);
            }

            public void mouseExited(MouseEvent e) {
                previousButton.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                previousButton.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
                previousButton.setForeground(orange);
            }

            public void mousePressed(MouseEvent e) {
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Game 1 Next Button (From Page 1 to Page 2)
                if (panel == "quizHowToPlay") {
                    window.showCard("quizHowToPlay1");
                }

                // Game 1 Next Button (From Page 2 to Page 3)
                if (panel == "quizHowToPlay1") {
                    window.showCard("quizHowToPlay2");
                }

                // Game 2 Next Button (From Page 1 to Page 2)
                if (panel == "pvzHowToPlay") {
                    window.showCard("pvzHowToPlay1");
                }

                // Game 2 Next Button (From Page 2 to Page 3)
                if (panel == "pvzHowToPlay1") {
                    window.showCard("pvzHowToPlay2");
                }

                // Game 2 Next Button (From Page 3 to Page 4)
                if (panel == "pvzHowToPlay2") {
                    window.showCard("pvzHowToPlay3");
                }

                // Game 2 Next Button (From Page 4 to Page 5)
                if (panel == "pvzHowToPlay3") {
                    window.showCard("pvzHowToPlay4");
                }
            }
        });

        nextButton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                nextButton.setForeground(orange);
            }

            public void mouseExited(MouseEvent e) {
                nextButton.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                nextButton.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
                nextButton.setForeground(orange);
            }

            public void mousePressed(MouseEvent e) {
            }
        });

    }
}