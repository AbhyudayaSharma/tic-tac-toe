import unittest

from tic_tac_toe.core.magic_square import get_magic_square


class MagicSquareTest(unittest.TestCase):
    def test_magic_square(self):
        magic_square = get_magic_square()
        self.assertEqual(True, False)


if __name__ == '__main__':
    unittest.main()
