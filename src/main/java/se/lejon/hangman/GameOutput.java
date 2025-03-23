package se.lejon.hangman;

public class GameOutput {
    public GameOutput() {

    }

    public void outputWithNewline(String message) {
        System.out.println(message);
    }

    public void outputWithoutNewline(String message) {
        System.out.print(message);
    }

    public void outputEmptyRow() {
        outputWithNewline("");
    }
}
