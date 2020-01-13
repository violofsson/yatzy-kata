package com.king.kata.yatzy;

import java.util.Arrays;

public class YatzyScorer {
    public int calculateScore(Category category, YatzyRoll roll) {
        if (category == Category.CHANCE) {
            return sumAllDice(roll.getDice());
        } else if (category == Category.YAHTZEE) {
            return (yatzy(roll.getDice()) ? 50 : 0);
        } else if (category == Category.ONES) {
            return countX(1, roll.getDice());
        } else if (category == Category.TWOS) {
            return 2 * countX(2, roll.getDice());
        } else if (category == Category.THREES) {
            return 3 * countX(3, roll.getDice());
        } else if (category == Category.FOURS) {
            return 4 * countX(4, roll.getDice());
        } else if (category == Category.FIVES) {
            return 5 * countX(5, roll.getDice());
        } else if (category == Category.SIXES) {
            return 6 * countX(6, roll.getDice());
        } else if (category == Category.PAIR) {
            return 2 * highestSetOfAtLeastX(2, roll.getDice(), 6);
        } else if (category == Category.TWO_PAIRS) {
            int pair1 = highestSetOfAtLeastX(2, roll.getDice(), 6);
            int pair2 = highestSetOfAtLeastX(2, roll.getDice(), pair1 - 1);
            if (pair1 != 0 && pair2 != 0) {
                return 2 * (pair1 + pair2);
            } else {
                return 0;
            }
        } else if (category == Category.THREE_OF_A_KIND) {
            return 3 * highestSetOfAtLeastX(3, roll.getDice(), 6);
        } else if (category == Category.FOUR_OF_A_KIND) {
            return 4 * highestSetOfAtLeastX(4, roll.getDice(), 6);
        } else if (category == Category.SMALL_STRAIGHT) {
            if (rollEquals(roll.getDice(), new int[]{1, 2, 3, 4, 5})) {
                return 15;
            } else {
                return 0;
            }
        } else if (category == Category.LARGE_STRAIGHT) {
            if (rollEquals(roll.getDice(), new int[]{2, 3, 4, 5, 6})) {
                return 20;
            } else {
                return 0;
            }
        } else if (category == Category.FULL_HOUSE) {
            int pair = highestSetOfExactlyX(2, roll.getDice(), 6);
            int threeOfAKind = highestSetOfExactlyX(3, roll.getDice(), 6);
            if (pair != 0 && threeOfAKind != 0) {
                return 2 * pair + 3 * threeOfAKind;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private int sumAllDice(int[] rolls) {
        int sum = 0;
        for (int i : rolls) {
            sum += i;
        }
        return sum;
    }

    private boolean yatzy(int[] rolls) {
        int side = rolls[0];
        for (int i : rolls) {
            if (i != side) {
                return false;
            }
        }
        return true;
    }

    private int countX(int x, int[] rolls) {
        int sum = 0;
        for (int i : rolls) {
            if (i == x)
                sum += 1;
        }
        return sum;
    }

    private int highestSetOfAtLeastX(int x, int[] rolls, int limit) {
        for (int i = limit; i >= 0; i--) {
            if (countX(i, rolls) >= x) {
                return i;
            }
        }
        return 0;
    }

    private int highestSetOfExactlyX(int x, int[] rolls, int limit) {
        for (int i = limit; i > 0; i--) {
            if (countX(i, rolls) == x) {
                return i;
            }
        }
        return 0;
    }

    private boolean rollEquals(int[] roll1, int[] roll2) {
        Arrays.sort(roll1);
        Arrays.sort(roll2);
        return (Arrays.equals(roll1, roll2));
    }
}
