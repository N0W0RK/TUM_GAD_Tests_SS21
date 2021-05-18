# Thank You
First of all thank you for considering to contribute. Having more people that write their own tests, we may achieve covering all tests on artemis, and therefore allow students to debug their code efficiently.  
If you have any question, feel free to open an issue here or contact me on Zulip @Konrad Gößmann.

# Guidelines

These are guidelines to keep tests in a somewhat well defined structuere. The goal ist, that students are able to clone the project, create a symlink, add Junit and be able to immediatly and easily test their submition.


## Test location
We want to have a unified test location for all exercises and a reasonable package structure. Therefore every exercise will have its own folder with the packages and tests inside of them.  
The package structure should mirror the structure of the exercise but replaces the `gad` package with a `tests` package.  

Tests for general purposes should be in a file wich miorrors the name of the main class with "Tester" as a suffix. General tests for `Hashtable.java` should be in the file `HashtableTester.java`.
An exception to this would be if the main file contains "Main", so the tests for `HashtableMain.java` would still be `HashtableTester.java`.  

If you create tests for additional classes ort special cases, put those in dedicated files or packages.

## JUnit
Please use JUnit 5.7.0 and not 4 for its extend feature Set and unified imports.

## Tests
Please try to use a descriptive name for your test. Try to describe either where your tests come from or what they intend to achieve.  
Also please try to use the assert methods most suitable to the comparison. Comparing arrays or testing if Exceptions are thrown can be achieved with dedicated methods and allow for easier debugging.  
Each assertion should also contain a message that describes the source of the expected value.
String comparisons should be avoided. The user will be able to change their toString() method.
