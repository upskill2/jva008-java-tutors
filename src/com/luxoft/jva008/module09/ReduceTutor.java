package com.luxoft.jva008.module09;

import java.util.Comparator;
import java.util.stream.Stream;

public class ReduceTutor {

    public static Stream<String> getStream() {
        return Stream.of("John Lennon", "Paul Mccartney",
                "George Harrison", "Ringo Star");
    }

    /**
     * Find the longest text in the stream in 2 ways and print it:
     * - Using getStream().reduce()
     */
    public static void main(String[] args) {

        getStream().reduce((w1, w2) -> w1.length() > w2.length() ? w1 : w2).ifPresent(System.out::println);
        getStream().max(Comparator.comparingInt(String::length)).ifPresent(s -> System.out.println(s));

        //print int
        getStream().mapToInt(String::length).reduce((s1, s2) -> s1 > s2 ? s1 : s2).ifPresent(p -> System.out.println(p));

    }

}
