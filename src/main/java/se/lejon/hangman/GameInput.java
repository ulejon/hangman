package se.lejon.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameInput {
    private final BufferedReader br;

    public GameInput() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readText() throws IOException {
        return br.readLine();
    }

    public int readNumber() throws IOException {
        var numberAsText = readText();
        try {
            return Integer.parseInt(numberAsText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        }
    }
}
