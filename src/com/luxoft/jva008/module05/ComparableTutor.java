package com.luxoft.jva008.module05;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;

import java.util.*;
import java.util.stream.Stream;

import org.junit.Test;

/**
 *	Implement method Set<Animal> getAnimalsOrderedByNameSet()
 * 	and method Set<Animal> getAnimalsOrderedByNameSetDesc()
 */

public class ComparableTutor {
	String [] animals =
        {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};

    class Animal{
        String name;
        public Animal(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return name;
        }

        public String getName() {
            return name;
        }

    }

    /**
	 * Method should return a Set of Animal instances, alphabetically sorted
	 * Use TreeSet for that and implement Comparable interface in Animal class.
     */
    public Set<Animal> getAnimalsOrderedByNameSet() {

        Set<Animal> set = new TreeSet<>(new AnimalComp());

        for (String an : animals) {
            set.add(new Animal(an));

        }


        return set;
    }

    /**
	 * Method should return a Set of Animal instances,
	 * ordered by the name in reverse order.
	 * Use TreeSet for that and pass a Comparator implementation as a parameter.
     *
     */
    public Set<Animal> getAnimalsOrderedByNameSetDesc() {

        Set<Animal> set = new TreeSet<>(new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.name.compareTo(o2.name) * -1;
            }
        });

        for (String an : animals) {
            set.add(new Animal(an));

        }

        return set;
    }

    public class AnimalComp implements Comparator<Animal>{
        public int compare(Animal o1, Animal o2) {
            return o1.name.compareTo(o2.name);
        }
    }



    public String joinByCycle(Collection<?> c) {
        if (c == null) {
        	return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Object s: c) {
            builder.append(s);
            if (builder.length()>0) builder.append(", ");
        }
        return builder.toString();
    }

    @Test
    public void testCollections() {
        log("getAnimalsList: " + joinByCycle(Arrays.asList(animals)));

        log("getAnimalsOrderedByNameSet: " + joinByCycle(getAnimalsOrderedByNameSet()));
        log("getAnimalsOrderedByNameSetDesc: " + joinByCycle(getAnimalsOrderedByNameSetDesc()));
    }

    @Test
    public void getAnimalsOrderedByNameSet_default_returnsSortedSet() {
        Set<Animal> set = getAnimalsOrderedByNameSet();
        String join = joinByCycle(set);
        assertEquals("Cat, Chicken, Cow, Dog, Elephant, Goose, Horse, Human, Rabbit, Snake, ", join);
    }

    @Test
    public void getAnimalsOrderedByNameSetDesc_default_returnsSortedSet() {
        Set<Animal> set = getAnimalsOrderedByNameSetDesc();
        String join = joinByCycle(set);
        assertEquals("Snake, Rabbit, Human, Horse, Goose, Elephant, Dog, Cow, Chicken, Cat, ", join);
    }
}
