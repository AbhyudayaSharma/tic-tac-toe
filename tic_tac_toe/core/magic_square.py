"""Contains a method for generating a magic square"""
import numpy as np


def get_magic_square(size):
    """Generates a magic square for odd `size`."""
    # this function works only for odd `size`
    assert size % 2 == 1
    matrix = np.zeros(shape=(size, size))

    # initialize position of 1
    i = size // 2
    j = size - 1

    count = 1
    while count <= size * size:

        # The indices should be within bounds of the matrix
        if i == -1 and j == size:
            j = size - 2
            i = 0
        elif j == size:
            j = 0
        elif i < 0:
            i = size - 1

        if matrix[i][j]:
            j -= 2
            i += 1
        else:
            matrix[i][j] = count
            count += 1
            j += 1
            i -= 1

    return matrix
