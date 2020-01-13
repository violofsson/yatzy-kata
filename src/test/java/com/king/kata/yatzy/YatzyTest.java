package com.king.kata.yatzy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class YatzyTest {

    private YatzyScorer yatzyScorer;

    @Before
    public void setUp() {
        yatzyScorer = new YatzyScorer();
    }

    @Test(expected = IllegalArgumentException.class)
    public void noDiceOverSix() {
        new YatzyRoll(2, 5, 4, 7, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noDiceUnderOne() {
        new YatzyRoll(0, 1, 2, 3, 4);
    }

    @Test
    public void chanceScoresCorrectly() {
        int score = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(5, 2, 1, 4, 6));
        Assert.assertEquals(18, score);
        score = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(1, 1, 3, 3, 6));
        Assert.assertEquals(14, score);
        score = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(4, 5, 5, 6, 1));
        Assert.assertEquals(21, score);
    }

    @Test
    public void yatzyScoresCorrectly() {
        int yatzy = yatzyScorer.calculateScore(Category.YAHTZEE, new YatzyRoll(1, 1, 1, 1, 1));
        Assert.assertEquals(50, yatzy);

    }

    @Test
    public void onesToSixesScoreCorrectly() {
        int score = yatzyScorer.calculateScore(Category.ONES, new YatzyRoll(1, 1, 2, 4, 4));
        Assert.assertEquals(2, score);
        score = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(2, 3, 2, 5, 1));
        Assert.assertEquals(4, score);
        score = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(3, 3, 3, 4, 6));
        Assert.assertEquals(9, score);
        score = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(4, 4, 2, 4, 1));
        Assert.assertEquals(12, score);
        score = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(5, 5, 2, 5, 1));
        Assert.assertEquals(15, score);
        score = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(2, 3, 2, 5, 1));
        Assert.assertEquals(0, score);
    }

    @Test
    public void SinglePairScoresCorrectly() {
        int pairs = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(4, 4, 1, 3, 5));
        Assert.assertEquals(8, pairs);
        pairs = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(4, 4, 4, 3, 5));
        Assert.assertEquals(8, pairs);
        pairs = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(1, 1, 5, 5, 6));
        Assert.assertEquals(10, pairs);
        pairs = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(1, 2, 3, 4, 6));
        Assert.assertEquals(0, pairs);
    }

    @Test
    public void twoPairsScoreCorrectly() {
        int score = yatzyScorer.calculateScore(Category.TWO_PAIRS, new YatzyRoll(1, 1, 2, 3, 3));
        Assert.assertEquals(8, score);
        score = yatzyScorer.calculateScore(Category.TWO_PAIRS, new YatzyRoll(1, 1, 2, 3, 4));
        Assert.assertEquals(0, score);
        score = yatzyScorer.calculateScore(Category.TWO_PAIRS, new YatzyRoll(1, 1, 2, 2, 2));
        Assert.assertEquals(6, score);
    }

    @Test
    public void threeOfAKindScoresCorrectly() {
        int score = yatzyScorer.calculateScore(Category.THREE_OF_A_KIND, new YatzyRoll(1, 1, 1, 4, 6));
        Assert.assertEquals(3, score);
        score = yatzyScorer.calculateScore(Category.THREE_OF_A_KIND, new YatzyRoll(3, 3, 3, 5, 6));
        Assert.assertEquals(9, score);
    }

    @Test
    public void fourOfAKindScoresCorrectly() {
        int score = yatzyScorer.calculateScore(Category.FOUR_OF_A_KIND, new YatzyRoll(2, 2, 2, 2, 5));
        Assert.assertEquals(8, score);
        score = yatzyScorer.calculateScore(Category.FOUR_OF_A_KIND, new YatzyRoll(2, 2, 2, 5, 5));
        Assert.assertEquals(0, score);
        score = yatzyScorer.calculateScore(Category.FOUR_OF_A_KIND, new YatzyRoll(2, 2, 2, 2, 2));
        Assert.assertEquals(8, score);
    }

    @Test
    public void straightsScoreCorrectly() {
        int score = yatzyScorer.calculateScore(Category.SMALL_STRAIGHT, new YatzyRoll(1, 2, 3, 5, 4));
        Assert.assertEquals(15, score);
        score = yatzyScorer.calculateScore(Category.LARGE_STRAIGHT, new YatzyRoll(4, 3, 2, 6, 5));
        Assert.assertEquals(20, score);
        score = yatzyScorer.calculateScore(Category.SMALL_STRAIGHT, new YatzyRoll(3, 4, 2, 1, 6));
        Assert.assertEquals(0, score);
    }

    @Test
    public void fullHouseScoresCorrectly() {
        int score = yatzyScorer.calculateScore(Category.FULL_HOUSE, new YatzyRoll(1, 1, 2, 2, 2));
        Assert.assertEquals(8, score);
        score = yatzyScorer.calculateScore(Category.FULL_HOUSE, new YatzyRoll(2, 2, 3, 3, 4));
        Assert.assertEquals(0, score);
        score = yatzyScorer.calculateScore(Category.FULL_HOUSE, new YatzyRoll(4, 4, 4, 4, 4));
        Assert.assertEquals(0, score);
    }
}
