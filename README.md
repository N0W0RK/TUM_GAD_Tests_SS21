# TUM_GAD_Tests_SS21

This is a repository to collect JUnit Tests for GAD exercises at TUM in SS21.
These tests have been written by students for students and are not part of the official course.

## Usage

To use the provided tests, first clone this repository.
Then, you have to create a symlink to your `src` folder to make it work with your IDE (see below).
After that you just have to include JUnit in your project and you're good to go.
To include JUnit you will just need to Alt+Enter on one of the red underlined @Test Annotation in the test file.
Your IDE will show you options to include the correct JAR.
**PLEASE ADD YOUR TEST PACKAGE TO `.gitignore` TO PREVENT UNNECASSARY LOAD ON ARTEMIS.**

### Windows

This has to be executed in the command line, run as administrator:

```
$ mklink /d \path\to\src\tests \path\to\repository\tests
```

### Linux/MacOS

```
$ ln -s /path/to/repository/tests /path/to/src/
```

## Structure of the tests

For every exercise that has tests, there will be a separate folder. The `gad` in the package name of the excercise file will be replaced by `tests`.
So, if the classes of the excercises are located in the package `gad.binarysearch` (for the class `gad.binarysearch.BinSea`), the tests will be located at `tests.binarysearch`.
Usually, the naming convention for test classes is to use the tested class name and append `Tester` (e.g. `BinSeaTester`) or another fitting name ending with `Tester`.
Further information can be found in the [contributing guidelines](docs/CONTRIBUTING.md), too.

## Collaboration

We will probably only add test cases from the problem statements and ones from issues we were experiencing.
If you have cases yourself or spot mistakes we did in the tests here, feel free to open an issue or to create a pull request.
Check out the [contributing guidelines](docs/CONTRIBUTING.md) for further information.
In case you are unsure how to create a pull request, take a look at the [GitHub documentation](https://docs.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request-from-a-fork).
Please use JUnit 5.7 when writing your own Tests. Existing tests will be modified accordingly.

## License

This project is licensed under GPLv3, see [LICENSE](LICENSE).
