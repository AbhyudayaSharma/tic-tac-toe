package com.abhyudayasharma.tictactoe;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Vector;

@ParametersAreNonnullByDefault
class Game {
    private PlayerType startPlayer = null;
    private PlayerType currentPlayer = null;
    private final JFrame gameFrame = new JFrame("Tic Tac Toe");
    private final JList<Integer> humanMovesList = new JList<>();
    private final JList<Integer> computerMovesList = new JList<>();
    private final Vector<Integer> humanMoves = new Vector<>(9);
    private final Vector<Integer> computerMoves = new Vector<>(9);

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
            button.setPreferredSize(new Dimension(125, 125));
            button.setMinimumSize(button.getPreferredSize());
            addOnClickListener(button, i / 3, i % 3);
            boxes.add(button);
            boxes.setBackground(Color.WHITE);
        }

        JPanel lists = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.weighty = 0;
        lists.add(new JLabel("Human moves"), gbc);
        gbc.gridx++;
        lists.add(new JLabel("Computer moves"));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridheight = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        var humanMovesScrollPane = new JScrollPane(humanMovesList);
        humanMovesScrollPane.setPreferredSize(new Dimension(100, 100));
        lists.add(humanMovesScrollPane, gbc);
        gbc.gridx++;

        var computerMovesScrollPane = new JScrollPane(computerMovesList);
        computerMovesScrollPane.setPreferredSize(new Dimension(100, 100));
        lists.add(computerMovesScrollPane, gbc);
        lists.setBackground(Color.WHITE);

        gameFrame.setLayout(new BoxLayout(gameFrame.getContentPane(), BoxLayout.LINE_AXIS));
        gameFrame.setResizable(false);
        gameFrame.add(boxes);
        gameFrame.add(lists);
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
            humanMovesList.setListData(humanMoves);
            computerMovesList.setListData(computerMoves);

            // FIXME check if the game has been won
            if (humanMoves.size() + computerMoves.size() == 9) {
                gameOver();
            }
        });

        humanMovesList.setEnabled(false);
        computerMovesList.setEnabled(false);
    }

    private void gameOver() {
        // FIXME
        JOptionPane.showMessageDialog(gameFrame, "Game Over! ${WINNER} won.", "Game Over",
            JOptionPane.PLAIN_MESSAGE);
        gameFrame.dispose();
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
