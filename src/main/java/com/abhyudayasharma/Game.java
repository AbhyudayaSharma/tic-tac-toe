package com.abhyudayasharma;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

@ParametersAreNonnullByDefault
class Game {
    private PlayerType startPlayer = null;
    private PlayerType currentPlayer = null;
    private final JFrame gameFrame = new JFrame("Tic Tac Toe");
    private final List<Integer> humanMoves = new ArrayList<>(9);
    private final List<Integer> computerMoves = new ArrayList<>(9);

    @Nonnegative
    private final int[][] magicSquare = MagicSquare.getMagicSquare(3);

    void start() {
        gameFrame.setBackground(Color.BLACK);

        JPanel boxes = new JPanel();
        boxes.setLayout(new GridLayout(3, 3, 5, 5));
        boxes.setBackground(Color.BLACK);
        boxes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (int i = 0; i < 3 * 3; i++) {
            JButton button = new JButton();
            button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 44));
            button.setBackground(Color.WHITE);
            addOnClickListener(button, i / 3, i % 3);
            boxes.add(button);
            boxes.setBackground(Color.WHITE);
        }

        gameFrame.setPreferredSize(new Dimension(400, 400));
        gameFrame.setLayout(new BoxLayout(gameFrame.getContentPane(), BoxLayout.PAGE_AXIS));
        gameFrame.setResizable(false);
        gameFrame.add(boxes);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
        gameFrame.pack();

        // Who starts first?
        startPlayer = findStartPlayer();
        if (startPlayer == null) {
            gameFrame.dispose();
        }
        currentPlayer = startPlayer;
    }

    private void addOnClickListener(JButton button, int row, int col) {
        button.addActionListener(e -> {
            button.setEnabled(false);

            button.setText(currentPlayer == startPlayer ? "X" : "O");
            switch (currentPlayer) {
                case COMPUTER:
                    computerMoves.add(magicSquare[row][col]);
                    break;
                case HUMAN:
                    humanMoves.add(magicSquare[row][col]);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown PlayerType");
            }

            currentPlayer = getNextPlayer();

            // FIXME we don't need console logs, move it to the GUI
            System.out.println("Human moves: " + humanMoves);
            System.out.println("Computer moves: " + computerMoves);

            // FIXME check if the game has been won
            if (humanMoves.size() + computerMoves.size() == 9) {
                gameOver();
            }
        });
    }

    private void gameOver() {
        // FIXME
        JOptionPane.showMessageDialog(gameFrame, "Game Over! ${WINNER} won.", "Game Over",
            JOptionPane.PLAIN_MESSAGE);
        gameFrame.setVisible(false);
    }

    @CheckForNull
    private PlayerType findStartPlayer() {
        int response = JOptionPane.showOptionDialog(gameFrame, "Welcome to Tic Tac Toe!\n" +
                                                                   "Would you like to start first?",
            "Tic Tac Toe", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
            new String[]{"Yes", "No"}, "Yes");
        if (response == JOptionPane.CLOSED_OPTION) {
            return null;
        }

        if (response == JOptionPane.YES_OPTION) {
            return PlayerType.HUMAN;
        } else {
            return PlayerType.COMPUTER;
        }
    }


    @Nonnull
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
