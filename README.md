# Guess The Number! - A Java Swing Game

## Overview

"Guess The Number!" is an interactive desktop game built with Java Swing, offering a classic number guessing experience with a modern interface. Players challenge their intuition across multiple difficulty levels, aiming to pinpoint a secret number within a limited number of attempts. The game provides helpful hints, tracks your progress, and manages scores for endless fun!

## Key Features

* **Adjustable Difficulty:**
    * **Easy:** Guess between 1 and 100.
    * **Medium:** Guess between 1 and 500.
    * **Hard:** Guess between 1 and 1000.
* **Attempt Management:** Each difficulty level currently provides 10 attempts.
* **Intuitive Feedback:** Receive instant hints like "Too Low!", "Too High!", or "Very close!" to guide your next guess.
* **Guess History Log:** A dedicated panel displays all your previous guesses, helping you strategize.
* **Scoreboard:** Keep track of your wins and losses across all games.
* **Automated Progression/Restart:** Win a level to automatically advance to the next (if available). Lose a level, and it will automatically restart after a brief pause.
* **User-Friendly Interface:** Clean and simple Swing UI for an enjoyable gaming experience.

## How to Play

1.  **Launch the Game:** Run the application to bring up the main game window.
2.  **Choose Your Challenge:** Use the dropdown menu at the top to select your desired difficulty (Easy, Medium, or Hard). This choice will reset the game and set the new number range.
3.  **Make a Guess:** Enter a number into the "Enter guess:" text field.
4.  **Submit:** Click the "GUESS" button to see if you're correct!
5.  **Observe Feedback:** The "messageLabel" will provide hints based on your guess. The "Attempts Left:" counter will update, showing your remaining chances.
6.  **Review History:** The "Guess History:" panel on the right will log every number you've tried.
7.  **Game End:**
    * **Win:** If you guess correctly, you'll be informed of your victory and the number of attempts taken. The game will then automatically transition to the next difficulty level or congratulate you for completing all levels.
    * **Loss:** If you run out of attempts, the game will reveal the correct number. It will then automatically restart the current level after a short delay.
8.  **Start Anew:** Click "New Game" anytime to reset the current game without affecting your overall win/loss record.

## Getting Started (Development Setup)

### Requirements

* Java Development Kit (JDK) 8 or newer.

### Build and Run Instructions

1.  **Save the Source:** Store the `NumberGuessing.java` file in your preferred directory.
2.  **Compile:** Open your terminal or command prompt, navigate to the directory where you saved the file, and execute the Java compiler:
    ```bash
    javac NumberGuessing.java
    ```
3.  **Execute:** Once compiled successfully, run the application using the Java Virtual Machine:
    ```bash
    java NumberGuessing
    ```

## Customization Options

Feel free to modify the `NumberGuessing.java` source code to personalize your game:

* **Game Parameters:** Adjust `maxNumber` (the upper limit for the random number) and `maxAttempts` (the number of guesses allowed) within the `resetGame()` method for each difficulty level.
* **Visuals:** Experiment with the `setBackground()`, `setForeground()`, and `setFont()` methods of various Swing components to change the game's color scheme and typography.
* **In-Game Messages:** Customize the text displayed by `messageLabel` to alter the feedback and guidance provided to the player.
* **Proximity Hint:** Modify the threshold for the "Very close!" hint by changing the `5` in `Math.abs(guess - randomNumber) <= 5`.

## Source Code

The entire game logic and UI are contained within the `NumberGuessing.java` file.
Key components include:
* The `NumberGuessing()` constructor sets up the main window and all interactive elements.
* The `actionPerformed()` method handles player guesses and game state transitions.
* `resetGame()` manages game initialization and difficulty changes.
* `endGame()` handles win/loss scenarios and post-game actions.
* `updateHistory()` ensures the guess log is always current.


