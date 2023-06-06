import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
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
                        return window.useFont(getClass().getClassLoader().getResourceAsStream("Alyssum-Sans.ttf"),
                                        size);
                if (panel == "pvzHowToPlay" || panel == "pvzHowToPlay1" || panel == "pvzHowToPlay2"
                                || panel == "pvzHowToPlay3"
                                || panel == "pvzHowToPlay4")
                        return window.useFont(getClass().getClassLoader().getResourceAsStream("emulogic.ttf"), size);
                if (panel == "sokobanHowToPlay" || panel == "sokobanHowToPlay1" || panel == "sokobanHowToPlay2"
                                || panel == "sokobanHowToPlay3" || panel == "sokobanHowToPlay4"
                                || panel == "sokobanHowToPlay5"
                                || panel == "sokobanHowToPlay6")
                        return window.useFont(getClass().getClassLoader().getResourceAsStream("Garet-Book.ttf"),
                                        size);
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
                if (panel == "sokobanHowToPlay" || panel == "sokobanHowToPlay1" || panel == "sokobanHowToPlay2"
                                || panel == "sokobanHowToPlay3" || panel == "sokobanHowToPlay4"
                                || panel == "sokobanHowToPlay5") {
                        backButton = button("Main Menu", setFont(panel, 20), Color.WHITE, 50, 600, 120, 100);
                        previousButton = button("Prev", setFont(panel, 20), Color.WHITE, 1000, 600, 60, 100);
                        nextButton = button("Next", setFont(panel, 20), Color.WHITE, 1080, 600, 60, 100);
                }

                // Legend Buttons
                if (panel == "sokobanHowToPlay6") {
                        backButton = button("Back", setFont(panel, 20), Color.WHITE, 50, 600, 120, 100);
                }

                // Disabled Previous Buttons
                if (panel == "quizHowToPlay" || panel == "pvzHowToPlay" || panel == "sokobanHowToPlay") {
                        previousButton.setEnabled(false);
                }

                // Disabled Next Buttons
                if (panel == "quizHowToPlay2" || panel == "pvzHowToPlay4" || panel == "sokobanHowToPlay5") {
                        nextButton.setEnabled(false);
                }

                add(backButton);
                if (panel != "sokobanHowToPlay6") {
                        add(previousButton);
                        add(nextButton);
                }
        }

        // Elements on the screen
        private void setLabels(String panel) {
                // Game 1 How to Play Elements
                if (panel == "quizHowToPlay" || panel == "quizHowToPlay1" || panel == "quizHowToPlay2") {
                        JLabel gameLabel = label("How to Play", setFont(panel, 45), orange,
                                        355, 100, 490, 50, null);
                        add(gameLabel);

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
                                                new ImageIcon(getClass().getClassLoader()
                                                                .getResource("quiz/Peek.png")));
                                JLabel gameLabel9 = label(null, null, null, 320, 370, 50, 50,
                                                new ImageIcon(getClass().getClassLoader()
                                                                .getResource("quiz/Copy.png")));

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
                                        new ImageIcon(getClass().getClassLoader()
                                                        .getResource("quiz/quizLaptop.png")));
                        add(mainBG);
                }

                // Game 2 How to Play Elements
                if (panel == "pvzHowToPlay" || panel == "pvzHowToPlay1" || panel == "pvzHowToPlay2"
                                || panel == "pvzHowToPlay3" || panel == "pvzHowToPlay4") {
                        JLabel gameLabel = label("How To Play", setFont(panel, 45), Color.WHITE,
                                        285, 50, 630, 50, null);
                        add(gameLabel);

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
                                                                new ImageIcon(getClass().getClassLoader()
                                                                                .getResource("pvz/pvzTile.png")));
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

                                // Threats
                                int y = 190;
                                for (int i = 0; i < 4; i++) {
                                        int x = 280;
                                        for (int j = 0; j < 2; j++) {
                                                if (i == 0 && j == 0) {
                                                        JLabel pvzThreat = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(resizeImage(getClass()
                                                                                        .getClassLoader()
                                                                                        .getResourceAsStream(
                                                                                                        "pvz/pvzThreats/pvzThreatTrojan.png"),
                                                                                        70, 70)));

                                                        add(pvzThreat);

                                                        JLabel pvzThreatLabel = label(
                                                                        "<html><center>Trojan Horse</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzThreatLabel);
                                                }

                                                else if (i == 0 && j == 1) {
                                                        JLabel pvzThreat = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzThreats/pvzThreatTrap.png"),
                                                                                                        70, 70)));
                                                        add(pvzThreat);

                                                        JLabel pvzThreatLabel = label(
                                                                        "<html><center>Trap Door</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzThreatLabel);
                                                }

                                                else if (i == 1 && j == 0) {
                                                        JLabel pvzThreat = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzThreats/pvzThreatLogic.png"),
                                                                                                        70, 70)));
                                                        add(pvzThreat);

                                                        JLabel pvzThreatLabel = label(
                                                                        "<html><center>Logic Bomb</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzThreatLabel);
                                                }

                                                else if (i == 1 && j == 1) {
                                                        JLabel pvzThreat = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzThreats/pvzThreatStack.png"),
                                                                                                        70,
                                                                                                        70)));
                                                        add(pvzThreat);

                                                        JLabel pvzThreatLabel = label(
                                                                        "<html><center>Stack and Buffer Overflow</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzThreatLabel);
                                                }

                                                else if (i == 2 && j == 0) {
                                                        JLabel pvzThreat = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzThreats/pvzThreatVirus.png"),
                                                                                                        70, 70)));
                                                        add(pvzThreat);

                                                        JLabel pvzThreatLabel = label(
                                                                        "<html><center>Virus</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzThreatLabel);
                                                }

                                                else if (i == 2 && j == 1) {
                                                        JLabel pvzThreat = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzThreats/pvzThreatWorm.png"),
                                                                                                        70, 70)));
                                                        add(pvzThreat);

                                                        JLabel pvzThreatLabel = label(
                                                                        "<html><center>Worm</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzThreatLabel);
                                                }

                                                else if (i == 3 && j == 0) {
                                                        JLabel pvzThreat = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzThreats/pvzThreatPort.png"),
                                                                                                        70, 70)));
                                                        add(pvzThreat);

                                                        JLabel pvzThreatLabel = label(
                                                                        "<html><center>Port Scanner</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzThreatLabel);
                                                }

                                                else if (i == 3 && j == 1) {
                                                        JLabel pvzThreat = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzThreats/pvzThreatDenial.png"),
                                                                                                        70, 70)));
                                                        add(pvzThreat);

                                                        JLabel pvzThreatLabel = label(
                                                                        "<html><center>Denial of Service</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzThreatLabel);
                                                }
                                                x = x + 330;
                                        }
                                        y = y + 90;
                                }
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

                                // Defenders
                                int y = 190;
                                for (int i = 0; i < 4; i++) {
                                        int x = 280;
                                        for (int j = 0; j < 2; j++) {
                                                if (i == 0 && j == 0) {
                                                        JLabel pvzDefender = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzDefenders/pvzDefenderEncryptor.png"),
                                                                                                        70, 70)));
                                                        add(pvzDefender);

                                                        JLabel pvzDefenderLabel = label(
                                                                        "<html><center>Encryptor</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzDefenderLabel);
                                                }

                                                else if (i == 0 && j == 1) {
                                                        JLabel pvzDefender = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzDefenders/pvzDefenderAuthenticator.png"),
                                                                                                        70, 70)));
                                                        add(pvzDefender);

                                                        JLabel pvzDefenderLabel = label(
                                                                        "<html><center>Authenticator</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzDefenderLabel);
                                                }

                                                else if (i == 1 && j == 0) {
                                                        JLabel pvzDefender = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzDefenders/pvzDefenderAssessor.png"),
                                                                                                        70, 70)));
                                                        add(pvzDefender);

                                                        JLabel pvzDefenderLabel = label(
                                                                        "<html><center>Assessor</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzDefenderLabel);
                                                }

                                                else if (i == 1 && j == 1) {
                                                        JLabel pvzDefender = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzDefenders/pvzDefenderDetector.png"),
                                                                                                        70, 70)));
                                                        add(pvzDefender);

                                                        JLabel pvzDefenderLabel = label(
                                                                        "<html><center>Detector</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzDefenderLabel);
                                                }

                                                else if (i == 2 && j == 0) {
                                                        JLabel pvzDefender = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzDefenders/pvzDefenderAntivirus.png"),
                                                                                                        70, 70)));
                                                        add(pvzDefender);

                                                        JLabel pvzDefenderLabel = label(
                                                                        "<html><center>Antivirus</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzDefenderLabel);
                                                }

                                                else if (i == 2 && j == 1) {
                                                        JLabel pvzDefender = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzDefenders/pvzDefenderTripwire.png"),
                                                                                                        70, 70)));
                                                        add(pvzDefender);

                                                        JLabel pvzDefenderLabel = label(
                                                                        "<html><center>Tripwire</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzDefenderLabel);
                                                }

                                                else if (i == 3 && j == 0) {
                                                        JLabel pvzDefender = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 70, 70,
                                                                        new ImageIcon(
                                                                                        resizeImage(getClass()
                                                                                                        .getClassLoader()
                                                                                                        .getResourceAsStream(
                                                                                                                        "pvz/pvzDefenders/pvzDefenderFirewall.png"),
                                                                                                        70, 70)));
                                                        add(pvzDefender);

                                                        JLabel pvzDefenderLabel = label(
                                                                        "<html><center>Firewall</center></html>",
                                                                        setFont(panel, 10), Color.WHITE, x + 90, y, 160,
                                                                        50, null);
                                                        add(pvzDefenderLabel);
                                                }
                                                x = x + 330;
                                        }
                                        y = y + 90;
                                }
                        }

                        // Page 4/n of Game 2 How To Play
                        if (panel == "pvzHowToPlay3") {
                                JLabel gameLabel2 = label(
                                                "<html><center>Install a Defender anywhere on the board by spending bitcoins. Bitcoins will increase over time so you can install more defenders.</center></html>",
                                                setFont(panel, 15), Color.WHITE, 200, 100, 800, 120, null);
                                add(gameLabel2);

                                JLabel gameLabel3 = label(
                                                "<html><center>Use the Pliers Tool to remove a Defender from the board.</html>",
                                                setFont(panel, 15), Color.WHITE, 200, 360, 800, 120, null);
                                add(gameLabel3);

                                JLabel pvzBitcoin = label(null,
                                                setFont(panel, 15), Color.WHITE, 540, 230, 120, 120,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "pvz/pvzBitcoin.png"),
                                                                120, 120)));

                                JLabel pvzPliers = label(null,
                                                setFont(panel, 15), Color.WHITE, 540, 480, 120, 120,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream("pvz/pvzPliers.png"),
                                                                120, 120)));

                                add(pvzBitcoin);
                                add(pvzPliers);
                        }

                        // Page 5/n of Game 2 How To Play
                        if (panel == "pvzHowToPlay4") {
                                JLabel gameLabel2 = label(
                                                "<html><center>Upgrade your Defenders with Security Policy Upgrade to enhance their Attack DMG.</html>",
                                                setFont(panel, 15), Color.WHITE, 300, 150, 600, 120, null);
                                add(gameLabel2);

                                JLabel pvzUpgrade = label(null,
                                                setFont(panel, 15), Color.WHITE, 525, 330, 150, 150,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream("pvz/pvzUpgrade.png"),
                                                                150, 150)));
                                add(pvzUpgrade);
                        }

                        JLabel mainBG = label(null, null, null, 0, 0, 1200, 725,
                                        new ImageIcon(getClass()
                                                        .getClassLoader()
                                                        .getResource("pvz/pvzGlobe.png")));
                        add(mainBG);
                }

                // Game 3 How to Play Elements
                if (panel == "sokobanHowToPlay" || panel == "sokobanHowToPlay1" || panel == "sokobanHowToPlay2"
                                || panel == "sokobanHowToPlay3" || panel == "sokobanHowToPlay4"
                                || panel == "sokobanHowToPlay5" || panel == "sokobanHowToPlay6") {

                        JLabel gameLabel = label("HOW TO PLAY", setFont(panel, 45), Color.WHITE,
                                        355, 50, 490, 50, null);
                        add(gameLabel);

                        // Page 1/6 of Game 3 How To Play
                        if (panel == "sokobanHowToPlay") {
                                JLabel gameLabel1 = label(
                                                "<html><center>The playing field is a confined map of squares with spots that represent the types of System Calls.</center></html>",
                                                setFont(panel, 25), Color.WHITE, 260, 80, 680, 120, null);
                                add(gameLabel1);

                                JLabel gameLabel2 = label(
                                                "<html><center>Types of System Calls</center></html>",
                                                setFont(panel, 25), Color.WHITE, 500, 450, 680, 120, null);
                                add(gameLabel2);

                                // Sokoban Map
                                int y = 200;
                                for (int i = 0; i < 14; i++) {
                                        int x = 200;
                                        for (int j = 0; j < 13; j++) {
                                                // Border
                                                if ((j == 0 && i > 1) || (i == 13 && j < 12) || (i > 8 && j == 11)
                                                                || (j == 12 && i > 1 && i < 10) || (i == 2 && j > 8)
                                                                || (j == 9 && i < 2)
                                                                || (i == 0 && j < 9 && j > 6) || (j == 7 && i < 2)
                                                                || (j < 8 && j > 5 && i == 2)
                                                                || (j == 6 && i > 2 && i < 5)
                                                                || (i == 4 && j > 3 && j < 6)
                                                                || (j == 4 && i < 4 && i > 0)
                                                                || (i == 1 && j < 4 && j > 1)
                                                                || (j > 0 && j < 3 && i == 2)) {

                                                        JLabel sokobanBrick = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 25, 25,
                                                                        new ImageIcon(resizeImage(getClass()
                                                                                        .getClassLoader()
                                                                                        .getResourceAsStream(
                                                                                                        "sokoban/sokobanMap/sokobanBrick.png"),
                                                                                        25, 25)));
                                                        add(sokobanBrick);
                                                }

                                                // Ground
                                                else if ((j > 0 && j < 12 && i > 3)
                                                                || (i == 3 && j != 5) || (i == 2 && (j == 3 || j == 8))
                                                                || (i == 1 && j == 8)) {
                                                        JLabel sokobanBrick = label(null,
                                                                        setFont(panel, 15), Color.WHITE, x, y, 25, 25,
                                                                        new ImageIcon(resizeImage(getClass()
                                                                                        .getClassLoader()
                                                                                        .getResourceAsStream(
                                                                                                        "sokoban/sokobanMap/sokobanGround.png"),
                                                                                        25, 25)));
                                                        add(sokobanBrick);
                                                }
                                                x = x + 30;
                                        }
                                        y = y + 30;
                                }

                                // System Call Icons
                                JLabel sokobanSystemCall = label(null,
                                                setFont(panel, 15), Color.WHITE, 680, 280, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanProcessControl.png"),
                                                                80, 80)));
                                add(sokobanSystemCall);

                                JLabel sokobanSystemCall1 = label(null,
                                                setFont(panel, 15), Color.WHITE, 800, 280, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanFileManagement.png"),
                                                                80, 80)));
                                add(sokobanSystemCall1);

                                JLabel sokobanSystemCall2 = label(null,
                                                setFont(panel, 15), Color.WHITE, 920, 280, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanDeviceManagement.png"),
                                                                80, 80)));
                                add(sokobanSystemCall2);

                                JLabel sokobanSystemCall3 = label(null,
                                                setFont(panel, 15), Color.WHITE, 680, 390, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanInformationMaintenanceManagement.png"),
                                                                80, 80)));
                                add(sokobanSystemCall3);

                                JLabel sokobanSystemCall4 = label(null,
                                                setFont(panel, 15), Color.WHITE, 800, 390, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanCommunications.png"),
                                                                80, 80)));
                                add(sokobanSystemCall4);

                                JLabel sokobanSystemCall5 = label(null,
                                                setFont(panel, 15), Color.WHITE, 920, 390, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanProtection.png"),
                                                                80, 80)));
                                add(sokobanSystemCall5);
                        }

                        // Page 2/6 of Game 3 How To Play
                        if (panel == "sokobanHowToPlay1") {
                                JLabel gameLabel1 = label(
                                                "<html><center>At the start of the game, the character and example system calls assigned as colored squares are placed in different locations.</center></html>",
                                                setFont(panel, 25), Color.WHITE, 260, 130, 680, 120, null);

                                add(gameLabel1);

                                JLabel gameLabel3 = label(
                                                "<html><center>Character</center></html>",
                                                setFont(panel, 25), Color.WHITE, 285, 480, 200, 120, null);
                                add(gameLabel3);

                                JLabel gameLabel2 = label(
                                                "<html><center>Example System Calls</center></html>",
                                                setFont(panel, 25), Color.WHITE, 635, 480, 250, 120, null);
                                add(gameLabel2);

                                // Android Character
                                JLabel sokobanCharacter = label(null,
                                                setFont(panel, 15), Color.WHITE, 280, 270, 212, 230,
                                                new ImageIcon(getClass()
                                                                .getClassLoader()
                                                                .getResource("sokoban/sokobanIcon.png")));
                                add(sokobanCharacter);

                                // Exampl System Call Icons
                                JLabel sokobanSystemCall = label(null,
                                                setFont(panel, 15), Color.WHITE, 600, 280, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanExampleSystemCall/sokobanCreateProcess().png"),
                                                                80, 80)));
                                add(sokobanSystemCall);

                                JLabel sokobanSystemCall1 = label(null,
                                                setFont(panel, 15), Color.WHITE, 720, 280, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanExampleSystemCall/sokobanCreateFile().png"),
                                                                80, 80)));
                                add(sokobanSystemCall1);

                                JLabel sokobanSystemCall2 = label(null,
                                                setFont(panel, 15), Color.WHITE, 840, 280, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanExampleSystemCall/sokobanReadConsole().png"),
                                                                80, 80)));
                                add(sokobanSystemCall2);

                                JLabel sokobanSystemCall3 = label(null,
                                                setFont(panel, 15), Color.WHITE, 600, 390, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanExampleSystemCall/sokobanSetTimer().png"),
                                                                80, 80)));
                                add(sokobanSystemCall3);

                                JLabel sokobanSystemCall4 = label(null,
                                                setFont(panel, 15), Color.WHITE, 720, 390, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanExampleSystemCall/sokobanCreateFileMapping().png"),
                                                                80, 80)));
                                add(sokobanSystemCall4);

                                JLabel sokobanSystemCall5 = label(null,
                                                setFont(panel, 15), Color.WHITE, 840, 390, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanExampleSystemCall/sokobanSetFileSecurity().png"),
                                                                80, 80)));
                                add(sokobanSystemCall5);
                        }

                        // Page 3/6 of Game 3 How To Play
                        if (panel == "sokobanHowToPlay2") {
                                JLabel gameLabel1 = label(
                                                "<html><center>The character can move a colored square by only pushing it horizontally or vertically to an empty square of a map.</center></html>",
                                                setFont(panel, 25), Color.WHITE, 260, 150, 680, 120, null);

                                add(gameLabel1);

                                JLabel gameLabel2 = label(
                                                "<html><center>Each example system call has its own designated spot. The game is solved when all are placed in their respective spots.</center></html>",
                                                setFont(panel, 25), Color.WHITE, 260, 250, 680, 120, null);

                                add(gameLabel2);

                                // Example System Call + Type of System Call = Solved System Call
                                // Example System Call
                                JLabel sokobanExampleSystemCall = label(null,
                                                setFont(panel, 15), Color.WHITE, 300, 430, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanExampleSystemCall/sokobanCreateProcess().png"),
                                                                80, 80)));
                                add(sokobanExampleSystemCall);

                                // Type of System Call
                                JLabel sokobanSystemCall = label(null,
                                                setFont(panel, 15), Color.WHITE, 550, 430, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanProcessControl.png"),
                                                                80, 80)));
                                add(sokobanSystemCall);

                                // Solved System Call
                                JLabel sokobanSolvedSystemCall = label(null,
                                                setFont(panel, 15), Color.WHITE, 820, 430, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSolvedSystemCall/sokobanProcessControlSolved.png"),
                                                                80, 80)));
                                add(sokobanSolvedSystemCall);

                                // Arrow
                                JLabel sokobanArrow = label(null,
                                                setFont(panel, 15), Color.WHITE, 420, 430, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanHowToPlay/sokobanArrow.png"),
                                                                80, 80)));
                                add(sokobanArrow);

                                // Equal
                                JLabel sokobanEqual = label(null,
                                                setFont(panel, 15), Color.WHITE, 680, 430, 80, 80,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanHowToPlay/sokobanEqual.png"),
                                                                80, 80)));
                                add(sokobanEqual);
                        }

                        // Page 4/6 of Game 3 How To Play
                        if (panel == "sokobanHowToPlay3") {
                                JLabel gameLabel1 = label(
                                                "<html><center>A Power-Up may appear at random. If the player gets it, a multiple-choice question will appear.</center></html>",
                                                setFont(panel, 25), Color.WHITE, 260, 150, 680, 120, null);

                                JLabel gameLabel2 = label(
                                                "<html><center>Get the correct answer, and an example system call is automatically placed to its spot.</center></html>",
                                                setFont(panel, 25), Color.WHITE, 260, 450, 680, 120, null);

                                JLabel gameLabel3 = label(
                                                "Power Up",
                                                setFont(panel, 15), Color.WHITE, 260, 380, 680, 120, null);

                                add(gameLabel1);
                                add(gameLabel2);
                                add(gameLabel3);

                                // Power Up
                                JLabel sokobanPowerUp = label(null,
                                                setFont(panel, 15), Color.WHITE, 525, 270, 150, 150,
                                                new ImageIcon(getClass()
                                                                .getClassLoader()
                                                                .getResource("sokoban/sokobanPowerUp.png")));
                                add(sokobanPowerUp);
                        }

                        // Page 5/6 of Game 3 How To Play
                        if (panel == "sokobanHowToPlay4") {
                                JLabel gameLabel1 = label(
                                                "<html>CONTROLS</html>",
                                                setFont(panel, 30), Color.WHITE, 510, 150, 180, 100, null);

                                add(gameLabel1);

                                JLabel LEFT = label(
                                                "<html>LEFT</html>",
                                                setFont(panel, 30), Color.WHITE, 400, 250, 120, 100, null);

                                JLabel RIGHT = label(
                                                "<html>RIGHT</html>",
                                                setFont(panel, 30), Color.WHITE, 400, 320, 120, 100, null);

                                JLabel UP = label(
                                                "<html>UP</html>",
                                                setFont(panel, 30), Color.WHITE, 400, 390, 120, 100, null);

                                JLabel DOWN = label(
                                                "<html>DOWN</html>",
                                                setFont(panel, 30), Color.WHITE, 400, 460, 120, 100, null);

                                add(LEFT);
                                add(RIGHT);
                                add(UP);
                                add(DOWN);

                                JLabel A = label(
                                                "<html>A</html>",
                                                setFont(panel, 30), Color.WHITE, 700, 250, 120, 100, null);

                                JLabel D = label(
                                                "<html>D</html>",
                                                setFont(panel, 30), Color.WHITE, 700, 320, 120, 100, null);

                                JLabel W = label(
                                                "<html>W</html>",
                                                setFont(panel, 30), Color.WHITE, 700, 390, 120, 100, null);

                                JLabel S = label(
                                                "<html>S</html>",
                                                setFont(panel, 30), Color.WHITE, 700, 460, 120, 100, null);

                                add(A);
                                add(D);
                                add(W);
                                add(S);
                        }

                        // Page 6/6 of Game 3 How To Play
                        if (panel == "sokobanHowToPlay5" || panel == "sokobanHowToPlay6") {
                                JLabel gameLabel1 = label(
                                                "<html><center>LEGEND</center></html>",
                                                setFont(panel, 30), Color.WHITE, 510, 100, 180, 100, null);

                                add(gameLabel1);

                                JLabel gameLabel2 = label(
                                                "<html><center>Types of System Calls</center></html>",
                                                setFont(panel, 20), Color.WHITE, 200, 150, 250, 120, null);
                                add(gameLabel2);

                                JLabel gameLabel3 = label(
                                                "<html><center>Example System Calls (based on Windows OS)</center></html>",
                                                setFont(panel, 20), Color.WHITE, 750, 150, 250, 120, null);
                                add(gameLabel3);

                                JLabel sokobanSystemCall = label(null,
                                                setFont(panel, 15), Color.WHITE, 220, 250, 50, 50,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanProcessControl.png"),
                                                                50, 50)));
                                add(sokobanSystemCall);

                                JLabel sokobanSystemCall1 = label(null,
                                                setFont(panel, 15), Color.WHITE, 220, 310, 50, 50,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanFileManagement.png"),
                                                                50, 50)));
                                add(sokobanSystemCall1);

                                JLabel sokobanSystemCall2 = label(null,
                                                setFont(panel, 15), Color.WHITE, 220, 370, 50, 50,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanDeviceManagement.png"),
                                                                50, 50)));
                                add(sokobanSystemCall2);

                                JLabel sokobanSystemCall3 = label(null,
                                                setFont(panel, 15), Color.WHITE, 220, 430, 50, 50,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanInformationMaintenanceManagement.png"),
                                                                50, 50)));
                                add(sokobanSystemCall3);

                                JLabel sokobanSystemCall4 = label(null,
                                                setFont(panel, 15), Color.WHITE, 220, 490, 50, 50,
                                                new ImageIcon(resizeImage(getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "sokoban/sokobanSystemCall/sokobanCommunications.png"),
                                                                50, 50)));
                                add(sokobanSystemCall4);

                                JLabel sokobanSystemCall5 = label(null,
                                                setFont(panel, 15), Color.WHITE, 220, 550, 50, 50,
                                                new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "sokoban/sokobanSystemCall/sokobanProtection.png"),
                                                                50, 50)));
                                add(sokobanSystemCall5);

                                JLabel sokobanSystemCallLabel = label(
                                                "<html><center>Process Control</center</html>",
                                                setFont(panel, 20), Color.WHITE, 300, 220, 250, 100, null);
                                add(sokobanSystemCallLabel);

                                JLabel sokobanSystemCallLabel1 = label(
                                                "<html><center>File Management</center></html>",
                                                setFont(panel, 20), Color.WHITE, 300, 280, 250, 100, null);
                                add(sokobanSystemCallLabel1);

                                JLabel sokobanSystemCallLabel2 = label(
                                                "<html><center>Device Management</center></html>",
                                                setFont(panel, 20), Color.WHITE, 300, 340, 250, 100, null);
                                add(sokobanSystemCallLabel2);

                                JLabel sokobanSystemCallLabel3 = label(
                                                "<html><center>Information Maintenance Management</center></html>",
                                                setFont(panel, 20), Color.WHITE, 300, 400, 250, 100, null);
                                add(sokobanSystemCallLabel3);

                                JLabel sokobanSystemCallLabel4 = label(
                                                "<html><center>Communications</center></html>",
                                                setFont(panel, 20), Color.WHITE, 300, 460, 250, 100, null);
                                add(sokobanSystemCallLabel4);

                                JLabel sokobanSystemCallLabel5 = label(
                                                "<html><center>Protection</center></html>",
                                                setFont(panel, 20), Color.WHITE, 300, 520, 250, 100, null);
                                add(sokobanSystemCallLabel5);

                                JLabel sokobanExampleSystemCall = label(null,
                                                setFont(panel, 15), Color.WHITE, 700, 250, 50, 50,
                                                new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "sokoban/sokobanExampleSystemCall/sokobanCreateProcess().png"),
                                                                50, 50)));
                                add(sokobanExampleSystemCall);

                                JLabel sokobanExampleSystemCall1 = label(null,
                                                setFont(panel, 15), Color.WHITE, 700, 310, 50, 50,
                                                new ImageIcon(resizeImage(
                                                                getClass().getClassLoader().getResourceAsStream(
                                                                                "sokoban/sokobanExampleSystemCall/sokobanCreateFile().png"),
                                                                50, 50)));
                                add(sokobanExampleSystemCall1);

                                JLabel sokobanExampleSystemCall2 = label(null,
                                                setFont(panel, 15), Color.WHITE, 700, 370, 50, 50,
                                                new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "sokoban/sokobanExampleSystemCall/sokobanReadConsole().png"),
                                                                50, 50)));
                                add(sokobanExampleSystemCall2);

                                JLabel sokobanExampleSystemCall3 = label(null,
                                                setFont(panel, 15), Color.WHITE, 700, 430, 50, 50,
                                                new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "sokoban/sokobanExampleSystemCall/sokobanSetTimer().png"),
                                                                50, 50)));
                                add(sokobanExampleSystemCall3);

                                JLabel sokobanExampleSystemCall4 = label(null,
                                                setFont(panel, 15), Color.WHITE, 700, 490, 50, 50,
                                                new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "sokoban/sokobanExampleSystemCall/sokobanCreateFileMapping().png"),
                                                                50, 50)));
                                add(sokobanExampleSystemCall4);

                                JLabel sokobanExampleSystemCall5 = label(null,
                                                setFont(panel, 15), Color.WHITE, 700, 550, 50, 50,
                                                new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "sokoban/sokobanExampleSystemCall/sokobanSetFileSecurity().png"),
                                                                50, 50)));
                                add(sokobanExampleSystemCall5);

                                JLabel sokobanExampleSystemCallLabel = label(
                                                "<html><center>CreateProcess()</center</html>",
                                                setFont(panel, 20), Color.WHITE, 750, 220, 250, 100, null);
                                add(sokobanExampleSystemCallLabel);

                                JLabel sokobanExampleSystemCallLabel1 = label(
                                                "<html><center>CreateFile()</center></html>",
                                                setFont(panel, 20), Color.WHITE, 750, 280, 250, 100, null);
                                add(sokobanExampleSystemCallLabel1);

                                JLabel sokobanExampleSystemCallLabel2 = label(
                                                "<html><center>ReadConsole()</center></html>",
                                                setFont(panel, 20), Color.WHITE, 750, 340, 250, 100, null);
                                add(sokobanExampleSystemCallLabel2);

                                JLabel sokobanExampleSystemCallLabel3 = label(
                                                "<html><center>SetTimer()</center></html>",
                                                setFont(panel, 20), Color.WHITE, 750, 400, 250, 100, null);
                                add(sokobanExampleSystemCallLabel3);

                                JLabel sokobanExampleSystemCallLabel4 = label(
                                                "<html><center>CreateFileMapping()</center></html>",
                                                setFont(panel, 20), Color.WHITE, 750, 460, 250, 100, null);
                                add(sokobanExampleSystemCallLabel4);

                                JLabel sokobanExampleSystemCallLabel5 = label(
                                                "<html><center>SetFileSecurity()</center></html>",
                                                setFont(panel, 20), Color.WHITE, 750, 520, 250, 100, null);
                                add(sokobanExampleSystemCallLabel5);
                        }

                        JLabel mainBG = label(null, null, null, 0, 0, 1200, 725,
                                        new ImageIcon(getClass()
                                                        .getClassLoader()
                                                        .getResource("sokoban/sokobanStars.png")));
                        add(mainBG);
                }
        }

        private Image resizeImage(InputStream file, int width, int height) {
                BufferedImage img = null;
                try {
                        img = ImageIO.read(file);
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }

        // Action and Mouse Listeners for Buttons
        public void setActionAndMouseListeners(String panel) {
                backButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                // Game 1 Back Button
                                if (panel == "quizHowToPlay" || panel == "quizHowToPlay1"
                                                || panel == "quizHowToPlay2") {
                                        window.showCard("quizMain");
                                }

                                // Game 2 Back Button
                                if (panel == "pvzHowToPlay" || panel == "pvzHowToPlay1" || panel == "pvzHowToPlay2"
                                                || panel == "pvzHowToPlay3" || panel == "pvzHowToPlay4") {
                                        window.showCard("pvzMain");
                                }

                                // Game 3 Back Button
                                if (panel == "sokobanHowToPlay" || panel == "sokobanHowToPlay1"
                                                || panel == "sokobanHowToPlay2"
                                                || panel == "sokobanHowToPlay3" || panel == "sokobanHowToPlay4"
                                                || panel == "sokobanHowToPlay5") {
                                        window.showCard("sokobanMain");
                                }

                                // Legend
                                if (panel == "sokobanHowToPlay6") {
                                        window.showCard("sokobanPlay");
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

                if (panel != "sokobanHowToPlay6") {
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

                                        // Game 3 Previous Button (From Page 6 to Page 5)
                                        if (panel == "sokobanHowToPlay5") {
                                                window.showCard("sokobanHowToPlay4");
                                        }

                                        // Game 3 Previous Button (From Page 5 to Page 4)
                                        if (panel == "sokobanHowToPlay4") {
                                                window.showCard("sokobanHowToPlay3");
                                        }

                                        // Game 3 Previous Button (From Page 4 to Page 3)
                                        if (panel == "sokobanHowToPlay3") {
                                                window.showCard("sokobanHowToPlay2");
                                        }

                                        // Game 3 Previous Button (From Page 3 to Page 2)
                                        if (panel == "sokobanHowToPlay2") {
                                                window.showCard("sokobanHowToPlay1");
                                        }

                                        // Game 3 Previous Button (From Page 2 to Page 1)
                                        if (panel == "sokobanHowToPlay1") {
                                                window.showCard("sokobanHowToPlay");
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

                                        // Game 3 Next Button (From Page 1 to Page 2)
                                        if (panel == "sokobanHowToPlay") {
                                                window.showCard("sokobanHowToPlay1");
                                        }

                                        // Game 3 Next Button (From Page 2 to Page 3)
                                        if (panel == "sokobanHowToPlay1") {
                                                window.showCard("sokobanHowToPlay2");
                                        }

                                        // Game 3 Next Button (From Page 3 to Page 4)
                                        if (panel == "sokobanHowToPlay2") {
                                                window.showCard("sokobanHowToPlay3");
                                        }

                                        // Game 3 Next Button (From Page 4 to Page 5)
                                        if (panel == "sokobanHowToPlay3") {
                                                window.showCard("sokobanHowToPlay4");
                                        }
                                        // Game 3 Next Button (From Page 5 to Page 6)
                                        if (panel == "sokobanHowToPlay4") {
                                                window.showCard("sokobanHowToPlay5");
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
}