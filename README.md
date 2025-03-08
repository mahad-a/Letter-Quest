# Letter Quest

## Overview
Letter Quest is a word-guessing game built in Java using Swing and the MVC (Model-View-Controller) design pattern. 
The game challenges players to guess a five-letter word within a limited number of attempts, providing feedback using color-coded indicators:
- **Green**: Letter is correct and in the correct position.
- **Yellow**: Letter is in the word but in the wrong position.
- **Gray**: Letter is not in the word.

## Features
- Simple graphical interface using Swing.
- Color-coded feedback to guide player guesses.
- MVC architecture for clear separation of concerns.
- Option to start a new game from the menu.

## Installation
1. Ensure you have Java installed (JDK 8 or later).
2. Clone or download this repository.
3. Compile and run the game:
   ```sh
   javac *.java
   java Main
   ```

## How to Play
1. Enter a five-letter word when prompted (this will be the target word).
2. Start making guesses by entering five-letter words.
3. The game will provide feedback based on your guess.
4. Continue guessing until you find the correct word or run out of attempts.
5. Select the options in top left if you want to start a new game.

## File Structure
- **Model.java**  
  The Model handles the game logic, including tracking guesses, storing the answer, and updating the game state. It maintains a grid for user guesses and notifies views when updates occur.

- **View.java**  
  The View is responsible for the graphical user interface (GUI). It creates the game window, displays the board, and updates button colors based on guesses. It also includes the menu option to start a new game.

- **Controller.java**  
  The Controller manages interactions between the Model and View. It handles user input, processes guesses, and ensures that the Model updates correctly based on player actions.

- **ModelView.java**  
  This interface defines the method for updating views when the Model state changes. It ensures consistency between the game logic and UI representation.

- **Status.java**  
  An enumeration that represents the possible states of a guess: CORRECT, MISPLACED, and INCORRECT. It is used to determine the feedback given to the player.

- **Event.java**  
  Defines a custom event that carries information about a guess, such as the guessed word and its position on the grid. It is used to notify views of updates.

