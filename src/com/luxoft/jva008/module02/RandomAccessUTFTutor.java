package com.luxoft.jva008.module02;

import static org.junit.Assert.assertEquals;

import java.io.*;

import org.junit.Before;
import org.junit.Test;

public class RandomAccessUTFTutor {
	
	private static final String FILES_TEST_PATH = "files/test.txt";

	/**
    * Write to the file FILES_TEST_PATH 2 UTF lines:
    * "test line" and "test line 2"
    */
	public void randomAccessWriteUTF() throws IOException {

		RandomAccessFile file = new RandomAccessFile(FILES_TEST_PATH,"rw");

		file.writeUTF("test line");
		file.writeUTF("test line 2");

	}
	
	/**
    * Jump to the second line of the UTF, using seek,
    * read and return its value
    * @return
	*/
	public String randomAccessReadUTF() throws IOException {
		RandomAccessFile file = new RandomAccessFile(FILES_TEST_PATH,"rw");

		file.seek(13);

		String res = file.readLine();


		return res;
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
	public void testRandomAccessUTF() throws IOException {
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
