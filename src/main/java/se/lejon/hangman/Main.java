package se.lejon.hangman;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var logger = new GameOutput();
        var gameInput = new GameInput();
        var wordGenerator = new WordGenerator();
        var game = new Game(logger, gameInput, wordGenerator);

        try {
            game.play();
        } catch (ExitGameException e) {
            logger.outputWithNewline("Exiting game...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
