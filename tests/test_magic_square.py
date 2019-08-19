import unittest
import numpy as np

from tic_tac_toe.core.magic_square import get_magic_square


class MagicSquareTest(unittest.TestCase):
    expected_sum = 15
    expected_array = np.full(shape=3, fill_value=expected_sum)

    def test_magic_square(self):
        magic_square = get_magic_square(3)
        self.assertTrue(
            np.array_equal(
                magic_square.sum(
                    axis=0),
                self.expected_array))
        self.assertTrue(
            np.array_equal(
                magic_square.sum(
                    axis=1),
                self.expected_array))
        self.assertEqual(magic_square.diagonal().sum(), self.expected_sum)


if __name__ == '__main__':
    unittest.main()
