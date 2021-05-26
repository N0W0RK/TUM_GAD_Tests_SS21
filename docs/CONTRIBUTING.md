# Contributing guidelines

## Thank You!

First of all, thank you for considering to contribute!
Having more people that write their own tests, we may achieve covering all tests on Artemis, and therefore allow students to debug their code efficiently.

If you have any question, feel free to open an issue here or contact me on Zulip @Konrad Gößmann or use the dedicated Zulip stream `GAD-Tests`.

## Scope of this guidelines

These are guidelines to keep the tests in a somewhat well defined structure.
The goal is that students are able to clone the project, create a symlink, add JUnit and be able to immediately and easily test their submission.

## Test location

We want to have a unified test location for all exercises and a reasonable package structure.
Therefore every exercise will have its own folder with the packages and tests inside of them.
The package structure should mirror the structure of the exercise while replacing the `gad` package with a `tests` package.

Tests for general purposes should be in a file wich mirrors the name of the main class with "Tester" as a suffix.
For example, general tests for the file `Hashtable.java` should be in the file `HashtableTester.java`.
An exception to this would be if the main file contains the phrase "Main", so the tests for `HashtableMain.java` would still be in `HashtableTester.java`.

If you create tests for additional classes or special cases, put those in dedicated files or packages.
But try to stick to the structure of the tests of the other exercises or the exercise you want to test.

## JUnit

Please use JUnit 5.7 and **not** version 4 for its extended feature set and unified imports.

## Tests

Please try to use a descriptive name for your test.
Try to describe either where your tests come from or what they intend to achieve.
Also please try to use the `assert` methods most suitable to the comparison.
Comparing arrays or testing if exceptions are thrown can be achieved with dedicated methods and allow for easier debugging.
Each assertion should also contain a message that describes the source of the expected value.
Generally, string comparisons should be avoided because a user might want to change their `toString()` method.
