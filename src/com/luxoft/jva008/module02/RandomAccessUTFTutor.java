package com.luxoft.jva008.module02;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

public class RandomAccessUTFTutor {
	
	private static final String FILES_TEST_PATH = "files/test.txt";

	/**
    * Write to the file FILES_TEST_PATH 2 UTF lines:
    * "test line" and "test line 2"
    */
	public void randomAccessWriteUTF() {
	}
	
	/**
    * Jump to the second line of the UTF, using seek,
    * read and return its value
    * @return
	*/
	public String randomAccessReadUTF() {
		return null;
	}
	
	@Before
	public void createFile() {
		File f = new File(FILES_TEST_PATH);
		try {
			f.delete();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Test
	public void testRandomAccessUTF() throws UnsupportedEncodingException  {
		randomAccessWriteUTF();
		String s1 = null, s2 = null;
		try (RandomAccessFile f = new RandomAccessFile(FILES_TEST_PATH, "r")){
			s1 = f.readUTF();
			s2 = f.readUTF();
		} catch(IOException e) {
			e.printStackTrace();
		}
		assertEquals("test line", s1);
		assertEquals("test line 2", s2);
		String read = randomAccessReadUTF();
		assertEquals("test line 2", read);
	}


}
