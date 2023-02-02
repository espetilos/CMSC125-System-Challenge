import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Other extends JPanel {
    private Window window;

    private JButton backButton;

    private Font emulogicFont30;
    private Font emulogicFont20;
    private Font emulogicFont10;

    public Other(int width, int height, String panel, Window g) {
        setSize(width, height);
        setLayout(null);
        setOpaque(true);

        window = g;
        emulogicFont30 = window.useFont(System.getProperty("user.dir") + "/src/resources/Emulogic.ttf", 30);
        emulogicFont20 = window.useFont(System.getProperty("user.dir") + "/src/resources/Emulogic.ttf", 20);
        emulogicFont10 = window.useFont(System.getProperty("user.dir") + "/src/resources/Emulogic.ttf", 10);
        setButtons();

        if (panel == "gameBundleCredits") {
            setBackground(Color.BLACK);

            JLabel creditsLabel = new JLabel("Credits");
            creditsLabel.setForeground(Color.WHITE);
            creditsLabel.setFont(emulogicFont30);
            creditsLabel.setBounds(450, 100, 300, 50);

            JLabel leaderLabel = new JLabel("Edu S. Petilos (Leader)");
            leaderLabel.setForeground(Color.WHITE);
            leaderLabel.setFont(emulogicFont20);
            leaderLabel.setBounds(150, 250, 600, 50);

            JLabel memberLabel = new JLabel("Kliezl P. Eclipse (Member)");
            memberLabel.setForeground(Color.WHITE);
            memberLabel.setFont(emulogicFont20);
            memberLabel.setBounds(150, 300, 600, 50);

            JLabel groupLabel = new JLabel("Assignment 2 (Group 5)");
            groupLabel.setForeground(Color.WHITE);
            groupLabel.setFont(emulogicFont20);
            groupLabel.setBounds(150, 400, 600, 50);

            JLabel courseLabel = new JLabel("CMSC 125 (A.Y. 2021-2022)");
            courseLabel.setForeground(Color.WHITE);
            courseLabel.setFont(emulogicFont20);
            courseLabel.setBounds(150, 450, 600, 50);

            add(creditsLabel);
            add(leaderLabel);
            add(memberLabel);
            add(groupLabel);
            add(courseLabel);
        }

        setActionAndMouseListeners();
    }

    public void setButtons() {
        backButton = new JButton("BACK");
        backButton.setFont(emulogicFont10);
        backButton.setForeground(Color.WHITE);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setBounds(10, 10, 200, 100);
        add(backButton);
    }

    public void setActionAndMouseListeners() {
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showCard("gameBundleMain");
            }
        });

        backButton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                backButton.setForeground(Color.ORANGE);
            }

            public void mouseExited(MouseEvent e) {
                backButton.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                backButton.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });
    }
}