package com.alignable;
import java.util.*;

public class Solution {

    // write a program that can follow an online board game to actually form words
    // out of selecting adjacent letters on the board.
    // If not adjacent, should not be able to select
    // if already selected, should not be able to select
    // if letter equals to blank, should not be able to select

    public static void main(String... args) {
        Board board = new Board(5, 5);
        System.out.println(board.getPositions());
        System.out.println("Selecting: expected true: " + board.onSelect(0, 0));

        System.out.println(board.getSelected());

        System.out.println("Selecting: expected true: " + board.onSelect(1, 0));
        System.out.println("Selecting: expected true: " + board.onSelect(1, 1));
        System.out.println(board.getSelected());
        System.out.println("Selecting: expected false: " + board.onSelect(1, 0));

        System.out.println("Selecting: expected false: " + board.onSelect(3, 3));
    }
}

class Board {
    private static final Random rand = new Random();
    private static final char BLANK_CHAR = ' ';
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz ";
    private Map<String, Tile> positions = new HashMap<>();
    private List<Tile> selected = new LinkedList<>();

    public Board(int maxX, int maxY) {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                char value = ALPHABET.charAt(rand.nextInt(ALPHABET.length()));

                String key = generateKey(x, y);

                positions.put(key, new Tile(x, y, value));
            }
        }
    }

    private String generateKey(int x, int y) {
        return String.format("%d-%d", x, y);
    }

    public Map<String, Tile> getPositions() {
        return positions;
    }

    public List<Tile> getSelected() {
        return selected;
    }

    public boolean onSelect(int x, int y) {
        Tile newSelected = positions.get(generateKey(x, y));
        if (newSelected.getValue() == BLANK_CHAR) {
            return false;
        }

        if (!selected.isEmpty()) {
            Tile lastSelected = getLastSelected();
            // is adjacent
            int xDifference = Math.abs(x - lastSelected.getX());
            int yDifference = Math.abs(y - lastSelected.getY());

            if (xDifference > 1 || yDifference > 1) {
                return false;
            }

            if (this.selected.contains(newSelected)) {
                return false;
            }
        }

        selected.add(newSelected);
        return true;
    }

    private Tile getLastSelected() {
        return selected.get(this.selected.size() - 1);
    }

    public void done() {
        // validate the word and score
    }
    public void clear() {
        selected.clear();
    }

}

class Tile {
    private int x;
    private int y;

    private char value;

    public Tile(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }
}

