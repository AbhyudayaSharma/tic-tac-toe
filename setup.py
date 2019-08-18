from setuptools import setup
from setuptools import find_packages

INSTALL_REQUIRES = [
    'numpy == 1.17.0'
]

TEST_REQUIRES = [
    'pytest == 5.1.0'
]

setup(version='0.1',
      name='tic_tac_toe',
      license='MIT License',
      packages=find_packages(),
      test_requires=TEST_REQUIRES,
      install_requires=INSTALL_REQUIRES)
