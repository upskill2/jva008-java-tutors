package com.luxoft.jva008.module01;

import org.junit.Assert;
import org.junit.Test;

import static com.luxoft.jva008.Logger.log;

public class NumbersTutor {

    public Double d = 123d;
    public Integer i = 23545452;

    @Test
    public void testConvertNumbers() {
        log("=== testConvertNumbers() ===");
        covertNumbers(i);
        covertNumbers(123d);
        covertNumbers(1239879634342l);
    }

    /**
     * Conversion between numeric data types
     *
     * @param n
     */
    public void covertNumbers(Number n) {
        // TODO your code goes here

        short sh = n.shortValue();
        float fl = n.floatValue();
        byte bt = n.byteValue();
        int in = n.intValue();
        long l = n.longValue();
        double db = n.doubleValue();


    }

    /**
     * Conversion from String type to various numeric data types
     *
     * @param s
     */
    public void stringToNumber(String s) {
        // TODO your code goes here

        if (Float.valueOf(s) > Integer.MAX_VALUE) {
            long lg = Long.valueOf(s);

        }
        float flt = Float.valueOf(s);


    }

    @Test
    public void testStringToNumber() {
        stringToNumber("123");
        stringToNumber("-123");
        stringToNumber("12345678987654321");
        stringToNumber("1.11f");
        stringToNumber("1.1111111111");
    }

    /**
     * Getting  NaN value for Double type
     */
    @Test
    public void testIsNaN() {
        Assert.assertTrue(Double.isNaN(Math.sqrt(-4)));
    }

    /**
     * Getting Infinite value for Double type
     */
    @Test
    public void testIsInfinite() {
        Assert.assertTrue(Double.isInfinite(Double.MAX_VALUE * 2));
    }

}
