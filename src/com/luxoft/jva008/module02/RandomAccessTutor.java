package com.luxoft.jva008.module02;

import static org.junit.Assert.assertEquals;

import java.io.*;

import org.junit.Before;
import org.junit.Test;

public class RandomAccessTutor {

	private static final String FILES_TEST_PATH = "files/test.txt";

	/**
     * The method should open the file RandomAccessFile on path FILES_TEST_PATH
     * and write there the number 10 (type Integer), and then the string "test line"
     */
	public void randomAccessWrite() throws IOException {

		RandomAccessFile randomAccessFile = new RandomAccessFile(FILES_TEST_PATH,"rw");
		randomAccessFile.writeInt(10);
		randomAccessFile.writeChars("test line");
		randomAccessFile.close();

	}

	/**
     * The method should open the file RandomAccessFile on path FILES_TEST_PATH,
     * skip the number 10 and the word "test" (without reading them),
     * jump to the fifth letter, read and return the string "line"
     */
	public String randomAccessRead() throws IOException {

		try(	RandomAccessFile file = new RandomAccessFile(FILES_TEST_PATH,"r");){
			file.seek(14);
			String res =file.readLine();
			return res;

		} catch (IOException e){
			e.printStackTrace();
		}




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
	public void testRandomAccess() throws IOException {
		randomAccessWrite();
		String s = null;
		int i = -1;
		try {
			RandomAccessFile f = new RandomAccessFile(FILES_TEST_PATH, "r");
			i = f.readInt();
			s = f.readLine();
			f.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		assertEquals(i, 10);
		s = new String(s.getBytes("UTF-8"), "Unicode");
		assertEquals("test line", s);
		String read = randomAccessRead();
		read = new String(read.getBytes("UTF-8"), "Unicode");		
		assertEquals("line", read);
	}


}
