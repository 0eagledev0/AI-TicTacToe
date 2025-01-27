# AI-TicTacToe

## Description

This project is a Tic-Tac-Toe game that can be played between a human and a computer. The computer can play using two different algorithms: Minimax and Minimax with Alpha-Beta Pruning. The game is implemented in Java and allows the player to either start the game or let the computer start, with the player having the option to choose the algorithm for the AI.

---

## Features

- **Two AI Algorithms**:
    - **Minimax**: A decision-making algorithm that evaluates all possible moves and selects the one with the best score.
    - **Minimax with Alpha-Beta Pruning**: A more optimized version of the Minimax algorithm with pruning to reduce the number of nodes evaluated.

- **Human vs AI Gameplay**: Players can choose whether they want to play as 'X' or 'O' and also select who will play first.

- **Graphical Representation**: The game board is displayed after each move to show the current game state.

- **Customizable Game Mode**: Players can choose whether the game starts with them or the computer and select which algorithm the computer will use.

- **Game Replay**: After a game finishes, the user has the option to restart the game.

---

## Prerequisites

- Java 8 or above.
- An IDE (Integrated Development Environment) such as IntelliJ IDEA, Eclipse, or a text editor like Visual Studio Code.

---

## Usage

1. **Running the Game**:
    - Compile the Java files and run the `Main.java` class to start the game.
    - Follow the on-screen prompts to decide whether you want to play first, which algorithm to use, and whether to restart the game after finishing a round.

2. **Gameplay**:
    - Players will be asked to choose if they want to start the game. Then, they will select the algorithm for the AI:
        - **Option 1**: Minimax Algorithm.
        - **Option 2**: Minimax Algorithm with Alpha-Beta Pruning.
    - The game will prompt the user to make a move and display the board after each turn. The game ends when there is a winner or the board is full.

3. **Replaying**:
    - After a game ends, players can choose to play again or exit.

---

## Project Structure

```
├── Board.java           # Manages the game board and evaluates the game state.
├── CPUPlayer.java       # Implements the logic for the CPU player, using Minimax or Alpha-Beta Pruning algorithms.
├── Main.java            # Entry point for the game, handles user input and game initialization.
├── Mark.java            # Enum to represent the marks on the board: X, O, or EMPTY.
├── Move.java            # Represents a move with a row and column.
├── TicTacToe.java       # Manages the game flow and player turns.
└── README.md            # This file.
```

---

## Warnings

- This game does not currently handle invalid user inputs robustly. If an invalid input is entered (e.g., typing a letter when the game expects a number), the game may crash or behave unexpectedly. Future improvements could include input validation.

- The AI's decision-making is computationally intensive, especially when the board is almost full. The performance can be optimized further for larger grids or more complex algorithms.
