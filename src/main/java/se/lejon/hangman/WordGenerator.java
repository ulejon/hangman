package se.lejon.hangman;

import java.util.ArrayList;
import java.util.List;

public class WordGenerator {
    private List<String> words;

    public WordGenerator() {
        this.words = new ArrayList<>();
        words.add("SOL");
        words.add("ROS");
        words.add("PIL");

        words.add("TRÄD");
        words.add("BOLL");
        words.add("GRIS");

        words.add("KAFFE");
        words.add("ÄPPLE");
    }

    public String getWord(int length) {
        return words
            .stream()
            .filter(word -> word.length() == length)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No word with length " + length + " found"));
    }
}
