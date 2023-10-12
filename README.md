# Assigment 4
This is the generated project for assignment 4.

1. Copy the clone link and use `git clone` to make a local copy in a suitable folder on your computer.
2. Step into the created folder using `cd`
3. Build the application using `./gradlew build` - it should build without problems
4. Run the application using `./gradlew run -q --console=plain` - it should display an hello world message.
5. Open the project in your IDE (e.g. VSCode) and start programming.


## Project structure
The project contains a basic Java project file structure as generated by gradle using JUnit testing. In addition two plugins have been added to the build procedure.

1. Checkstyle using the google java standard. There are two changes to the standard: it allows for slighly longer lines, and package names may contain numbers i.e. `assignment4`

2. Findbugs. This is a static code analysis tool it analyses the code and tries to find common problems related to bugs, performance problems etc. You should strive to minimize such problems.

To make things a bit easier and more transparent to use the above quality checks are automatically run as part of the testing procedure of the implementation. If you have errors these will be reported as JUnit test case problems, if you have too many such issues the build will fail.

Use `./gradlew build` to build the system - this will also report errors from the plugins above in the console. If you have many issues it is adviced to tackle the first one.

To run the application use `./gradlew run -q --console=plain`

## Build Pipeline
When pusing code to gitlab a build pipeline will run. This will try to build the code and then run all the tests. This is one of the main advantages of using gradle: we get a uniform and distributable way to build and test our system that can be automatically run. In our case we run gradle in a docker image build using gradle 7.3.3, jdk17 and alpine linux. 

The results of the build process will be shown to you, and you can dig deeper by using the CI/CD menu in gilab. If there are any failures it is adviced to check the outputs of the respective steps and try to figure out what could be the problem. You can also inspect the test report of the automatic testing step.

## Adding existing code
If you have already worked on the task in whole or part using your own gradle setup we reccomend that you copy only the source code files into this structure. Your source code should be copied into the `app/src/main/java/` folder and you can change the `application` `mainClass` in `build.gradle` accordingly.

## Radius and Orbit Radius Limits
In this Solar System Registry application, radius and orbit radius values for stars, planets, and moons have the following limits:

All radius values (for stars, planets, and moons) should be positive, non-zero numbers.
The orbit radius values for planets and moons should also be positive, non-zero numbers.
The specific limits are as follows:




Assignment 4: Solar System Registry
This program allows users to create and manage a registry of solar systems. Each solar system can contain multiple planets, and each planet can have multiple moons. The program also allows users to load solar systems from a text file and save them back to a text file.

Constraints
The following constraints apply to the various celestial objects:

Star
Radius must be between 0.1 and 10,000.
Planet
Radius must be between 1,000 and 1,000,000.
Orbit radius must be between 1,000,000 and 10,000,000,000.
Moon
Radius must be between 1,000 and 100,000.
Orbit radius must be between 1,000 and 1,000,000.
Usage
To run the program, follow these steps:

Compile the Java source files.
Run the App class as the main class.
Follow the on-screen prompts to interact with the program.
You can create new solar systems, add planets and moons to them, and manage existing solar systems. Additionally, you can load and save solar systems from and to text files.
