package com.luxoft.jva008.module09;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Try to get default value using 
 * - opt.orElse(0);
 * - opt.orElseThrow
 * - opt.orElseGet
 * Add some elements to the array.
 *
 */
public class OptionalTutor {

    final static int RESULT= 25;

    public static void main(String... args) {
        
        List<Integer> list = Arrays.asList();
        
        Optional<Integer> opt = 
	        list.stream()
	                .reduce(Integer::max);
        System.out.println("max = " + opt);


        int task1 = opt.orElse(25);
        System.out.println(task1);

        try{

            opt.orElseThrow(()->new NullPointerException("Is empty"));
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        int task3 = opt.orElseGet(OptionalTutor:: defaultMethod);
        System.out.println("Task3 " + task3);

    }
    public static int defaultMethod(){
        return 16;
    }
}
