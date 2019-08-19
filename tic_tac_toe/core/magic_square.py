"""Contains a method for generating a magic square"""
import numpy as np


def get_magic_square(n):
    """Generates a magic square for odd `n`."""
    # this function works only for odd `n`
    assert n % 2 == 1
    matrix = np.zeros(shape=(n, n))

    # initialize position of 1
    i = n // 2
    j = n - 1

    count = 1
    while count <= n * n:

        # The indices should be within bounds of the matrix
        if i == -1 and j == n:
            j = n - 2
            i = 0
        elif j == n:
            j = 0
        elif i < 0:
            i = n - 1

        if matrix[i][j]:
            j -= 2
            i += 1
        else:
            matrix[i][j] = count
            count += 1
            j += 1
            i -= 1

    return matrix
