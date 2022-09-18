package com.luxoft.jva008.module09;

import com.luxoft.jva008.module09.model.Gender;
import com.luxoft.jva008.module09.model.Person;
import com.sun.source.tree.UsesTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1) Create a map which prints amount of person of each age
 *  use stream.collect(Collectors.groupingBy(Function, Collectors.mapping(Function, Collectors.counting())))
 * 2) Create a map Gender->String with gender as keys and comma-separated names as values
 * 	use stream.collect(Collectors.groupingBy(Function, Collectors.mapping(Function, Collectors.joining(","))))
 * 3) Calculate and print the average age of females
 * 	use stream.filter(Predicate).mapToInt(Function).average()
 * 4) Create a map with genders as keys and average ages as values
 * 	use stream.collect(Collectors.groupingBy(Function, Collectors.averagingInt(Function))
 * 5) Print the list of persons in alphabetical order of names
 * 	use stream.sorted(Comparator.comparing(Function))
 * 6) Print the list of persons in order of gender, then name:
 * 	use stream.sorted(Comparator.comparing(Function).thenComparing(Function)
 *
 */
public class CollectorsTutor {

    public static void main(String... args)  {
        
        List<Person> persons = new ArrayList<>();
        
        try (
            BufferedReader reader = 
                new BufferedReader(
                    new InputStreamReader(
                        CollectorsTutor.class.getResourceAsStream("people.txt")));

            Stream<String> stream = reader.lines();
        ) {

            stream.map(line -> {
                String[] s = line.split(" ");
                Person p = new Person(s[0].trim(), Integer.parseInt(s[1]), s[2].trim());
                persons.add(p);
                return p;
            })
            .forEach(System.out::println);
                
            
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        
        // Find a person with a minimal age more than 20
        Optional<Person> opt = 
        persons.stream().filter(p -> p.getAge() >= 20)
                .min(Comparator.comparing(Person::getAge));
        System.out.println(opt);
        
        // Find a person with a maximal age 
        Optional<Person> opt2 = 
        		persons.stream().max(Comparator.comparing(Person::getAge));
        System.out.println(opt2);
        
        // Group persons by their ages 
        Map<Integer, String> map = 
        persons.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge, 
                                Collectors.mapping(
                                        Person::getName, 
                                        Collectors.joining(", ")
                                )
                        )
                );
        System.out.println(map);
 
        // 1) Create a map which prints amount of person of each age
        //  use stream.collect(Collectors.groupingBy(Function, Collectors.mapping(Function, Collectors.counting())))
        Map<Integer, Long> mapByAge = persons.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Function.identity(),Collectors.counting())));
        System.out.println("------mapByAge-------");
        System.out.println(mapByAge);
        
        // 2) Create a map Gender->String with gender as keys and comma-separated names as values
        // 	use stream.collect(Collectors.groupingBy(Function, Collectors.mapping(Function, Collectors.joining(","))))
       Map<Gender,String> mapByGender = persons.stream().collect(Collectors.groupingBy(Person::getGender,Collectors.mapping(Person::getName,Collectors.joining(" ,"))));
        System.out.println("------mapByAge-------");
        System.out.println(mapByGender);


        // 3) Calculate and print the average age of females
        // 	use stream.filter(Predicate).mapToInt(Function).average()
        double avFAge = persons.stream().filter(x->x.getGender().toString().equals("Female")).mapToInt(Person::getAge).average().getAsDouble();
        System.out.println("------avFAge-------");
        System.out.println(avFAge);
        
        // 4) Create a map with genders as keys and average ages as values
        // 	use stream.collect(Collectors.groupingBy(Function, Collectors.averagingInt(Function))
        Map<Gender,Double> mapByGenderAndAv = persons.stream().collect(Collectors.groupingBy(Person::getGender, Collectors.averagingInt(Person::getAge)));
        System.out.println("------avGenderAge-------");
        System.out.println(mapByGenderAndAv);

        
        // 5) Print the list of persons in alphabetical order of names
        // 	use stream.sorted(Comparator.comparing(Function))
        System.out.println("------listAlphabet-------");
        persons.stream().sorted(Comparator.comparing(Person::getName)).forEach(System.out::println);
        
        
        // 6) Print the list of persons in order of gender, then name:
        // 	use stream.sorted(Comparator.comparing(Function).thenComparing(Function)
        System.out.println("------listGenderOrder-------");
        persons.stream().sorted(Comparator.comparing(Person::getGender).thenComparing(Person::getName)).forEach(System.out::println);

    
    }
}
