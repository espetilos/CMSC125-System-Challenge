import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PvzPlay extends JPanel {

        private static int length = 80;
        private int bitCoinNum = 50, ctr = 0;
        private int[] indexYPos = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        private int[] threatYPos = { 185, 285, 385, 485, 585 };
        private boolean planting = false, highlighted = false, removing = false;
        /*
         * "planting" is true when an defender is selected
         * "removing" is true when the pliers are selected
         * "highlighed" is true when the cursor is hovered on a tile while "planting" or
         * "removing" is true
         */
        private Color orange = new Color(226, 161, 101);
        private Window window;
        private JButton[][] tiles = new JButton[5][8];
        private JButton[] defenders = new JButton[7];
        private JLabel[] defValue = new JLabel[7];
        private JLabel[] threats = new JLabel[20];
        private JButton upgrade;
        private JLabel bitCoin;
        private JLabel bitCoinAmount;
        private JButton pliers;
        private JButton exit;
        private JLabel defIcon;
        private JLabel bullet;
        // defIcon is the determinant of the icon over which defender is selected

        private SoundClip soundmain = new SoundClip("pvz/pvzAudio/pvzPlayAudio.wav");

        public PvzPlay(int width, int height, Window w) {
                setSize(width, height);
                setLayout(null);
                setOpaque(true);

                window = w;

                setThreats();
                setDefenders();
                setDefendersValues();
                setExtras();
                setTiles();
                setPvzBackground(); // Setting Panel Background

                Timer bitCoinTimer = new Timer();
                TimerTask bitCoinTask = new BitCoinIterator();
                bitCoinTimer.schedule(bitCoinTask, 2000, 2000); // Iteration of BitCoins over time

                Timer waveTimer = new Timer();
                TimerTask waveTask = new WaveGenerator();
                waveTimer.schedule(waveTask, 6000, 5000); // Iteration of threats over time
        }

        private void setExtras() {
                soundmain.start();
                bullet = new JLabel(new ImageIcon(resizeImage(getClass()
                                .getClassLoader()
                                .getResourceAsStream("pvz/pvzBullet.png"), 20, 20)));
                add(bullet);

                bitCoin = new JLabel(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzBitcoin.png"),
                                length, length)));

                pliers = new JButton(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzPliers.png"),
                                length, length)));

                bitCoinAmount = new JLabel();
                bitCoinAmount.setText(Integer.toString(bitCoinNum));
                bitCoinAmount.setHorizontalAlignment(SwingConstants.CENTER);
                bitCoinAmount.setFont(font(40));
                bitCoinAmount.setBounds(20, 110, 170, length);
                bitCoinAmount.setForeground(Color.WHITE);
                bitCoinAmount.setBorder(BorderFactory.createEmptyBorder());
                add(bitCoinAmount);

                bitCoin.setBounds(65, 35, length, length);
                bitCoin.setBorder(BorderFactory.createEmptyBorder());
                add(bitCoin);

                pliers.setBounds(915, 50, length, length);
                pliers.setContentAreaFilled(false);
                pliers.setFocusPainted(false);
                pliers.setBorder(BorderFactory.createEmptyBorder());
                setPliersMouseListeners(pliers);
                add(pliers);

                exit = new JButton("Exit");
                exit.setFont(font(20));
                exit.setBounds(1025, 50, 100, length);
                exit.setForeground(Color.WHITE);
                exit.setContentAreaFilled(false);
                exit.setBorder(BorderFactory.createEmptyBorder());
                exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                soundmain.stop();
                                window.startAudio("pvzMain");
                                window.showCard("pvzMain");
                        }
                });
                exit.addMouseListener(new MouseListener() {
                        public void mouseEntered(MouseEvent e) {
                                if (exit.getForeground() == Color.WHITE)
                                        exit.setForeground(orange);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (exit.getForeground() == orange)
                                        exit.setForeground(Color.WHITE);
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseClicked(MouseEvent e) {
                        }

                        public void mousePressed(MouseEvent e) {
                        }
                });
                add(exit);

                defIcon = new JLabel();
                defIcon.setText("0");
        }

        // Setting Game 2 Background
        private void setPvzBackground() {
                setBackground(Color.BLACK);
                JLabel pvzGlobe = new JLabel();
                pvzGlobe.setIcon(new ImageIcon(getClass()
                                .getClassLoader()
                                .getResource("pvz/pvzGlobe.png")));
                pvzGlobe.setBounds(0, 0, 1200, 725);
                add(pvzGlobe);
        }

        private void setThreats() {
                Random randThreatNum = new Random();
                for (int i = 0; i < 20; i++) {
                        threats[i] = setThreatIcon(threats[i], randThreatNum.nextInt(8));
                        threats[i].setBorder(BorderFactory.createEmptyBorder());
                        threats[i].setBounds(1210, 0, 80, 80);
                        setComponentZOrder(threats[i], 0);
                        add(threats[i]);
                        threats[i].setVisible(false);
                }
        }

        private void setDefenders() {
                defenders[0] = new JButton(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzDefenders/pvzDefenderEncryptor.png"),
                                length, length)));
                defenders[1] = new JButton(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzDefenders/pvzDefenderAuthenticator.png"),
                                length, length)));
                defenders[2] = new JButton(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzDefenders/pvzDefenderAssessor.png"),
                                length, length)));
                defenders[3] = new JButton(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzDefenders/pvzDefenderDetector.png"),
                                length, length)));
                defenders[4] = new JButton(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzDefenders/pvzDefenderAntivirus.png"),
                                length, length)));
                defenders[5] = new JButton(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzDefenders/pvzDefenderTripwire.png"),
                                length, length)));
                defenders[6] = new JButton(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzDefenders/pvzDefenderFirewall.png"),
                                length, length)));

                int y = 35;
                int x = 100;
                for (int i = 0; i < 7; i++) {
                        defenders[i].setBounds(x + (100 * (i + 1)), y, length, length);
                        defenders[i].setContentAreaFilled(false);
                        defenders[i].setFocusPainted(false);
                        defenders[i].setBorder(BorderFactory.createEmptyBorder());
                        add(defenders[i]);
                }

                defenders[0].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (bitCoinNum < 50)
                                        return;
                                defIcon.setText("1");
                                setDefendersActionListenerExtension(defenders[0]);
                        }
                });
                defenders[1].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (bitCoinNum < 100)
                                        return;
                                defIcon.setText("2");
                                setDefendersActionListenerExtension(defenders[1]);
                        }
                });
                defenders[2].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (bitCoinNum < 100)
                                        return;
                                defIcon.setText("3");
                                setDefendersActionListenerExtension(defenders[2]);
                        }
                });
                defenders[3].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (bitCoinNum < 100)
                                        return;
                                defIcon.setText("4");
                                setDefendersActionListenerExtension(defenders[3]);
                        }
                });
                defenders[4].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (bitCoinNum < 125)
                                        return;
                                defIcon.setText("5");
                                setDefendersActionListenerExtension(defenders[4]);
                        }
                });
                defenders[5].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (bitCoinNum < 125)
                                        return;
                                defIcon.setText("6");
                                setDefendersActionListenerExtension(defenders[5]);
                        }
                });
                defenders[6].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (bitCoinNum < 150)
                                        return;
                                defIcon.setText("7");
                                setDefendersActionListenerExtension(defenders[6]);
                        }
                });
        }

        private void setDefendersValues() {
                defValue[0] = new JLabel();
                defValue[0].setText("50");
                defValue[1] = new JLabel();
                defValue[1].setText("100");
                defValue[2] = new JLabel();
                defValue[2].setText("100");
                defValue[3] = new JLabel();
                defValue[3].setText("100");
                defValue[4] = new JLabel();
                defValue[4].setText("125");
                defValue[5] = new JLabel();
                defValue[5].setText("125");
                defValue[6] = new JLabel();
                defValue[6].setText("150");

                int y = 125;
                int x = 100;
                for (int i = 0; i < 7; i++) {
                        defValue[i].setBounds(x + (100 * (i + 1)), y, length, 30);
                        defValue[i].setFont(font(20));
                        defValue[i].setHorizontalAlignment(SwingConstants.CENTER);
                        defValue[i].setForeground(Color.GRAY);
                        defValue[i].setBorder(BorderFactory.createEmptyBorder());
                        add(defValue[i]);
                }
        }

        private void setTiles() {
                int y = 180;
                for (int i = 0; i < 5; i++) {
                        int x = 200;
                        for (int j = 0; j < 8; j++) {
                                tiles[i][j] = new JButton();
                                // tiles[i][j] = new JButton(new ImageIcon(resizeImage(
                                // getClass()
                                // .getClassLoader()
                                // .getResourceAsStream("pvz/pvzTile.png"),
                                // 90, 90)));
                                tiles[i][j].setBounds(x, y, 90, 90);
                                tiles[i][j].setBackground(Color.GRAY);
                                tiles[i][j].setBorder(BorderFactory.createEmptyBorder());
                                setTilesMouseListeners(tiles[i][j]);
                                setTilesActionListenerExtension(tiles[i][j]);
                                add(tiles[i][j]);
                                x += 100;
                        }
                        y += 100;
                }
        }

        // Resizing Images for JLabels
        private Image resizeImage(InputStream file, int width, int height) {
                BufferedImage img = null;
                try {
                        img = ImageIO.read(file);
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }

        private Font font(int size) {
                return window.useFont(getClass().getClassLoader().getResourceAsStream("emulogic.ttf"), size);
        }

        private void setDefendersActionListenerExtension(JButton button) {
                // Puts a green border on a selected defender
                if (planting == false) {
                        planting = true;
                } else if (planting == true) {
                        for (int i = 0; i < 7; i++) {
                                defenders[i].setBorder(BorderFactory.createEmptyBorder());
                        }
                }
                button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }

        private void setPliersMouseListeners(JButton button) {
                button.addMouseListener(new MouseListener() {
                        public void mouseEntered(MouseEvent e) {
                                if (highlighted == false && removing == false) {
                                        button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                                        highlighted = true;
                                }
                        }

                        public void mouseExited(MouseEvent e) {
                                if (highlighted == true && removing == false) {
                                        button.setBorder(BorderFactory.createEmptyBorder());
                                        highlighted = false;
                                }
                        }

                        public void mouseClicked(MouseEvent e) {
                                if (removing == false) {
                                        button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                                        removing = true;
                                } else if (removing == true) {
                                        button.setBorder(BorderFactory.createEmptyBorder());
                                        removing = false;
                                }
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }
                });
        }

        private void setTilesMouseListeners(JButton button) {
                // Puts a green border on tiles where cursor is hovered when "planting" is true
                button.addMouseListener(new MouseListener() {
                        public void mouseEntered(MouseEvent e) {
                                if (highlighted == false && (planting == true || removing == true)) {
                                        button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                                        highlighted = true;
                                }
                        }

                        public void mouseExited(MouseEvent e) {
                                if (highlighted == true && (planting == true || removing == true)) {
                                        button.setBorder(BorderFactory.createEmptyBorder());
                                        highlighted = false;
                                }
                        }

                        public void mouseClicked(MouseEvent e) {
                                button.setBackground(Color.GRAY);

                                if (highlighted == true) {
                                        button.setBorder(BorderFactory.createEmptyBorder());
                                        highlighted = false;
                                }

                                if (removing == true) {
                                        button.setIcon(null);
                                        pliers.setBorder(BorderFactory.createEmptyBorder());
                                        removing = false;
                                }
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                                button.setBorder(BorderFactory.createEmptyBorder());
                        }
                });
        }

        private void setTilesActionListenerExtension(JButton button) {
                // Sets the icon of a tile to the icon of the selected defender
                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String icon = defIcon.getText();

                                if (button.getIcon() != null) {
                                        return;
                                }

                                switch (icon) {
                                        case "1":
                                                button.setIcon(new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "pvz/pvzDefenders/pvzDefenderEncryptor.png"),
                                                                length, length)));
                                                bitCoinNum -= 50;
                                                availableDef();
                                                break;
                                        case "2":
                                                button.setIcon(new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "pvz/pvzDefenders/pvzDefenderAuthenticator.png"),
                                                                length, length)));
                                                bitCoinNum -= 100;
                                                availableDef();
                                                break;
                                        case "3":
                                                button.setIcon(new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "pvz/pvzDefenders/pvzDefenderAssessor.png"),
                                                                length, length)));
                                                bitCoinNum -= 100;
                                                availableDef();
                                                break;
                                        case "4":
                                                button.setIcon(new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "pvz/pvzDefenders/pvzDefenderDetector.png"),
                                                                length, length)));
                                                bitCoinNum -= 100;
                                                availableDef();
                                                break;
                                        case "5":
                                                button.setIcon(new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "pvz/pvzDefenders/pvzDefenderAntivirus.png"),
                                                                length, length)));
                                                bitCoinNum -= 125;
                                                availableDef();
                                                break;
                                        case "6":
                                                button.setIcon(new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "pvz/pvzDefenders/pvzDefenderTripwire.png"),
                                                                length, length)));
                                                bitCoinNum -= 125;
                                                availableDef();
                                                break;
                                        case "7":
                                                button.setIcon(new ImageIcon(resizeImage(
                                                                getClass()
                                                                                .getClassLoader()
                                                                                .getResourceAsStream(
                                                                                                "pvz/pvzDefenders/pvzDefenderFirewall.png"),
                                                                length, length)));
                                                bitCoinNum -= 150;
                                                availableDef();
                                                break;
                                        default:
                                                return;
                                }
                                button.setBackground(Color.GRAY);
                                defIcon.setText("0");
                                planting = false;
                                for (int i = 0; i < 7; i++) {
                                        defenders[i].setBorder(BorderFactory.createEmptyBorder());
                                }
                        }
                });
        }

        private void availableDef() {
                // Refreshes the bitCoinAmount to its actual value
                // Refreshes the indicators if certain defenders are already available for
                // planting
                bitCoinAmount.setText(Integer.toString(bitCoinNum));

                for (int i = 0; i < 7; i++) {
                        defValue[i].setForeground(Color.GRAY);
                }

                if (bitCoinNum >= 50) {
                        defValue[0].setForeground(Color.WHITE);
                }

                if (bitCoinNum >= 100) {
                        for (int i = 1; i < 4; i++) {
                                defValue[i].setForeground(Color.WHITE);
                        }
                }

                if (bitCoinNum >= 125) {
                        for (int i = 4; i < 6; i++) {
                                defValue[i].setForeground(Color.WHITE);
                        }
                }

                if (bitCoinNum >= 150) {
                        defValue[6].setForeground(Color.WHITE);
                }
        }

        class BitCoinIterator extends TimerTask {
                // Timer task for automatic iteration of bitcoins over time
                @Override
                public void run() {
                        bitCoinNum += 25;
                        availableDef();
                }
        }

        class WaveGenerator extends TimerTask {
                // Timer task for automatic iteration of threat waves over time
                @Override
                public void run() {
                        if (ctr >= 20) {
                                ctr = 0;
                        }

                        threats[ctr].setVisible(true);
                        Random randIndex = new Random();
                        indexYPos[ctr] = randIndex.nextInt(5);
                        Timer threatTimer = new Timer();
                        TimerTask threatTask = new ThreatIterator();
                        threatTimer.schedule(threatTask, 0, 50);
                        ctr++;
                }
        }

        public JLabel setThreatIcon(JLabel threat, int threatType) {
                switch (threatType) {
                        case 0:
                                threat = new JLabel(new ImageIcon(resizeImage(
                                                getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "pvz/pvzThreats/pvzThreatTrojan.png"),
                                                length, length)));
                                break;
                        case 1:
                                threat = new JLabel(new ImageIcon(resizeImage(
                                                getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "pvz/pvzThreats/pvzThreatTrap.png"),
                                                length, length)));
                                break;
                        case 2:
                                threat = new JLabel(new ImageIcon(resizeImage(
                                                getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "pvz/pvzThreats/pvzThreatLogic.png"),
                                                length, length)));
                                break;
                        case 3:
                                threat = new JLabel(new ImageIcon(resizeImage(
                                                getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "pvz/pvzThreats/pvzThreatStack.png"),
                                                length, length)));
                                break;
                        case 4:
                                threat = new JLabel(new ImageIcon(resizeImage(
                                                getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "pvz/pvzThreats/pvzThreatVirus.png"),
                                                length, length)));
                                break;
                        case 5:
                                threat = new JLabel(new ImageIcon(resizeImage(
                                                getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "pvz/pvzThreats/pvzThreatWorm.png"),
                                                length, length)));
                                break;
                        case 6:
                                threat = new JLabel(new ImageIcon(resizeImage(
                                                getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "pvz/pvzThreats/pvzThreatPort.png"),
                                                length, length)));
                                break;
                        case 7:
                                threat = new JLabel(new ImageIcon(resizeImage(
                                                getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream(
                                                                                "pvz/pvzThreats/pvzThreatDenial.png"),
                                                length, length)));
                                break;
                }

                // threat.setVisible(true);
                return threat;
        }

        class ThreatIterator extends TimerTask {
                // Timer task for automatic iteration of threats over time
                int thisIndex = indexYPos[ctr];
                int threatXPos = 1210;
                int thisCtr = ctr;

                @Override
                public void run() {
                        threats[thisCtr].setLocation(threatXPos, threatYPos[thisIndex]);
                        threats[thisCtr].setVisible(true);
                        // setComponentZOrder(threats[thisCtr], 0);
                        threatXPos -= 3;

                        if (threatXPos == -100) {
                                cancel();
                        }
                }

        }
}
