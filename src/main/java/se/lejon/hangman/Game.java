package se.lejon.hangman;

import java.io.IOException;

public class Game {

    private final GameOutput gameOutput;
    private final GameInput gameInput;
    private final WordGenerator wordGenerator;
    private String playerName;
    private int wordLength;
    private String correctWord;
    private String correctGuessedCharacters;
    private boolean isRunning;

    public Game(GameOutput gameOutput, GameInput gameInput, WordGenerator wordGenerator) {
        this.gameOutput = gameOutput;
        this.gameInput = gameInput;
        this.wordGenerator = wordGenerator;
        this.gameOutput.outputEmptyRow();
        this.gameOutput.outputWithNewline("Initiating Hangman game...");
        this.gameOutput.outputEmptyRow();
        this.isRunning = true;
    }

    public void play() throws IOException {
        getPlayerName();
        getWordLengthAndGenerateWord();
        gameLoop();
    }

    private void getPlayerName() throws IOException {
        gameOutput.outputWithoutNewline("Please write your name: ");
        playerName = gameInput.readText();
    }

    private void getWordLengthAndGenerateWord() throws IOException {
        while (true) {
            gameOutput.outputWithoutNewline("Enter number of characters (min 3, max 5): ");
            wordLength = gameInput.readNumber();
            if (wordLength >= 3 && wordLength <= 5) {
                break;
            } else {
                gameOutput.outputWithNewline("Invalid input. Please enter a number between 3 and 5.");
            }
        }
        correctWord = wordGenerator.getWord(wordLength);
        correctGuessedCharacters = correctWord.replaceAll(".", "_");
        gameOutput.outputWithNewline("Psst, the word is : " + correctWord);
    }

    private void gameLoop() throws IOException {
        while (isRunning) {
            gameOutput.outputEmptyRow();
            gameOutput.outputWithNewline("You have: " + correctGuessedCharacters);
            gameOutput.outputWithoutNewline("Enter guess (or :x to exit): ");
            String guess = gameInput.readText().toUpperCase();
            processGuess(guess);
        }
    }

    private void processGuess(String guess) {
        if (guess.equalsIgnoreCase(":x")) {
            isRunning = false;
            gameOutput.outputWithNewline("Exiting game...");
            return;
        }

        if (guess.equalsIgnoreCase(correctWord)) {
            gameOutput.outputWithNewline("Congratulations " + playerName + "! You guessed the word!");
            isRunning = false;
        } else if (guess.length() > 1 && guess.length() != correctWord.length()) {
            gameOutput.outputWithNewline("Either guess the complete word or a single character!");
        } else if (guess.length() == 1) {
            var indexOfCorrectGuess = correctWord.indexOf(guess);
            if (indexOfCorrectGuess == -1) {
                gameOutput.outputWithNewline("" + guess + " is not part of the word. Try again!");
            } else {
                StringBuilder stringBuilder = new StringBuilder(correctGuessedCharacters);
                stringBuilder.setCharAt(indexOfCorrectGuess, guess.charAt(0));
                correctGuessedCharacters = stringBuilder.toString();

                if (correctGuessedCharacters.equalsIgnoreCase(correctWord)) {
                    gameOutput.outputWithNewline("Congratulations " + playerName + "! You guessed the word!");
                    isRunning = false;
                } else {
                    gameOutput.outputWithNewline("Yes! " + guess + " is part of the word. Keep going!");
                }
            }
        } else {
            gameOutput.outputWithNewline("Wrong guess, try again!");
        }
    }
}
