package com.king.kata.yatzy;

public class YahtzeeRoll {
    private final int[] dice;

    public YahtzeeRoll(int d1, int d2, int d3, int d4, int d5) throws IllegalArgumentException {
        dice = new int[]{d1, d2, d3, d4, d5};
        for (int i : dice) {
            if (i < 1 || i > 6) throw new IllegalArgumentException();
        }
    }

    public int[] getDiceArray() {
        return dice;
    }
}
