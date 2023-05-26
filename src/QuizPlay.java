import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.io.File;
import java.io.FileInputStream;
// import java.util.Iterator;
// import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseEvent;

public class QuizPlay extends JPanel {

        private Window window;
        private ArrayList<Integer> questions = new ArrayList<>();

        private Random randomizer = new Random();

        private Color orange = new Color(226, 161, 101);

        private JLabel gameLabel = new JLabel("Choose A Category");
        private JLabel scoreLabel = new JLabel();
        private JLabel questionNo = new JLabel();
        private JLabel questionLabel = new JLabel();
        private JLabel quizLaptop;

        private JButton button1 = new JButton();
        private JButton button2 = new JButton();
        private JButton button3 = new JButton();
        private JButton button4 = new JButton();
        private JButton button5 = new JButton();

        private JButton nextButton = new JButton();
        private JButton choiceA = new JButton();
        private JButton choiceB = new JButton();
        private JButton choiceC = new JButton();
        private JButton choiceD = new JButton();
        private JButton[] wagerButtons = new JButton[10];

        private String choiceAText;
        private String choiceBText;
        private String choiceCText;
        private String choiceDText;
        private String answer;

        private int index = 0;
        private int score = 0;
        private int newScore = 0;
        private int wager = 0;

        private FileInputStream fis;
        private XSSFWorkbook wb;
        private XSSFSheet sheet;

