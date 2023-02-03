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

        if (panel == "quizMain") { // Game 1 Panel
            setBackground(Color.BLACK);
            setButtons();
            setLabels();
        }

        setActionAndMouseListeners();
    }

    private Font setFont(int size) {
        return window.useFont(System.getProperty("user.dir") + "/src/resources/Alyssum-Sans.ttf", size);
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

    private JLabel label(String label, Font font, Color color, int x, int y, int width, int height, ImageIcon icon) {
        JLabel theLabel = new JLabel(label);
        theLabel.setFont(font);
        theLabel.setForeground(color);
        theLabel.setBounds(x, y, width, height);
        theLabel.setIcon(icon);
        theLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return theLabel;
    }

    private void setButtons() {
        playButton = button("Play", setFont(30), Color.WHITE, 450, 350, 300, 50);
        add(playButton);

        howToPlayButton = button("How to Play", setFont(30), Color.WHITE, 450, 400, 300, 50);
        add(howToPlayButton);

        exitButton = button("Exit", setFont(30), Color.WHITE, 450, 450, 300, 50);
        add(exitButton);
    }

    private void setLabels() {
        JLabel quizIcon = label(null, null, null, 260, 130, 150, 130,
                new ImageIcon(System.getProperty("user.dir") + "/src/resources/quizIcon.png"));
        add(quizIcon);

        JLabel gameLabel1 = label("Are you Smarter", setFont(45), orange,
                440, 140, 490, 50, null);
        add(gameLabel1);

        JLabel gameLabel2 = label("than my OS?", setFont(60), Color.WHITE,
                440, 200, 490, 50, null);
        add(gameLabel2);

        JLabel quizLaptop = label(null, null, null, 0, 0, 1200, 725,
                new ImageIcon(System.getProperty("user.dir") + "/src/resources/quizLaptop.png"));
        add(quizLaptop);
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