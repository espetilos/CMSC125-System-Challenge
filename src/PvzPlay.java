import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PvzPlay extends JPanel {

        private static int length = 80;
        private int bitCoinNum = 999;
        private Window window;
        private JButton[][] panels = new JButton[5][8];
        private JButton[] defenders = new JButton[7];
        private JLabel[] threats = new JLabel[8];
        private JButton upgrade;
        private JLabel bitCoin;
        private JLabel bitCoinAmount;
        private JButton pliers;
        private JButton exit;

        public PvzPlay(int width, int height, Window w) {
                setSize(width, height);
                setLayout(null);
                setOpaque(true);

                window = w;

                setThreats();
                setDefenders();
                setExtras();
                setPanels();
                setPvzBackground(); // Setting Panel Background
        }

        private void setPanels() {
                int y = 180;
                for (int i = 0; i < 5; i++) {
                        int x = 200;
                        for (int j = 0; j < 8; j++) {
                                panels[i][j] = new JButton(new ImageIcon(resizeImage(
                                                getClass()
                                                                .getClassLoader()
                                                                .getResourceAsStream("pvz/pvzTile.png"),
                                                90, 90)));
                                panels[i][j].setBounds(x, y, 90, 90);
                                panels[i][j].setBorder(BorderFactory.createEmptyBorder());
                                add(panels[i][j]);
                                x += 100;
                        }
                        y += 100;
                }
        }

        private void setExtras() {
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
                // bitCoinAmount.setText("<html> <center>" + Integer.toString(bitCoinNum) +
                // "</center> </html>");
                bitCoinAmount.setText(Integer.toString(bitCoinNum));
                bitCoinAmount.setHorizontalAlignment(SwingConstants.CENTER);
                bitCoinAmount.setFont(font(40));
                bitCoinAmount.setBounds(40, 125, length + 50, length);
                bitCoinAmount.setForeground(Color.WHITE);
                bitCoinAmount.setBorder(BorderFactory.createEmptyBorder());
                add(bitCoinAmount);

                bitCoin.setBounds(65, 50, length, length);
                bitCoin.setBorder(BorderFactory.createEmptyBorder());
                add(bitCoin);

                pliers.setBounds(915, 50, length, length);
                pliers.setContentAreaFilled(false);
                pliers.setFocusPainted(false);
                pliers.setBorder(BorderFactory.createEmptyBorder());
                add(pliers);

                exit = new JButton("Exit");
                exit.setFont(font(20));
                exit.setBounds(1025, 50, 100, length);
                exit.setForeground(Color.WHITE);
                exit.setContentAreaFilled(false);
                exit.setBorder(BorderFactory.createEmptyBorder());
                exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                window.showCard("pvzMain");
                        }
                });
                add(exit);
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
                threats[0] = new JLabel(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzThreats/pvzThreatTrojan.png"),
                                length, length)));
                threats[1] = new JLabel(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzThreats/pvzThreatTrap.png"),
                                length, length)));
                threats[2] = new JLabel(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzThreats/pvzThreatLogic.png"),
                                length, length)));
                threats[3] = new JLabel(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzThreats/pvzThreatStack.png"),
                                length, length)));
                threats[4] = new JLabel(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzThreats/pvzThreatVirus.png"),
                                length, length)));
                threats[5] = new JLabel(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzThreats/pvzThreatWorm.png"),
                                length, length)));
                threats[6] = new JLabel(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzThreats/pvzThreatPort.png"),
                                length, length)));
                threats[7] = new JLabel(new ImageIcon(resizeImage(
                                getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("pvz/pvzThreats/pvzThreatDenial.png"),
                                length, length)));
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

                int y = 50;
                int x = 100;
                for (int i = 0; i < 7; i++) {
                        defenders[i].setBounds(x + (100 * (i + 1)), y, length, length);
                        defenders[i].setContentAreaFilled(false);
                        defenders[i].setFocusPainted(false);
                        defenders[i].setBorder(BorderFactory.createEmptyBorder());
                        add(defenders[i]);
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
}
