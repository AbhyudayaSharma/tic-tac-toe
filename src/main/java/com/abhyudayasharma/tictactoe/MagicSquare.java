package com.abhyudayasharma.tictactoe;

/**
 * Generates a magic square of odd size.
 *
 * @author Kabir Kanha Arora
 */
class MagicSquare {
    private int mainCnt = 0;
    private int[][] matrix;
    private static int expectedSum;

    /**
     * Private constructor for the Magic square.
     *
     * @param n the size of the magic square to be generated.
     */
    private MagicSquare(int n) {
        int max = n * n;
        expectedSum = (int) (n * (Math.pow(n, 2) + 1)) / 2;
        matrix = new int[n][n];
        matrix[0][(n - 1) / 2] = ++mainCnt;
        placeNextOdd(0, (n - 1) / 2, matrix, n, max);
    }

    /**
     * Returns the expected sum of every diagonal, row and column of the Magic Square.
     *
     * @return the expected sum of every diagonal, row and column of the Magic Square.
     */
    static int getExpectedSum() {
        return expectedSum;
    }

    /**
     * Calculates and returns a new magic square object. The objects produced by this methods
     * are isomorphic to one another but are not eht same objects.
     *
     * @param n the size of the magic square to be generated. Even values do not produce expected results.
     * @return a new magic square
     */
    @SuppressWarnings("SameParameterValue") // tic tac toe uses a 3x3 magic square only
    static int[][] getMagicSquare(int n) {
        return new MagicSquare(n).matrix;
    }

    /**
     * Recursive method for placing digits in the magic square.
     *
     * @param r      the row index
     * @param c      the column index
     * @param matrix matrix that will eventually become a magic square
     * @param n      the size of the magic square to be constructed
     * @param max    the maximum possible value in the magic square.
     */
    private void placeNextOdd(int r, int c, int[][] matrix, int n, int max) {
        if (mainCnt == max) {
            return;
        }

        int temp_r, temp_c;

        if (r - 1 < 0) {
            temp_r = n - 1;
        } else {
            temp_r = r - 1;
        }

        if (c + 1 >= n) {
            temp_c = 0;
        } else {
            temp_c = c + 1;
        }

        if (matrix[temp_r][temp_c] == 0) {
            matrix[temp_r][temp_c] = ++mainCnt;
            placeNextOdd(temp_r, temp_c, matrix, n, max);
        } else {
            if (r + 1 >= n) {
                r = 0;
            } else {
                ++r;
            }
            matrix[r][c] = ++mainCnt;
            placeNextOdd(r, c, matrix, n, max);
        }
    }
}
