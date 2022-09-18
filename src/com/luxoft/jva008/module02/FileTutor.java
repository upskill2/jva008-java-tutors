package com.luxoft.jva008.module02;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class FileTutor {
	
	/**
	 * The method must create a folder "test" and a file "test.txt" inside it 
	 * Also, output in the full path to the log file you have created
	 */
	public void createFile() throws IOException {

		String text = "D:\\IdeaProjects\\jva008-java-tutors\\files\\test1.txt";
		Path textFilePath = Paths.get(text);
		Files.createFile(textFilePath);

		File file1 = new File("D:\\IdeaProjects\\jva008-java-tutors\\files\\test2.txt");
		file1.createNewFile();


	}
	
	/**
	 * This method should remove the "test" folder and the "test/test.txt" file
	 */
	public void deleteFile() {

		File file
				= new File("D:\\IdeaProjects\\jva008-java-tutors\\files\\test1.txt");

		file.delete();
	}
	
	
	@Test
	public void testCreateFile() throws IOException {
		createFile();
		File f = new File("files/test1.txt");
		assertTrue(f.exists());
	}
	
	@Test
	public void testDeleteFile() {
		deleteFile();
		File f = new File("test/test.txt");
		assertFalse(f.exists());
		assertFalse(new File("test").exists());
	}

}
