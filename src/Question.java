import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Question extends JPanel {

    private Window window;
    private JButton choiceA = new JButton();
    private JButton choiceB = new JButton();
    private JButton choiceC = new JButton();
    private JButton choiceD = new JButton();
    private JButton exitButton = new JButton();

    private JLabel questionLabel = new JLabel();

    private Color orange = new Color(226, 161, 101);

    private String answer;
    private String choiceAText;
    private String choiceBText;
    private String choiceCText;
    private String choiceDText;
    private String panel;

    private XSSFWorkbook wb;
    private XSSFSheet sheet;

    private boolean correct = false;

    public Question(int width, int height, Window w, String p) {
        setSize(width, height);
        setLayout(null);
        setOpaque(true);

        window = w;
        panel = p;
        setButtons();
        try {
            InputStream fis = getClass().getClassLoader().getResourceAsStream("quiz/quizQuestions.xlsx");
            wb = new XSSFWorkbook(fis);
            sheet = wb.getSheetAt(0);
        } catch (

        Exception e) {

        }

        add(questionLabel);
        add(exitButton);
        add(choiceA);
        add(choiceB);
        add(choiceC);
        add(choiceD);

        exitButton.setVisible(false);

        Random r = new Random();
        int index = r.nextInt(100);
        displayQuestion(index);
        setSokobanBackground(panel);
    }

    // Question Panel
    private void displayQuestion(int item) {
        Row row = sheet.getRow(item + 1); // returns the logical row
        choiceAText = row.getCell(3).getStringCellValue();
        choiceBText = row.getCell(4).getStringCellValue();
        choiceCText = row.getCell(5).getStringCellValue();
        choiceDText = row.getCell(6).getStringCellValue();
        answer = row.getCell(7).getStringCellValue();

        questionLabel.setText(
                "<html><center>" + row.getCell(2).getStringCellValue() + "</center></html>");
        questionLabel.setFont(font(20));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBounds(260, 150, 680, 180);

        choiceA.setForeground(Color.WHITE);
        choiceB.setForeground(Color.WHITE);
        choiceC.setForeground(Color.WHITE);
        choiceD.setForeground(Color.WHITE);
        choiceA.setText("<html><center>" + choiceAText + "</center></html>");
        choiceB.setText("<html><center>" + choiceBText + "</center></html>");
        choiceC.setText("<html><center>" + choiceCText + "</center></html>");
        choiceD.setText("<html><center>" + choiceDText + "</center></html>");

        questionLabel.setVisible(true);
        choiceA.setVisible(true);
        choiceB.setVisible(true);
        choiceC.setVisible(true);
        choiceD.setVisible(true);
    }

    // Setting Game 3 Background
    private void setSokobanBackground(String panel) {
        setBackground(Color.BLACK);

        if (panel == "sokoban") {
            JLabel sokobanStars = new JLabel();
            sokobanStars.setIcon(new ImageIcon(getClass()
                    .getClassLoader()
                    .getResource("sokoban/sokobanStars.png")));
            sokobanStars.setBounds(0, 0, 1200, 725);
            add(sokobanStars);
        }

        if (panel == "pvz") {
            JLabel pvzGlobe = new JLabel();
            pvzGlobe.setIcon(new ImageIcon(getClass()
                    .getClassLoader()
                    .getResource("pvz/pvzGlobe.png")));
            pvzGlobe.setBounds(0, 0, 1200, 725);
            add(pvzGlobe);
        }
    }

    private void setButtons() {
        newButton(exitButton, "EXIT", Color.WHITE, 15, 950, 500, 60, 50);
        newButton(choiceA, null, Color.WHITE, 15, 250, 330, 300, 100);
        newButton(choiceB, null, Color.WHITE, 15, 650, 330, 300, 100);
        newButton(choiceC, null, Color.WHITE, 15, 250, 430, 300, 100);
        newButton(choiceD, null, Color.WHITE, 15, 650, 430, 300, 100);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (panel == "sokoban")
                    window.showCard("sokobanPlay");
                if (panel == "pvz")
                    window.showCard("pvzPlay");
            }
        });

        setChoiceActionListeners(choiceA);
        setChoiceActionListeners(choiceB);
        setChoiceActionListeners(choiceC);
        setChoiceActionListeners(choiceD);

        setMouseListener(exitButton);
        setMouseListener(choiceA);
        setMouseListener(choiceB);
        setMouseListener(choiceD);
        setMouseListener(choiceC);
    }

    private void newButton(JButton newButton, String text, Color color, int size, int x, int y, int width,
            int height) {
        newButton.setText(text);
        newButton.setForeground(color);
        newButton.setFont(font(size));
        newButton.setBounds(x, y, width, height);
        newButton.setContentAreaFilled(false);
        newButton.setFocusPainted(false);
        newButton.setBorder(BorderFactory.createEmptyBorder());
    }

    private Font font(int size) {
        return window.useFont("Garet-Book.ttf", size);
    }

    private void setChoiceActionListeners(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == choiceA) {
                    if (choiceAText.matches(answer)) {
                        choiceA.setForeground(Color.GREEN);
                        correct = true;

                    } else {
                        choiceA.setForeground(Color.RED);
                    }

                    choiceD.setEnabled(false);
                    choiceB.setEnabled(false);
                    choiceC.setEnabled(false);

                } else if (e.getSource() == choiceB) {

                    if (choiceBText.matches(answer)) {
                        choiceB.setForeground(Color.GREEN);
                        correct = true;

                    } else {
                        choiceB.setForeground(Color.RED);
                    }

                    choiceA.setEnabled(false);
                    choiceD.setEnabled(false);
                    choiceC.setEnabled(false);
                }

                else if (e.getSource() == choiceC) {

                    if (choiceCText.matches(answer)) {
                        choiceC.setForeground(Color.GREEN);
                        correct = true;

                    } else {
                        choiceC.setForeground(Color.RED);
                    }

                    choiceA.setEnabled(false);
                    choiceB.setEnabled(false);
                    choiceD.setEnabled(false);
                }

                else if (e.getSource() == choiceD) {

                    if (choiceDText.matches(answer)) {
                        choiceD.setForeground(Color.GREEN);
                        correct = true;
                    } else {
                        choiceD.setForeground(Color.RED);
                    }

                    choiceA.setEnabled(false);
                    choiceB.setEnabled(false);
                    choiceC.setEnabled(false);
                }
                exitButton.setVisible(true);
            }
        });
    }

    private void setMouseListener(JButton button) {
        button.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                if (button.getForeground() == Color.WHITE)
                    button.setForeground(orange);
            }

            public void mouseExited(MouseEvent e) {
                if (button.getForeground() == orange)
                    button.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });
    }

    public boolean bonusCorrect() {
        return correct;
    }

    public void setBonusCorrect(boolean c) {
        correct = c;
    }
}