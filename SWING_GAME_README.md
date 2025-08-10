# Swing Number Guessing Game

A modern Java Swing-based implementation of the classic number guessing game with multi-player support.

## Features

### 🎮 Game Start & Greeting
- Welcome screen with game instructions
- Player selection (1-6 players)
- Clean, user-friendly interface

### 🔄 Turn-Based Guessing
- Clear indication of current player's turn
- Real-time feedback for each guess:
  - 📈 "Too low" for guesses below the target
  - 📉 "Too high" for guesses above the target
  - 🎉 "Correct!" when the target is guessed

### 🎯 Game Loop
- Players take turns in sequence (Player 1 → Player 2 → Player 3 → ...)
- Game continues until:
  - Any player wins by guessing the correct number
  - Player clicks "Stop Game" to end early
- Guess counter tracks total attempts

### 🖥️ Swing UI Components
- **Input field** for number guesses (1-100)
- **Submit Guess button** to confirm guesses
- **Stop Game button** to end the current game
- **New Game button** to start over
- **Visual feedback** with colors and emojis
- **Player spinner** for selecting number of players

## How to Run

### Method 1: Using GameLauncher
```bash
cd src
javac *.java
java GameLauncher
```

### Method 2: Direct execution
```bash
cd src
javac *.java
java SwingNumberGuessingGame
```

## Game Rules

1. The computer selects a random number between 1 and 100
2. Players take turns guessing the number
3. After each guess, you'll receive feedback:
   - Too low: Your guess is smaller than the target
   - Too high: Your guess is larger than the target
   - Correct: You've found the target number!
4. The first player to guess correctly wins
5. You can stop the game at any time

## Technical Details

- **Language**: Java
- **GUI Framework**: Swing
- **Target Range**: 1-100
- **Max Players**: 1-6
- **Input Validation**: Ensures valid number ranges
- **Error Handling**: User-friendly error messages

## File Structure

```
src/
├── SwingNumberGuessingGame.java  # Main Swing implementation
├── GameLauncher.java             # Simple launcher
├── Main.java                     # Original demo file (unchanged)
└── MyProjectPackage/
    └── GuesserGameProject.java   # Original console game (unchanged)
```

## Screenshots

The game features:
- 🎯 Welcome screen with instructions
- 🎮 Game interface with current player display
- 📊 Guess counter and feedback messages
- 🎨 Colorful, intuitive design
- 🔄 Easy game restart functionality

Enjoy playing the Number Guessing Game! 🎉
