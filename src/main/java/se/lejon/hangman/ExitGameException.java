package se.lejon.hangman;

public class ExitGameException extends RuntimeException {
    public ExitGameException() {
        super("Exiting game...");
    }
}
