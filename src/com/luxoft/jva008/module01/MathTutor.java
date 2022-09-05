package com.luxoft.jva008.module01;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MathTutor {

    /**
     * Returns a random age in the range from minAge to maxAge
     */
    public int getRandomAge(int minAge, int maxAge) {
        // TODO your implementation goes here
        double random = Math.random();

        int res = (int) (random * (maxAge - minAge + 1) + minAge);

        return res;
    }

    /**
     * Pythagorean theorem:
     * Calculates the hypotenuse to the nearest
     * 2nd decimal place,
     * For example, if the cathetus is 2 and 3, then the hypotenuse = 3.61
     *
     * @return
     */
    public double getHypotenuse(double cathetus1, double cathetus2) {

        // TODO your implementation goes here

        double result = Math.pow(cathetus1, 2);
        double result1 = Math.pow(cathetus2, 2);

        double res = Math.sqrt(result1 + result);

        BigDecimal bd = new BigDecimal(res).setScale(2, RoundingMode.HALF_UP);
        double newInput = bd.doubleValue();



        return newInput;
    }

    @Test
    public void testGetHypotenuse() {
        double hyp = getHypotenuse(2, 3);
        log(hyp);
        assertEquals(3.61, hyp, 0);
    }

    /**
     * Tests the fullness of the range MIN_AGE..MAX_AGE
     * And uniformity of distribution
     * (Each age should meet equally often)
     */
    @Test
    public void testRandomAge() {
        int ITERATIONS = 1000000;
        int MIN_AGE = 18;
        int MAX_AGE = 25;
        Map<Integer, Integer> occurences = new HashMap<Integer, Integer>();
        for (int i = 0; i < ITERATIONS; i++) {
            int age = getRandomAge(MIN_AGE, MAX_AGE);
            Integer occur = occurences.get(age);
            if (occur == null) occur = 0;
            occurences.put(age, occur + 1);
        }
        Set<Integer> ages = occurences.keySet();
        List<Integer> sortedAges = new ArrayList<Integer>(ages);
        Collections.sort(sortedAges);
        int minOccurences = 0, maxOccurences = 0;
        double mean = ITERATIONS / (MAX_AGE - MIN_AGE + 1);
        double varianceSum = 0;
        for (Integer age : sortedAges) {
            int o = occurences.get(age);
            minOccurences = Math.min(o, minOccurences);
            maxOccurences = Math.max(o, maxOccurences);
            int variance = (int) (o - mean);
            varianceSum += variance * variance;
            log("for age" + age + ":" + occurences.get(age) + "occurrences, variance =" + variance);
        }
        double deviation = Math.sqrt(varianceSum / sortedAges.size());
        log("Standard deviation =" + deviation);
        double uniformity = (maxOccurences - minOccurences) * 1d / ITERATIONS;
        log("Uniformity of age distribution: " + uniformity);
        assertEquals(MIN_AGE, sortedAges.get(0), 0);
        assertEquals(MAX_AGE, sortedAges.get(sortedAges.size() - 1), 0);
        assertTrue(uniformity < 0.2);
    }

}
