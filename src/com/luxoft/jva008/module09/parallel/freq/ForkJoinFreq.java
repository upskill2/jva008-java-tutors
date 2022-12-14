package com.luxoft.jva008.module09.parallel.freq;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

/**
 * Class should create a map animalFrequency:
 * animal -> frequency
 *
 * It should be done in 3 ways:
 * - using Fork/Join algorithm
 * - using map/reduce (use collect() method)
 * - using Collection.frequency()
 */
public class ForkJoinFreq {
    static ForkJoinPool fjPool = new ForkJoinPool();
    static long ARRAY_SIZE = 10000;
    Map<String, Integer> animalFrequency = new HashMap<>();
    
	interface FreqCalculator {
		Map<String, Integer> getFrequences(String[] array);
	}

    public static void benchmark(String name, String[] array, FreqCalculator f) {
		long start = System.currentTimeMillis();
		f.getFrequences(array);
		long end = System.currentTimeMillis();
		long time = end-start;
		System.out.println(name+": time="+time+"ms");
	}
    
    public static String getRandomAnimal() {
    	return null;
    }

	public static void main(String[] args) {
		// generate array with random animals
		String[] array = Stream
				.generate(ForkJoinFreq::getRandomAnimal)
				.limit(ARRAY_SIZE)
				.toArray(String[]::new);
		
		/*
		benchmark("freqArrayForkJoin", array, ForkJoinFreq::freqArrayForkJoin);
		benchmark("freqArrayForkJoin", array, ForkJoinFreq::freqArrayForkJoin);
		benchmark("freqArrayForkJoin", array, ForkJoinFreq::freqArrayForkJoin);
		benchmark("freqArrayStream", array, ForkJoinFreq::freqArrayStream);
		benchmark("freqArrayStream", array, ForkJoinFreq::freqArrayStream);
		benchmark("freqArrayStream", array, ForkJoinFreq::freqArrayStream);
		benchmark("freqArrayStreamParallel", array, ForkJoinFreq::freqArrayStreamParallel);
		benchmark("freqArrayStreamParallel", array, ForkJoinFreq::freqArrayStreamParallel);
		benchmark("freqArrayStreamParallel", array, ForkJoinFreq::freqArrayStreamParallel);
		benchmark("freqArraySeq", array, ForkJoinFreq::freqArraySeq);
		benchmark("freqArraySeq", array, ForkJoinFreq::freqArraySeq);
		*/
	}
}
