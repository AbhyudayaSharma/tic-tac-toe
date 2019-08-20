package com.abhyudayasharma;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Game {
    private PlayerType currentPlayer = null;
    private List<Integer> humanMoves = new ArrayList<>(9);
    private List<Integer> computerMoves = new ArrayList<>(9);

    void start() {
        currentPlayer = getStartPlayer();
        JFrame frame = new JFrame("Tic Tac Toe");

        JPanel boxes = new JPanel();
        boxes.setLayout(new GridLayout(3, 3, 5, 5));

        for (int i = 0; i < 9; i++) {
            JButton button = new JButton(ThreadLocalRandom.current().nextBoolean() ? "O" : "X");
            button.setFont(button.getFont().deriveFont(44f));
            boxes.add(button);
            boxes.setBackground(Color.WHITE);
        }

        frame.setPreferredSize(new Dimension(400, 400));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.setResizable(false);
        frame.add(boxes);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

    private PlayerType getStartPlayer() {
        int response = JOptionPane.showOptionDialog(null, "Would you like to start first?",
            "Tic Tac Toe", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
            new String[]{"Yes", "No"}, "Yes");
        if (response == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

        if (response == JOptionPane.YES_OPTION) {
            return PlayerType.HUMAN;
        } else {
            return PlayerType.COMPUTER;
        }
    }

    private PlayerType getNextPlayer() {
        switch (currentPlayer) {
            case HUMAN:
                return PlayerType.COMPUTER;
            case COMPUTER:
                return PlayerType.HUMAN;
            default:
                throw new IllegalArgumentException("Unknown PlayerType");
        }
    }
}
