package com.luxoft.jva008.module02;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class SerializableTutor {

    private static final String FILE_OBJECT_DATA = "files/object.data";
    private static Calendar dayOfBirth;

    static {
        dayOfBirth = Calendar.getInstance();
        dayOfBirth.set(Calendar.YEAR, 2000);
        dayOfBirth.set(Calendar.MONTH, Calendar.OCTOBER);
        dayOfBirth.set(Calendar.DAY_OF_MONTH, 10);
    }

    /**
     * Make the class static, implement Serializable
     * and mark the field age as transient
     */
    static class Person implements Serializable {
        private transient String name;
        private Date birthdate;
        private List<Address> addressList = new ArrayList<Address>();
        transient private int age;

        private Person(String name, Date birthdate) {
            this.name = name;
            this.birthdate = birthdate;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthdate);
            age = Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        }

        public void addAddress(Address address) {
            addressList.add(address);
        }

    }


    /**
     * Make the class static and implement Serializable
     */
    static class Address implements Serializable {
        private String city;
        private String street;
        private int appartement;

        private Address(String city, String street, int appartement) {
            this.city = city;
            this.street = street;
            this.appartement = appartement;
        }

    }

    /**
     * Should write the data of Person to file FILE_OBJECT_DATA,
     * using ObjectOutputStream
     *
     * @param person
     */
    public void writeToFile(Person person) {

        try (FileOutputStream fos = new FileOutputStream(FILE_OBJECT_DATA);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(person);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Should read and return data from file FILE_OBJECT_DATA
     */
    public Person readFromFile() {
        Person person = null;
        try (FileInputStream fis = new FileInputStream(FILE_OBJECT_DATA);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            person = (Person) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return person;
    }

    @Test
    public void testSerializeObject() {
        Person person = new Person("John Johnes", dayOfBirth.getTime());
        writeToFile(person);
        log("Age: " + person.age);
        Person personFromFile = readFromFile();
        log("Name from file: " + personFromFile.name);
        log("Age from file: " + personFromFile.age);
        assertEquals(personFromFile.name, personFromFile.name);
        assertNotEquals("Name age was not marked as transient", person.age, personFromFile.age);
    }

    @Test
    public void testSerializePersonAddress() {
        Person person = new Person("John Johnes", dayOfBirth.getTime());
        Address address = new Address("New York", "Water street", 10);
        person.addAddress(address);

        writeToFile(person);

        log("Age: " + person.age);
        Person personFromFile = readFromFile();
        log("Name from file: " + personFromFile.name);
        log("Age from file: " + personFromFile.age);
        assertEquals(personFromFile.name, personFromFile.name);
        assertNotEquals(person.age, personFromFile.age);

        Address addressFromFile = personFromFile.addressList.get(0);
        log("City from file: " + addressFromFile.city);
        log("Appartment from file: " + addressFromFile.appartement);
        assertEquals(addressFromFile.city, address.city);
        assertEquals(addressFromFile.appartement, address.appartement);
    }

}
