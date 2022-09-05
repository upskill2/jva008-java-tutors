package com.luxoft.jva008.module01;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static com.luxoft.jva008.Logger.log;

public class OverflowTutor {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    /**
     * Multiply 2 numbers, excite ArithmeticException in case of overflow
     */
    public Integer multiply(int a, int b) {


        int res = a * b;


        if (Double.isNaN(Math.multiplyExact(a, b))) {
            throw new ArithmeticException("i1*i2=");
        }
        return res;


    }

    @Test(expected = ArithmeticException.class)
    public void testOverflow() {
        int i1 = 34524235;
        int i2 = 23423423;
        int overflowed = i1 * i2;
        log("i1*i2=" + overflowed);
        this.multiply(i1, i2);
    }

    @Test
    public void testSum() {
        Double d = Double.POSITIVE_INFINITY * 10;
        log("doubleMultiply=" + d);
    }

}
