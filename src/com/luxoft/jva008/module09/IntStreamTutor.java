package com.luxoft.jva008.module09;

import org.junit.Test;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class IntStreamTutor {

    public static IntStream intStream() {
        return IntStream.of(2, 3, 3, 4);
    }

    public static void log(int i) {
        System.out.println(i);
    }

    public static void log(String i) {
        System.out.println(i);
    }


    /**
     * Find and print:
     * - max number in intStream
     * - average number in intStream
     * - list of distinct values: "2,3,4" (use distinct())
     */
    @Test
    public void testIntStream() {

        int max = intStream().max().getAsInt();
        log(max);
        assertEquals(max, 4);

        int avg = (int) intStream().average().orElse(0.0);
        log(avg);
        assertEquals(avg, 3);

        StringBuilder sb = new StringBuilder();
        String distinct = intStream().distinct().mapToObj(i -> ((Integer) i).toString()).collect(Collectors.joining(","));
        String distinct1 = intStream().distinct().boxed().map(String::valueOf).collect(Collectors.joining(","));
        log(distinct);
        assertEquals(distinct, "2,3,4");
        assertEquals(distinct1, "2,3,4");
    }

}
