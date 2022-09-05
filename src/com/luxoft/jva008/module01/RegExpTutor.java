package com.luxoft.jva008.module01;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RegExpTutor {

	public Email getEmail(String emailString) {

		Email n = new Email();

		n.name = emailString.substring(0,emailString.indexOf("@"));
		n.domainName = emailString.substring(emailString.indexOf("@")+1,emailString.indexOf("."));
		n.domainZone = emailString.substring(emailString.indexOf(".")+1);

		return n;
	}

	/**
	 * Returns a list of strings according to the text representation of strings:
	 * Takes a string
	 * "List of animals: Cow, Goose, Cat, Dog, Elephant, Hare, Snake, Hen, Horse, Man."
	 * And returns an array of individual animals
	 */
	public String[] getAnimalsArray(String animalsString) {

		String str = animalsString.replace("List of animals: ", "");
		str = str.replace(".", "");
		str = str.replace(",", "");

		String[] array = str.split(" ");

		return array;
	}

	@Test
	public void testGetEmail() {
		Email email = getEmail("ivanov@mail.ru");
		assertEquals("ivanov", email.name);
		assertEquals("mail", email.domainName);
		assertEquals("ru", email.domainZone);

	}

	@Test
	public void testGetAnimalsArray() {
		String[] animals = {"Cow", "Goose", "Cat", "Dog", "Elephant", "Hare", "Snake", "Chicken", "Horse", "Man"};
		String animalsString = "List of animals: Cow, Goose, Cat, Dog, Elephant, Hare, Snake, Chicken, Horse, Man.";
		assertArrayEquals(animals, getAnimalsArray(animalsString));
	}

	class Email {
		String name;
		String domainName;
		String domainZone;


	}

}
