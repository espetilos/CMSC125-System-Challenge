import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SokobanPlay extends JPanel {
    private ArrayList<String> lines = new ArrayList<>();
    private Window window;
    private JLabel sokobanStars;
    private JLabel[] exampleSystemCalls = new JLabel[6];
    private JLabel[] sokobanSystemCalls = new JLabel[6];
    private ImageIcon[] solvedIcons = new ImageIcon[6];
    private ImageIcon[] unsolvedIcons = new ImageIcon[6];

    private JLabel android;
    private JLabel powerUp;
    private JButton reset;
    private JButton exit;
    private JButton legend;

    private Question sokobanQuestion;
    private int playerRow = -1;
    private int playerColumn = -1;
    private int powerUpTriggerRow = -1;
    private int powerUpTriggerCol = -1;
    private int powerUpRow = -1;
    private int powerUpCol = -1;
    private int[] exampleCallsRow = new int[6];
    private int[] exampleCallsCol = new int[6];
    private int[] systemCallsRow = new int[6];
    private int[] systemCallsCol = new int[6];

    private int initplayerRow = -1;
    private int initplayerColumn = -1;
    private int[] initexampleCallsRow = new int[6];
    private int[] initexampleCallsCol = new int[6];

    private int numRows = 0;
    private int numCols = 0;

    private char[][] map;

    private boolean powerUpUsed = false;

    private Color orange = new Color(226, 161, 101);

    public SokobanPlay(int width, int height, Window w) {
        setSize(width, height);
        setLayout(null);
        setOpaque(true);

        window = w;
        sokobanMap(); // Setting Map
        setSokobanButtons(); // Setting Buttons
        setSokobanBackground(); // Setting Panel Background

        // Key Bindings
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "moveRight");

        actionMap.put("moveUp", new MoveAction("Up"));
        actionMap.put("moveDown", new MoveAction("Down"));
        actionMap.put("moveLeft", new MoveAction("Left"));
        actionMap.put("moveRight", new MoveAction("Right"));
    }

    // Setting Game 3 Background
    private void setSokobanBackground() {
        setBackground(Color.BLACK);
        sokobanStars = new JLabel();
        sokobanStars.setIcon(new ImageIcon(getClass()
                .getClassLoader()
                .getResource("sokoban/sokobanStars.png")));
        sokobanStars.setBounds(0, 0, 1200, 725);
        add(sokobanStars);
    }

    // Setting All Game Play Buttons
    private void setSokobanButtons() {
        reset = button("Reset", font(30), Color.WHITE, 850, 200, 270, 80);
        exit = button("Exit", font(30), Color.WHITE, 850, 300, 270, 80);
        legend = button("Legend", font(30), Color.WHITE, 850, 400, 270, 80);

        add(reset);
        add(exit);
        add(legend);

        setActionAndMouseListeners();
    }

    // Setting Game Map
    private void sokobanMap() {
        // Read Map.txt
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass()
                .getClassLoader()
                .getResourceAsStream("sokoban/map.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get dimensions of map
        numRows = lines.size();
        numCols = lines.get(0).length();

        // Record map with array
        map = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < numCols; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // Start of Setting Up Map Elements //
        int y = 30; // start Y
        int boxSize = 40; // length of box side

        // Setting up Main Character
        android = new JLabel();
        android.setIcon(new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanIcon.png"),
                boxSize, boxSize)));
        add(android);

        // Setting Up Boxes
        randomInitialSystemCall();

        // setting up Power Up Location
        powerUp = new JLabel(new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanPowerUp.png"),
                40, 40)));
        randomPowerUp();
        add(powerUp);

        // Setting Up Solved Boxes
        setSolvedCalls();

        // Setting up Map
        for (int i = 0; i < numRows; i++) {
            int x = 80; // start x
            for (int j = 0; j < numCols; j++) {
                char element = map[i][j];
                // Main Character's location
                if (element == '9') {
                    map[i][j] = '.';
                    initplayerRow = i;
                    initplayerColumn = j;
                    playerRow = i;
                    playerColumn = j;

                    android.setBounds(x, y, boxSize, boxSize);

                    // Ground under Main Character
                    JLabel sokobanGround = new JLabel();
                    sokobanGround.setIcon(new ImageIcon(resizeImage(
                            getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("sokoban/sokobanMap/sokobanGround.png"),
                            boxSize, boxSize)));
                    sokobanGround.setBounds(x, y, boxSize, boxSize);
                    add(sokobanGround);
                }
                // Ground Location
                else if (element == '.') {
                    // add ground
                    JLabel sokobanGround = new JLabel();
                    sokobanGround.setIcon(new ImageIcon(resizeImage(
                            getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("sokoban/sokobanMap/sokobanGround.png"),
                            boxSize, boxSize)));
                    sokobanGround.setBounds(x, y, boxSize, boxSize);
                    add(sokobanGround);
                }
                // Brick Location
                else if (element == 'x') {
                    // add brick
                    JLabel sokobanBrick = new JLabel();
                    sokobanBrick.setIcon(new ImageIcon(resizeImage(
                            getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("sokoban/sokobanMap/sokobanBrick.png"),
                            boxSize, boxSize)));
                    sokobanBrick.setBounds(x, y, boxSize, boxSize);
                    add(sokobanBrick);
                }
                // System Call 1 Location
                else if (element == '1') {
                    systemCallsRow[0] = i;
                    systemCallsCol[0] = j;
                    sokobanSystemCalls[0] = new JLabel();
                    unsolvedIcons[0] = new ImageIcon(resizeImage(
                            getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("sokoban/sokobanSystemCall/sokobanProcessControl.png"),
                            boxSize, boxSize));
                    sokobanSystemCalls[0].setIcon(unsolvedIcons[0]);
                    sokobanSystemCalls[0].setBounds(x, y, boxSize, boxSize);
                    add(sokobanSystemCalls[0]);
                }
                // System Call 2 Location
                else if (element == '2') {
                    systemCallsRow[1] = i;
                    systemCallsCol[1] = j;
                    sokobanSystemCalls[1] = new JLabel();
                    unsolvedIcons[1] = new ImageIcon(resizeImage(
                            getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("sokoban/sokobanSystemCall/sokobanFileManagement.png"),
                            boxSize, boxSize));
                    sokobanSystemCalls[1].setIcon(unsolvedIcons[1]);
                    sokobanSystemCalls[1].setBounds(x, y, boxSize, boxSize);
                    add(sokobanSystemCalls[1]);
                }
                // System Call 3 Location
                else if (element == '3') {
                    systemCallsRow[2] = i;
                    systemCallsCol[2] = j;
                    sokobanSystemCalls[2] = new JLabel();
                    unsolvedIcons[2] = new ImageIcon(resizeImage(
                            getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("sokoban/sokobanSystemCall/sokobanDeviceManagement.png"),
                            boxSize, boxSize));
                    sokobanSystemCalls[2].setIcon(unsolvedIcons[2]);
                    sokobanSystemCalls[2].setBounds(x, y, boxSize, boxSize);
                    add(sokobanSystemCalls[2]);
                }
                // System Call 4 Location
                else if (element == '4') {
                    systemCallsRow[3] = i;
                    systemCallsCol[3] = j;
                    sokobanSystemCalls[3] = new JLabel();
                    unsolvedIcons[3] = new ImageIcon(resizeImage(
                            getClass()
                                    .getClassLoader()
                                    .getResourceAsStream(
                                            "sokoban/sokobanSystemCall/sokobanInformationMaintenanceManagement.png"),
                            boxSize, boxSize));
                    sokobanSystemCalls[3].setIcon(unsolvedIcons[3]);
                    sokobanSystemCalls[3].setBounds(x, y, boxSize, boxSize);
                    add(sokobanSystemCalls[3]);
                }
                // System Call 5 Location
                else if (element == '5') {
                    systemCallsRow[4] = i;
                    systemCallsCol[4] = j;
                    sokobanSystemCalls[4] = new JLabel();
                    unsolvedIcons[4] = new ImageIcon(resizeImage(
                            getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("sokoban/sokobanSystemCall/sokobanCommunications.png"),
                            boxSize, boxSize));
                    sokobanSystemCalls[4].setIcon(unsolvedIcons[4]);
                    sokobanSystemCalls[4].setBounds(x, y, boxSize, boxSize);
                    add(sokobanSystemCalls[4]);
                }
                // System Call 6 Location
                else if (element == '6') {
                    systemCallsRow[5] = i;
                    systemCallsCol[5] = j;
                    sokobanSystemCalls[5] = new JLabel();
                    unsolvedIcons[5] = new ImageIcon(resizeImage(
                            getClass()
                                    .getClassLoader()
                                    .getResourceAsStream("sokoban/sokobanSystemCall/sokobanProtection.png"),
                            boxSize, boxSize));
                    sokobanSystemCalls[5].setIcon(unsolvedIcons[5]);
                    sokobanSystemCalls[5].setBounds(x, y, boxSize, boxSize);
                    add(sokobanSystemCalls[5]);
                }
                x = x + 45;
            }
            y = y + 45;
        }
    }

    //
    private void randomPowerUp() {
        // Trigger operation
        Random r = new Random();
        int row = -1;
        int col = -1;

        // Choose random trigger spot
        do {
            row = r.nextInt(numRows);
            col = r.nextInt(numCols);
        } while (map[row][col] != '.');

        powerUpTriggerRow = row;
        powerUpTriggerCol = col;

        // System.out.println("Trigger: map[" + powerUpTriggerRow + "][" +
        // powerUpTriggerCol + "]");

        // Choose random power up spot
        do {
            row = r.nextInt(numRows);
            col = r.nextInt(numCols);
        } while (map[row][col] != '.');

        powerUpRow = row;
        powerUpCol = col;

        powerUp.setBounds(80 + (powerUpCol * 45), 30 + (powerUpRow * 45), 40, 40);
        powerUp.setVisible(false);

        sokobanQuestion = new Question(window.getWidth(), window.getHeight(),
                window, "sokoban");
    }

    // Setting solved icons
    private void setSolvedCalls() {
        solvedIcons[0] = new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanSolvedSystemCall/sokobanProcessControlSolved.png"),
                40, 40));

        solvedIcons[1] = new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanSolvedSystemCall/sokobanFileManagementSolved.png"),
                40, 40));

        solvedIcons[2] = new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanSolvedSystemCall/sokobanDeviceManagementSolved.png"),
                40, 40));

        solvedIcons[3] = new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream(
                                "sokoban/sokobanSolvedSystemCall/sokobanInformationMaintenanceManagementSolved.png"),
                40, 40));
        solvedIcons[4] = new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanSolvedSystemCall/sokobanCommunicationsSolved.png"),
                40, 40));

        solvedIcons[5] = new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanSolvedSystemCall/sokobanProtectionSolved.png"),
                40, 40));
    }

    // Setting Up Example System Calls
    private void randomInitialSystemCall() {
        exampleSystemCalls[0] = new JLabel(new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanExampleSystemCall/sokobanCreateProcess().png"),
                40, 40)));
        exampleSystemCalls[1] = new JLabel(new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanExampleSystemCall/sokobanCreateFile().png"),
                40, 40)));
        exampleSystemCalls[2] = new JLabel(new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanExampleSystemCall/sokobanReadConsole().png"),
                40, 40)));
        exampleSystemCalls[3] = new JLabel(new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanExampleSystemCall/sokobanSetTimer().png"),
                40, 40)));
        exampleSystemCalls[4] = new JLabel(new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanExampleSystemCall/sokobanCreateFileMapping().png"),
                40, 40)));
        exampleSystemCalls[5] = new JLabel(new ImageIcon(resizeImage(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream("sokoban/sokobanExampleSystemCall/sokobanSetFileSecurity().png"),
                40, 40)));

        // Random Selection of Location per Example System Call
        for (int i = 0; i < 6; i++) {
            Random r = new Random();
            int row = -1;
            int col = -1;
            // Check surroundings
            boolean taken;
            do {
                taken = false;
                row = r.nextInt(numRows);
                col = r.nextInt(numCols);

                if (map[row][col] != '.')
                    taken = true;
                else if (map[row + 1][col] != '.'
                        || map[row - 1][col] != '.'
                        || map[row][col + 1] != '.'
                        || map[row][col - 1] != '.'
                        || map[row - 1][col - 1] != '.'
                        || map[row + 1][col + 1] != '.'
                        || map[row - 1][col + 1] != '.'
                        || map[row + 1][col - 1] != '.')
                    taken = true;
                for (int j = 0; j < 6; j++) {
                    if (i != j && exampleCallsCol[j] == col && exampleCallsRow[j] == row)
                        taken = true;
                }
            } while (taken);

            initexampleCallsRow[i] = row;
            initexampleCallsCol[i] = col;
            exampleCallsRow[i] = row;
            exampleCallsCol[i] = col;
            exampleSystemCalls[i].setBounds(80 + (col * 45), 30 + (row * 45), 40, 40);
            add(exampleSystemCalls[i]);
        }
    }

    // Reset after click
    private void reset() {
        // Reset Android Spot
        playerColumn = initplayerColumn;
        playerRow = initplayerRow;
        android.setBounds(80 + (initplayerColumn * 45), 30 + (initplayerRow * 45), 40, 40);

        // Reset System Call Icons
        for (int i = 0; i < 6; i++) {
            // Reset system call icon
            sokobanSystemCalls[i].setIcon(unsolvedIcons[i]);
            // Reset example call spot
            exampleCallsCol[i] = initexampleCallsCol[i];
            exampleCallsRow[i] = initexampleCallsRow[i];
            exampleSystemCalls[i].setVisible(true);
            exampleSystemCalls[i].setBounds(80 + (initexampleCallsCol[i] * 45), 30 + (initexampleCallsRow[i] * 45), 40,
                    40);
        }

        // Reset powerUp visibility and new random power
        randomPowerUp();
        powerUpUsed = false;

        // Reset powerup question
        sokobanQuestion = new Question(window.getWidth(), window.getHeight(),
                window, "sokoban");
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

    // Button Required Settings
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

    // Game 3 Buttons
    private Font font(int size) {
        return window.useFont("Garet-Book.ttf", size);
    }

    // Action and Mouse Listeners for All Buttons
    public void setActionAndMouseListeners() {
        // Show Legend
        legend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showCard("sokobanHowToPlay6");
            }
        });

        // Go back to Game Main Menu
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showCard("sokobanMain");
            }
        });

        // Reset Map
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        // Highlight Buttons at entry
        reset.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                reset.setForeground(orange);
            }

            public void mouseExited(MouseEvent e) {
                reset.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                reset.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });

        exit.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                exit.setForeground(orange);
            }

            public void mouseExited(MouseEvent e) {
                exit.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                exit.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });

        legend.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                legend.setForeground(orange);
            }

            public void mouseExited(MouseEvent e) {
                legend.setForeground(Color.WHITE);
            }

            public void mouseReleased(MouseEvent e) {
                legend.setForeground(Color.WHITE);
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }
        });
    }

    // Inner Class for Key Bindings
    private class MoveAction extends AbstractAction {
        private String direction;

        public MoveAction(String direction) {
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Main Character's current location in panel
            int x = android.getX();
            int y = android.getY();

            // The current example system call at Character's surroundings
            int boxLeftIndex = 0; // Left
            int boxRightIndex = 0; // Right
            int boxUpIndex = 0; // Up
            int boxDownIndex = 0; // Down

            // Current space at character's surroundings
            char left;
            char right;
            char up;
            char down;

            // Flag if there's an example system call within character's surroundings
            boolean boxLeft = false;
            boolean boxRight = false;
            boolean boxUp = false;
            boolean boxDown = false;

            // Check if example system call within surrounding of character
            for (int i = 0; i < 6; i++) {
                if (playerColumn - 1 == exampleCallsCol[i] && playerRow == exampleCallsRow[i]) {
                    boxLeft = true;
                    boxLeftIndex = i;
                }
                if (playerColumn + 1 == exampleCallsCol[i] && playerRow == exampleCallsRow[i]) {
                    boxRight = true;
                    boxRightIndex = i;
                }
                if (playerRow - 1 == exampleCallsRow[i] && playerColumn == exampleCallsCol[i]) {
                    boxUp = true;
                    boxUpIndex = i;
                }
                if (playerRow + 1 == exampleCallsRow[i] && playerColumn == exampleCallsCol[i]) {
                    boxDown = true;
                    boxDownIndex = i;
                }
            }

            // Check what is within character's surroundings
            if (boxLeft) {
                left = map[playerRow][playerColumn - 2];
            } else
                left = map[playerRow][playerColumn - 1];
            if (boxRight) {
                right = map[playerRow][playerColumn + 2];
            } else
                right = map[playerRow][playerColumn + 1];
            if (boxUp) {
                up = map[playerRow - 2][playerColumn];
            } else
                up = map[playerRow - 1][playerColumn];
            if (boxDown) {
                down = map[playerRow + 2][playerColumn];
            } else
                down = map[playerRow + 1][playerColumn];

            boolean movable = true;
            if (direction == "Left") {
                // If Left Side is not a wall
                if (left != 'x') {
                    // if there's a box at left
                    if (boxLeft) {
                        // check if another box is at left of box
                        for (int i = 0; i < 6; i++) {
                            // if left of box is another box's location
                            if (boxLeftIndex != i) {
                                if (exampleCallsCol[boxLeftIndex] - 1 == exampleCallsCol[i]
                                        && exampleCallsRow[boxLeftIndex] == exampleCallsRow[i]) {
                                    movable = false;
                                }
                            }
                        }

                        // check if its system call is left of box
                        if ((exampleCallsCol[boxLeftIndex] - 1 == systemCallsCol[boxLeftIndex]
                                && exampleCallsRow[boxLeftIndex] == systemCallsRow[boxLeftIndex])
                                || sokobanQuestion.bonusCorrect()) {
                            exampleCallsCol[boxLeftIndex] = systemCallsCol[boxLeftIndex];
                            exampleCallsRow[boxLeftIndex] = systemCallsRow[boxLeftIndex];
                            sokobanSystemCalls[boxLeftIndex].setIcon(solvedIcons[boxLeftIndex]); // change Icon
                            exampleSystemCalls[boxLeftIndex].setVisible(false); // hide example call;
                            sokobanQuestion.setBonusCorrect(false);
                        }

                        if (movable) {
                            // move example call
                            exampleSystemCalls[boxLeftIndex].setBounds(
                                    exampleSystemCalls[boxLeftIndex].getX() -
                                            exampleSystemCalls[boxLeftIndex].getWidth()
                                            - 5,
                                    exampleSystemCalls[boxLeftIndex].getY(),
                                    exampleSystemCalls[boxLeftIndex].getWidth(),
                                    exampleSystemCalls[boxLeftIndex].getHeight());
                            exampleCallsCol[boxLeftIndex] -= 1;
                        }
                    }

                    // If player is next to a system call
                    else if (map[playerRow][playerColumn - 1] != '.') {
                        movable = false;
                    }
                } else if (boxLeft && sokobanQuestion.bonusCorrect()) {
                    exampleCallsCol[boxLeftIndex] = systemCallsCol[boxLeftIndex];
                    exampleCallsRow[boxLeftIndex] = systemCallsRow[boxLeftIndex];
                    sokobanSystemCalls[boxLeftIndex].setIcon(solvedIcons[boxLeftIndex]); // change Icon
                    exampleSystemCalls[boxLeftIndex].setVisible(false); // hide example call;
                    sokobanQuestion.setBonusCorrect(false);
                } else
                    movable = false;

                if (movable) {
                    // Character moves to left
                    // If player is next to power Up trigger
                    if (!powerUpUsed && playerColumn - 1 == powerUpTriggerCol
                            && playerRow == powerUpTriggerRow) {
                        powerUp.setVisible(true);
                    }

                    // If player is next to power Up
                    if (powerUp.isVisible() && playerColumn - 1 == powerUpCol && playerRow == powerUpRow) {
                        window.add("sokobanQuestion", sokobanQuestion);
                        window.showCard("sokobanQuestion");
                        powerUpUsed = true;
                        powerUp.setVisible(false);
                    }

                    playerColumn -= 1;
                    android.setBounds(x - android.getWidth() - 5, y, android.getWidth(),
                            android.getHeight());
                }
            }

            if (direction == "Right") {
                // If Right Side is not a wall
                if (right != 'x') {
                    // if there's a box at right
                    if (boxRight) {
                        // check if another box is at right of box
                        for (int i = 0; i < 6; i++) {
                            // if right of box is another box's location
                            if (boxRightIndex != i) {
                                if (exampleCallsCol[boxRightIndex] + 1 == exampleCallsCol[i]
                                        && exampleCallsRow[boxRightIndex] == exampleCallsRow[i]) {
                                    movable = false;
                                }
                            }
                        }

                        // check if its system call is right of box
                        if ((exampleCallsCol[boxRightIndex] + 1 == systemCallsCol[boxRightIndex]
                                && exampleCallsRow[boxRightIndex] == systemCallsRow[boxRightIndex])
                                || sokobanQuestion.bonusCorrect()) {
                            exampleCallsCol[boxRightIndex] = systemCallsCol[boxRightIndex];
                            exampleCallsRow[boxRightIndex] = systemCallsRow[boxRightIndex];
                            sokobanSystemCalls[boxRightIndex].setIcon(solvedIcons[boxRightIndex]); // change Icon
                            exampleSystemCalls[boxRightIndex].setVisible(false); // remove example call
                            sokobanQuestion.setBonusCorrect(false);
                        }

                        if (movable) {
                            exampleSystemCalls[boxRightIndex].setBounds(
                                    exampleSystemCalls[boxRightIndex].getX() +
                                            exampleSystemCalls[boxRightIndex].getWidth()
                                            + 5,
                                    exampleSystemCalls[boxRightIndex].getY(),
                                    exampleSystemCalls[boxRightIndex].getWidth(),
                                    exampleSystemCalls[boxRightIndex].getHeight());
                            exampleCallsCol[boxRightIndex] += 1;
                        }
                    }

                    // If player is next to a system call
                    else if (map[playerRow][playerColumn + 1] != '.') {
                        movable = false;
                    }
                } else if (boxRight && sokobanQuestion.bonusCorrect()) {
                    exampleCallsCol[boxRightIndex] = systemCallsCol[boxRightIndex];
                    exampleCallsRow[boxRightIndex] = systemCallsRow[boxRightIndex];
                    sokobanSystemCalls[boxRightIndex].setIcon(solvedIcons[boxRightIndex]); // change Icon
                    exampleSystemCalls[boxRightIndex].setVisible(false); // remove example call
                    sokobanQuestion.setBonusCorrect(false);
                } else
                    movable = false;

                if (movable) {
                    // Character moves to right
                    if (!powerUpUsed && playerColumn + 1 == powerUpTriggerCol && playerRow == powerUpTriggerRow) {
                        powerUp.setVisible(true);
                    }

                    // If player is next to power Up
                    if (powerUp.isVisible() && playerColumn + 1 == powerUpCol && playerRow == powerUpRow) {
                        window.add("sokobanQuestion", sokobanQuestion);
                        window.showCard("sokobanQuestion");
                        powerUpUsed = true;
                        powerUp.setVisible(false);
                    }

                    playerColumn += 1;
                    android.setBounds(x + android.getWidth() + 5, y, android.getWidth(),
                            android.getHeight());
                }
            }

            if (direction == "Up") {
                // If Up Side is not a wall
                if (up != 'x') {
                    // if there's a box at up
                    if (boxUp) {
                        // check if another box is at up of box
                        for (int i = 0; i < 6; i++) {
                            // if right of box is another box's location
                            if (boxUpIndex != i) {
                                if (exampleCallsCol[boxUpIndex] == exampleCallsCol[i]
                                        && exampleCallsRow[boxUpIndex] - 1 == exampleCallsRow[i]) {
                                    movable = false;
                                }
                            }
                        }

                        // check if its system call is right of box
                        if ((exampleCallsCol[boxUpIndex] == systemCallsCol[boxUpIndex]
                                && exampleCallsRow[boxUpIndex] - 1 == systemCallsRow[boxUpIndex])
                                || sokobanQuestion.bonusCorrect()) {
                            exampleCallsCol[boxUpIndex] = systemCallsCol[boxUpIndex];
                            exampleCallsRow[boxUpIndex] = systemCallsRow[boxUpIndex];
                            sokobanSystemCalls[boxUpIndex].setIcon(solvedIcons[boxUpIndex]); // change Icon
                            exampleSystemCalls[boxUpIndex].setVisible(false); // remove example call
                            sokobanQuestion.setBonusCorrect(false);
                        }

                        if (movable) {
                            exampleSystemCalls[boxUpIndex].setBounds(
                                    exampleSystemCalls[boxUpIndex].getX(),
                                    exampleSystemCalls[boxUpIndex].getY() -
                                            exampleSystemCalls[boxUpIndex].getWidth()
                                            - 5,
                                    exampleSystemCalls[boxUpIndex].getWidth(),
                                    exampleSystemCalls[boxUpIndex].getHeight());
                            exampleCallsRow[boxUpIndex] -= 1;
                        }
                    }

                    // If player is next to a system call
                    else if (map[playerRow - 1][playerColumn] != '.') {
                        movable = false;
                    }
                } else if (boxUp && sokobanQuestion.bonusCorrect()) {
                    exampleCallsCol[boxUpIndex] = systemCallsCol[boxUpIndex];
                    exampleCallsRow[boxUpIndex] = systemCallsRow[boxUpIndex];
                    sokobanSystemCalls[boxUpIndex].setIcon(solvedIcons[boxUpIndex]); // change Icon
                    exampleSystemCalls[boxUpIndex].setVisible(false); // remove example call
                    sokobanQuestion.setBonusCorrect(false);
                } else
                    movable = false;

                if (movable) {
                    // Character moves up
                    if (!powerUpUsed && playerColumn == powerUpTriggerCol
                            && playerRow - 1 == powerUpTriggerRow) {
                        powerUp.setVisible(true);
                    }

                    // If player is next to power Up
                    if (powerUp.isVisible() && playerColumn == powerUpCol && playerRow - 1 == powerUpRow) {
                        window.add("sokobanQuestion", sokobanQuestion);
                        window.showCard("sokobanQuestion");
                        powerUpUsed = true;
                        powerUp.setVisible(false);
                    }

                    playerRow -= 1;
                    android.setBounds(x, y - android.getWidth() - 5, android.getWidth(),
                            android.getHeight());
                }
            }

            if (direction == "Down") {
                // If Down Side is not a wall
                if (down != 'x') {
                    // if there's a box at up
                    if (boxDown) {
                        // check if another box is at up of box
                        for (int i = 0; i < 6; i++) {
                            // if right of box is another box's location
                            if (boxDownIndex != i) {
                                if (exampleCallsCol[boxDownIndex] == exampleCallsCol[i]
                                        && exampleCallsRow[boxDownIndex] + 1 == exampleCallsRow[i]) {
                                    movable = false;
                                }
                            }
                        }

                        // check if its system call is right of box
                        if ((exampleCallsCol[boxDownIndex] == systemCallsCol[boxDownIndex]
                                && exampleCallsRow[boxDownIndex] + 1 == systemCallsRow[boxDownIndex])
                                || sokobanQuestion.bonusCorrect()) {
                            exampleCallsCol[boxDownIndex] = systemCallsCol[boxDownIndex];
                            exampleCallsRow[boxDownIndex] = systemCallsRow[boxDownIndex];
                            sokobanSystemCalls[boxDownIndex].setIcon(solvedIcons[boxDownIndex]); // change Icon
                            exampleSystemCalls[boxDownIndex].setVisible(false); // remove example call
                            sokobanQuestion.setBonusCorrect(false);
                        }

                        if (movable) {
                            exampleSystemCalls[boxDownIndex].setBounds(
                                    exampleSystemCalls[boxDownIndex].getX(),
                                    exampleSystemCalls[boxDownIndex].getY() +
                                            exampleSystemCalls[boxDownIndex].getWidth()
                                            + 5,
                                    exampleSystemCalls[boxDownIndex].getWidth(),
                                    exampleSystemCalls[boxDownIndex].getHeight());
                            exampleCallsRow[boxDownIndex] += 1;
                        }
                    }

                    // If player is next to a system call
                    else if (map[playerRow + 1][playerColumn] != '.') {
                        movable = false;
                    }
                } else if (boxDown && sokobanQuestion.bonusCorrect()) {
                    exampleCallsCol[boxDownIndex] = systemCallsCol[boxDownIndex];
                    exampleCallsRow[boxDownIndex] = systemCallsRow[boxDownIndex];
                    sokobanSystemCalls[boxDownIndex].setIcon(solvedIcons[boxDownIndex]); // change Icon
                    exampleSystemCalls[boxDownIndex].setVisible(false); // remove example call
                    sokobanQuestion.setBonusCorrect(false);
                } else
                    movable = false;

                if (movable) {
                    // If player is next to power Up Trigger
                    if (!powerUpUsed && playerColumn == powerUpTriggerCol && playerRow + 1 == powerUpTriggerRow) {
                        powerUp.setVisible(true);
                    }

                    // If player is next to power Up
                    if (powerUp.isVisible() && playerColumn == powerUpCol && playerRow + 1 == powerUpRow) {
                        window.add("sokobanQuestion", sokobanQuestion);
                        window.showCard("sokobanQuestion");
                        powerUpUsed = true;
                        powerUp.setVisible(false);
                    }
                    playerRow += 1;
                    android.setBounds(x, y + android.getWidth() + 5, android.getWidth(),
                            android.getHeight());
                }
            }

            boolean end = true;
            // Check if all in
            for (int i = 0; i < 6; i++) {
                if (exampleSystemCalls[i].isVisible()) {
                    end = false;
                }
            }
            if (end)
                window.showCard("sokobanMain");
        }
    }
}
