CMSC125-System-Challenge

The System Challenge is an operating system-themed game bundle design in partial fulfillment of Assignment 2: "OS Game Bundle Development" in CMSC 125 AY 2021-2022.

The game bundle has three game options:
    a. Are you smarter than my O.S.? (An "Are you smarter than a 5th-grader" game variant)
    b. System Defense (A "Plants Vs. Zombies" game variant)
    c. System Calls Center (A "Sokoban" game variant)

How to run the game bundle: Open the System Challenge.jar file.
Alternatively, compile and run the game bundle in the command prompt by doing the following steps:

    1. Go to the directory of CMSC125-System-Challenge folder:
        "cd [path to CMSC125-System-Challenge folder]"
    2. Compile Java files:
        "javac --module-path "lib" --add-modules poi.ooxml,poi src/*.java -d src"
    3. If Step 2 has no errors, Run the program: 
        "java --module-path "lib" --add-modules poi.ooxml,poi -cp src GameBundle"

Happy playing!
