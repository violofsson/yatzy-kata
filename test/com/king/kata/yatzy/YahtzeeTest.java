package com.king.kata.yatzy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class YahtzeeTest {
    private YahtzeeScorer scorer;

    @Before
    public void setUp() {
        scorer = new YahtzeeScorer();
    }

    @Test(expected = IllegalArgumentException.class)
    public void noDiceOverSix() {
        new YahtzeeRoll(2, 5, 4, 7, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noDiceUnderOne() {
        new YahtzeeRoll(0, 1, 2, 3, 4);
    }

    @Test
    public void chanceScoresCorrectly() {
        Assert.assertEquals(18, scorer.calculateScore(Category.CHANCE, new YahtzeeRoll(5, 2, 1, 4, 6)));
        Assert.assertEquals(14, scorer.calculateScore(Category.CHANCE, new YahtzeeRoll(1, 1, 3, 3, 6)));
        Assert.assertEquals(21, scorer.calculateScore(Category.CHANCE, new YahtzeeRoll(4, 5, 5, 6, 1)));
    }

    @Test
    public void yahtzeeScoresCorrectly() {
        Assert.assertEquals(50, scorer.calculateScore(Category.YAHTZEE, new YahtzeeRoll(1, 1, 1, 1, 1)));
    }

    @Test
    public void onesToSixesScoreCorrectly() {
        Assert.assertEquals(2, scorer.calculateScore(Category.ONES, new YahtzeeRoll(1, 1, 2, 4, 4)));
        Assert.assertEquals(4, scorer.calculateScore(Category.TWOS, new YahtzeeRoll(2, 3, 2, 5, 1)));
        Assert.assertEquals(9, scorer.calculateScore(Category.THREES, new YahtzeeRoll(3, 3, 3, 4, 6)));
        Assert.assertEquals(12, scorer.calculateScore(Category.FOURS, new YahtzeeRoll(4, 4, 2, 4, 1)));
        Assert.assertEquals(15, scorer.calculateScore(Category.FIVES, new YahtzeeRoll(5, 5, 2, 5, 1)));
        Assert.assertEquals(0, scorer.calculateScore(Category.SIXES, new YahtzeeRoll(2, 3, 2, 5, 1)));
    }

    @Test
    public void singlePairScoresCorrectly() {
        Assert.assertEquals(8, scorer.calculateScore(Category.PAIR, new YahtzeeRoll(4, 4, 1, 3, 5)));
        Assert.assertEquals(8, scorer.calculateScore(Category.PAIR, new YahtzeeRoll(4, 4, 4, 3, 5)));
        Assert.assertEquals(10, scorer.calculateScore(Category.PAIR, new YahtzeeRoll(1, 1, 5, 5, 6)));
        Assert.assertEquals(0, scorer.calculateScore(Category.PAIR, new YahtzeeRoll(1, 2, 3, 4, 6)));
    }

    @Test
    public void twoPairsScoreCorrectly() {
        Assert.assertEquals(8, scorer.calculateScore(Category.TWO_PAIRS, new YahtzeeRoll(1, 1, 2, 3, 3)));
        Assert.assertEquals(0, scorer.calculateScore(Category.TWO_PAIRS, new YahtzeeRoll(1, 1, 2, 3, 4)));
        Assert.assertEquals(6, scorer.calculateScore(Category.TWO_PAIRS, new YahtzeeRoll(1, 1, 2, 2, 2)));
    }

    @Test
    public void threeOfAKindScoresCorrectly() {
        Assert.assertEquals(3, scorer.calculateScore(Category.THREE_OF_A_KIND, new YahtzeeRoll(1, 1, 1, 4, 6)));
        Assert.assertEquals(9, scorer.calculateScore(Category.THREE_OF_A_KIND, new YahtzeeRoll(3, 3, 3, 5, 6)));
    }

    @Test
    public void fourOfAKindScoresCorrectly() {
        Assert.assertEquals(8, scorer.calculateScore(Category.FOUR_OF_A_KIND, new YahtzeeRoll(2, 2, 2, 2, 5)));
        Assert.assertEquals(0, scorer.calculateScore(Category.FOUR_OF_A_KIND, new YahtzeeRoll(2, 2, 2, 5, 5)));
        Assert.assertEquals(8, scorer.calculateScore(Category.FOUR_OF_A_KIND, new YahtzeeRoll(2, 2, 2, 2, 2)));
    }

    @Test
    public void straightsScoreCorrectly() {
        Assert.assertEquals(15, scorer.calculateScore(Category.SMALL_STRAIGHT, new YahtzeeRoll(1, 2, 3, 5, 4)));
        Assert.assertEquals(20, scorer.calculateScore(Category.LARGE_STRAIGHT, new YahtzeeRoll(4, 3, 2, 6, 5)));
        Assert.assertEquals(0, scorer.calculateScore(Category.SMALL_STRAIGHT, new YahtzeeRoll(3, 4, 2, 1, 6)));
    }

    @Test
    public void fullHouseScoresCorrectly() {
        Assert.assertEquals(8, scorer.calculateScore(Category.FULL_HOUSE, new YahtzeeRoll(1, 1, 2, 2, 2)));
        Assert.assertEquals(0, scorer.calculateScore(Category.FULL_HOUSE, new YahtzeeRoll(2, 2, 3, 3, 4)));
        Assert.assertEquals(0, scorer.calculateScore(Category.FULL_HOUSE, new YahtzeeRoll(4, 4, 4, 4, 4)));
    }
}
