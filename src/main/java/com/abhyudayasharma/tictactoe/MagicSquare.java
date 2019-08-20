package com.abhyudayasharma.tictactoe;

class MagicSquare {
    private int mainCnt = 0;
    private int[][] matrix;

    private MagicSquare(int n) {
        int max = n * n;
        matrix = new int[n][n];
        matrix[0][(n - 1) / 2] = ++mainCnt;
        placeNextOdd(0, (n - 1) / 2, matrix, n, max);
    }

    static int[][] getMagicSquare(int n) {
        return new MagicSquare(n).matrix;
    }

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