        public QuizPlay(int width, int height, Window w) {
                setSize(width, height);
                setLayout(null);
                setOpaque(true);

                window = w;

                newButton(nextButton, "Next", Color.WHITE, 15, 950, 500, 60, 50);
                newButton(choiceA, null, Color.WHITE, 15, 250, 330, 300, 100);
                newButton(choiceB, null, Color.WHITE, 15, 650, 330, 300, 100);
                newButton(choiceC, null, Color.WHITE, 15, 250, 430, 300, 100);
                newButton(choiceD, null, Color.WHITE, 15, 650, 430, 300, 100);

                add(nextButton);

                nextButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (index < 10) {
                                        score = newScore;
                                        choiceA.setEnabled(true);
                                        choiceB.setEnabled(true);
                                        choiceC.setEnabled(true);
                                        choiceD.setEnabled(true);
                                        displayQuestion(index);
                                        nextButton.setVisible(false);
                                } else if (index == 10 && newScore == 10) {
                                        score = newScore;
                                        pageThree();
                                } else if (index == 10 && wager > 0) {
                                        score -= wager;
                                        for (JButton button : wagerButtons) {
                                                button.setVisible(false);
                                        }
                                        choiceA.setEnabled(true);
                                        choiceB.setEnabled(true);
                                        choiceC.setEnabled(true);
                                        choiceD.setEnabled(true);
                                        nextButton.setVisible(false);
                                        displayQuestion(index);
                                } else {
                                        reset();
                                        window.showCard("quizMain");
                                }
                        }
                });

                setChoiceActionListeners(choiceA);
                setChoiceActionListeners(choiceB);
                setChoiceActionListeners(choiceC);
                setChoiceActionListeners(choiceD);

                setMouseListener(nextButton);
                setMouseListener(choiceA);
                setMouseListener(choiceB);
                setMouseListener(choiceD);
                setMouseListener(choiceC);

                add(questionNo);
                add(questionLabel);
                add(scoreLabel);
                add(choiceA);
                add(choiceB);
                add(choiceC);
                add(choiceD);

                questionNo.setVisible(false);
                questionLabel.setVisible(false);
                scoreLabel.setVisible(false);
                choiceA.setVisible(false);
                choiceB.setVisible(false);
                choiceC.setVisible(false);
                choiceD.setVisible(false);
                nextButton.setVisible(false);

                try {
                        fis = new FileInputStream(new File(System.getProperty("user.dir")
                                        + "/src/resources/quiz/quizQuestions.xlsx"));
                        wb = new XSSFWorkbook(fis);
                        sheet = wb.getSheetAt(0);
                } catch (

                Exception e) {

                }

                pageOne();
                setQuizBackground();
        }

        private void reset() {
                choiceAText = null;
                choiceBText = null;
                choiceCText = null;
                choiceDText = null;
                answer = null;

                index = 0;
                score = 0;
                newScore = 0;
                wager = 0;

                questions.clear();
        }

        private void setQuizBackground() {
                setBackground(Color.BLACK);
                quizLaptop = new JLabel();
                quizLaptop.setIcon(new ImageIcon(System.getProperty("user.dir")
                                + "/src/resources/quiz/quizLaptop.png"));
                quizLaptop.setBounds(0, 0, 1200, 725);
                quizLaptop.setHorizontalAlignment(SwingConstants.CENTER);
                add(quizLaptop);
        }

        // Choose Category
        private void pageOne() {
                gameLabel.setFont(font(40));
                gameLabel.setBounds(355, 100, 500, 50);
                gameLabel.setForeground(orange);
                add(gameLabel);

                newButton(button1, "<html><center>Overview of Operating Systems</center></html>", Color.WHITE,
                                23, 220, 220,
                                200, 100);

                newButton(button2, "<html><center>Process Management</center></html>", Color.WHITE,
                                23, 500, 220,
                                200, 100);

                newButton(button3, "<html><center>Memory Management</center></html>", Color.WHITE,
                                23, 780, 220,
                                200, 100);

                newButton(button4, "<html><center>Storage Management</center></html>", Color.WHITE,
                                23, 350, 400,
                                200, 100);

                newButton(button5, "<html><center>Security and Protection</center></html>", Color.WHITE,
                                23, 650, 400,
                                200, 100);
                add(button1);
                add(button2);
                add(button3);
                add(button4);
                add(button5);

                setMouseListener(button1);
                setMouseListener(button2);
                setMouseListener(button3);
                setMouseListener(button4);
                setMouseListener(button5);

                setCategoryActionListeners(button1);
                setCategoryActionListeners(button2);
                setCategoryActionListeners(button3);
                setCategoryActionListeners(button4);
                setCategoryActionListeners(button5);
        }

        private void setCategoryActionListeners(JButton button) {
                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                pageTwo(button.getText());
                                button1.setVisible(false);
                                button2.setVisible(false);
                                button3.setVisible(false);
                                button4.setVisible(false);
                                button5.setVisible(false);
                                gameLabel.setVisible(false);
                        }
                });

        }

        private void setChoiceActionListeners(JButton button) {
                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (e.getSource() == choiceA) {
                                        if (choiceA.getForeground().equals(orange)) {
                                                if (choiceAText.matches(answer) && newScore <= score) {
                                                        choiceA.setForeground(Color.GREEN);
                                                        if (index == 10)
                                                                newScore = score + (wager * 3);
                                                        else
                                                                newScore = score + 1;
                                                        scoreLabel.setText(Integer.toString(newScore));

                                                } else {
                                                        choiceA.setForeground(Color.RED);
                                                }
                                                index += 1;
                                        }

                                        choiceD.setEnabled(false);
                                        choiceB.setEnabled(false);
                                        choiceC.setEnabled(false);
                                }

                                else if (e.getSource() == choiceB) {
                                        if (choiceB.getForeground().equals(orange)) {
                                                if (choiceBText.matches(answer) && newScore <= score) {
                                                        choiceB.setForeground(Color.GREEN);
                                                        if (index == 10)
                                                                newScore = score + (wager * 3);
                                                        else
                                                                newScore = score + 1;
                                                        scoreLabel.setText(Integer.toString(newScore));

                                                } else {
                                                        choiceB.setForeground(Color.RED);
                                                }
                                                index += 1;
                                        }

                                        choiceA.setEnabled(false);
                                        choiceD.setEnabled(false);
                                        choiceC.setEnabled(false);
                                }

                                else if (e.getSource() == choiceC) {
                                        if (choiceC.getForeground().equals(orange)) {
                                                if (choiceCText.matches(answer) && newScore <= score) {
                                                        choiceC.setForeground(Color.GREEN);
                                                        if (index == 10)
                                                                newScore = score + (wager * 3);
                                                        else
                                                                newScore = score + 1;
                                                        scoreLabel.setText(Integer.toString(newScore));
                                                } else {
                                                        choiceC.setForeground(Color.RED);
                                                }
                                                index += 1;
                                        }

                                        choiceA.setEnabled(false);
                                        choiceB.setEnabled(false);
                                        choiceD.setEnabled(false);
                                }

                                else if (e.getSource() == choiceD) {
                                        if (choiceD.getForeground().equals(orange)) {
                                                if (choiceDText.matches(answer)) {
                                                        choiceD.setForeground(Color.GREEN);
                                                        if (index == 10)
                                                                newScore = score + (wager * 3);
                                                        else
                                                                newScore = score + 1;
                                                        scoreLabel.setText(Integer.toString(newScore));
                                                } else {
                                                        choiceD.setForeground(Color.RED);
                                                }
                                                index += 1;
                                        }

                                        choiceA.setEnabled(false);
                                        choiceB.setEnabled(false);
                                        choiceC.setEnabled(false);
                                }
                                nextButton.setVisible(true);
                        }
                });

        }

        // Selection Question Panel
        private void pageTwo(String category) {
                int i = 0;
                int choice = -1;
                while (i < 11) {
                        if (category.contentEquals("<html><center>Overview of Operating Systems</center></html>")) {
                                choice = randomizer.nextInt(20);
                        } else if (category.contentEquals("<html><center>Process Management</center></html>")) {
                                choice = randomizer.nextInt(21, 40);
                        } else if (category.contentEquals("<html><center>Memory Management</center></html>")) {
                                choice = randomizer.nextInt(41, 60);
                        } else if (category.contentEquals("<html><center>Storage Management</center></html>")) {
                                choice = randomizer.nextInt(61, 80);
                        } else if (category.contentEquals("<html><center>Security and Protection</center></html>")) {
                                choice = randomizer.nextInt(81, 100);
                        }

                        if (!questions.contains(choice)) {
                                questions.add(choice);
                                i += 1;
                        } else if (choice == -1)
                                break;
                }

                if (questions.size() > 0)
                        displayQuestion(index);
        }

        // Question Panel
        private void displayQuestion(int item) {
                Row row = sheet.getRow(questions.get(item) + 1); // returns the logical row
                choiceAText = row.getCell(3).getStringCellValue();
                choiceBText = row.getCell(4).getStringCellValue();
                choiceCText = row.getCell(5).getStringCellValue();
                choiceDText = row.getCell(6).getStringCellValue();
                answer = row.getCell(7).getStringCellValue();

                questionNo.setText("Question " + Integer.toString(questions.get(item) + 1));
                questionNo.setFont(font(40));
                questionNo.setBounds(250, 100, 500, 50);
                questionNo.setForeground(orange);

                scoreLabel.setText(Integer.toString(score));
                scoreLabel.setFont(font(40));
                scoreLabel.setBounds(900, 100, 100, 100);
                scoreLabel.setForeground(orange);

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

                questionNo.setVisible(true);
                questionLabel.setVisible(true);
                scoreLabel.setVisible(true);
                choiceA.setVisible(true);
                choiceB.setVisible(true);
                choiceC.setVisible(true);
                choiceD.setVisible(true);
        }

        // Choose Wager Points
        private void pageThree() {
                questionNo.setText("MILLION DOLLAR QUESTION!");
                questionNo.setFont(font(30));
                questionLabel.setText("<html><center>How many points will you wager?</center></html>");

                nextButton.setVisible(false);
                choiceA.setVisible(false);
                choiceB.setVisible(false);
                choiceC.setVisible(false);
                choiceD.setVisible(false);

                for (int i = 1, j = 1; i <= 10; i++) {
                        wagerButtons[i - 1] = new JButton();
                        if (i < 6) {
                                newButton(wagerButtons[i - 1], Integer.toString(i), Color.WHITE, 15, 130 + (150 * j),
                                                300,
                                                50,
                                                50);
                                j++;
                        }

                        if (i == 6)
                                j = 1;

                        if (i > 5) {
                                newButton(wagerButtons[i - 1], Integer.toString(i), Color.WHITE, 15, 130 + (150 * j),
                                                400,
                                                50,
                                                50);
                                j++;
                        }
                        int choice = i;

                        add(wagerButtons[i - 1]);
                        setMouseListener(wagerButtons[i - 1]);
                        wagerButtons[i - 1].addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == wagerButtons[choice - 1]
                                                        && choice <= newScore
                                                        && newScore > 0) {
                                                for (JButton button : wagerButtons) {
                                                        if (button != wagerButtons[choice - 1])
                                                                button.setEnabled(false);
                                                }
                                                wager = choice;
                                                newScore = 0;
                                                nextButton.setVisible(true);
                                        }
                                }
                        });
                }

                remove(quizLaptop);
                setQuizBackground();
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
                return window.useFont(System.getProperty("user.dir") + "/src/resources/Alyssum-Sans.ttf", size);
        }

}