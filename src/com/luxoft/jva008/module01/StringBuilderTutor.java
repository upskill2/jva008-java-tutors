package com.luxoft.jva008.module01;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StringBuilderTutor {

	String[] animals = {"Cow", "Goose", "Cat", "Dog", "Elephant", "Hare", "Snake", "Chicken", "Horse", "Man"};

	/**
	 * The method should return a string:
	 * "List of animals: Cow, Goose, ..., Man."
	 * For implementation, use StringBuilder
	 */
	public String getAnimalsString() {
		StringBuilder sb = new StringBuilder("List of animals:");

		for (String animal: animals) {
			sb.append(" " + animal + ",");

		}

		sb.replace(sb.length()-1, sb.length(), ".");


		return sb.toString();
	}

	@Test
	public void testGetAnimalsString() {
		String animalsString = getAnimalsString();
		assertEquals("List of animals: Cow, Goose, Cat, Dog, Elephant, Hare, Snake, Chicken, Horse, Man.", animalsString);
	}

}
