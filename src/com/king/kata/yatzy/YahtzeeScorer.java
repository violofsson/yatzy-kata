package com.king.kata.yatzy;

import java.util.Arrays;

public class YahtzeeScorer {
    private final static int HIGHEST_NUMBER = 6;

    public int calculateScore(Category category, YahtzeeRoll roll) {
        int[] dice = roll.getDiceArray();
        switch (category) {
            case CHANCE:
                return sumAllDice(dice);
            case YAHTZEE:
                return (checkYahtzee(dice) ? 50 : 0);
            case ONES:
                return countX(dice, 1);
            case TWOS:
                return 2 * countX(dice, 2);
            case THREES:
                return 3 * countX(dice, 3);
            case FOURS:
                return 4 * countX(dice, 4);
            case FIVES:
                return 5 * countX(dice, 5);
            case SIXES:
                return 6 * countX(dice, 6);
            case PAIR:
                return 2 * highestSetOfAtLeastX(dice, 2);
            case TWO_PAIRS:
                int pair1 = highestSetOfAtLeastX(dice, 2);
                int pair2 = highestSetOfAtLeastX(dice, 2, pair1 - 1);
                return (pair1 != 0 && pair2 != 0) ? 2 * (pair1 + pair2) : 0;
            case THREE_OF_A_KIND:
                return 3 * highestSetOfAtLeastX(dice, 3, 6);
            case FOUR_OF_A_KIND:
                return 4 * highestSetOfAtLeastX(dice, 4, 6);
            case SMALL_STRAIGHT:
                return rollEquals(dice, new int[]{1, 2, 3, 4, 5}) ? 15 : 0;
            case LARGE_STRAIGHT:
                return rollEquals(dice, new int[]{2, 3, 4, 5, 6}) ? 20 : 0;
            case FULL_HOUSE:
                int pair = highestSetOfExactlyX(dice, 2);
                int threeOfAKind = highestSetOfExactlyX(dice, 3);
                return (pair != 0 && threeOfAKind != 0) ? 2 * pair + 3 * threeOfAKind : 0;
            default:
                return 0;
        }
    }

    private boolean checkYahtzee(int[] rolls) {
        int side = rolls[0];
        for (int i : rolls) {
            if (i != side) return false;
        }
        return true;
    }

    private int countX(int[] rolls, int x) {
        int sum = 0;
        for (int i : rolls) {
            if (i == x) sum += 1;
        }
        return sum;
    }

    private int countXFromCounts(int[] counts, int x) {
        return counts[x];
    }

    private int[] getCounts(int[] rolls) {
        int[] counts = new int[HIGHEST_NUMBER + 1]; // vi hoppar över index 0 för att minimera off-by-one-fel
        for (int i : rolls) {
            counts[i]++;
        }
        return counts;
    }

    private int highestSetOfAtLeastX(int[] rolls, int x) {
        return highestSetOfAtLeastX(rolls, x, HIGHEST_NUMBER);
    }

    private int highestSetOfAtLeastX(int[] rolls, int x, int highestNumber) {
        int[] counts = getCounts(rolls);
        for (int i = highestNumber; i >= 0; i--) {
            if (countXFromCounts(counts, i) >= x) return i;
        }
        return 0;
    }

    private int highestSetOfExactlyX(int[] rolls, int x) {
        int[] counts = getCounts(rolls);
        for (int i = HIGHEST_NUMBER; i > 0; i--) {
            if (countXFromCounts(counts, i) == x) return i;
        }
        return 0;
    }

    private boolean rollEquals(int[] roll1, int[] roll2) {
        return Arrays.equals(getCounts(roll1), getCounts(roll2));
    }

    private int sumAllDice(int[] rolls) {
        int sum = 0;
        for (int i : rolls) {
            sum += i;
        }
        return sum;
    }
}
