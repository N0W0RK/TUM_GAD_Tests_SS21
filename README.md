# TUM_GAD_Tests_SS21
This is a repository to collect JUnit Tests for GAD exercises at TUM in SS21
These tests have been written by students and are not part of the official course.

## Usage

To use the provided tests, first clone this repository.

For every exercise that has tests, there will be a separate folder. The gad in the package of the excercise file will be replaced by tests. So if the excercise file is located at `gad.binarysearch.BinSea`, the tests will be located at `tests.binarysearch.BinSea`.

To use the tests in your IDE you have to create a symlink to your src folder.

### Windows:
```
$ mklink /J \path\to\repository\tests \path\to\src\tests
```

### Linux/MacOS
```
$ ln -s /path/to/repository/tests /path/to/src/tests
```

After that you just have to include JUnit in your project and you're good to go.

To include JUnit you will just need to Alt+Enter on one of the red underlined @Test Annotation in the Test file. Your IDE will show you options to include the correct JAR.

## Collaboration

I will probably only add test cases from the problem statements and ones from issues I am experiencing. If you have cases yourself, be sure to create a pull request.
