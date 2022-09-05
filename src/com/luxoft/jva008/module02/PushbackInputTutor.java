package com.luxoft.jva008.module02;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

import org.junit.Test;

public class PushbackInputTutor {

	@Test
	public void testPushbackInputStream() {

		try(PushbackInputStream input = new PushbackInputStream(new FileInputStream("files/input.txt"))) {
			for (int i = 0; i < 5; i++) {
				int data = input.read();
				input.unread(data);

				int rereadData = input.read();
				assertEquals(data, rereadData);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
