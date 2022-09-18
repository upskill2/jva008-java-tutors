package com.luxoft.jva008.module02;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;

public class FileStreamTutor {
    private static final String FILES_TEST_PATH = "files/test.txt";
    private static final String TEST_LINE = "test line";

    /**
     * Writes a string TEST_LINE to the file FILES_TEST_PATH ,
     * converting the string into array of bytes.
     * For the writing, use the class FileOutputStream.
     */
    public void writeToFile() {

        try {
            FileOutputStream fos = new FileOutputStream(FILES_TEST_PATH);
            byte[] byteArray = TEST_LINE.getBytes();

            fos.write(byteArray);

            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Reads a line from a file and returns it using FileInputStream.
     *
     * @return
     */
    public String readFromFile() {

        String res = "";
        StringBuilder sb = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(FILES_TEST_PATH)) {
            int content;
            while ((content = fis.read()) != -1) {
                sb.append((char) content);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    @Test
    public void testFileStream() {
        writeToFile();
        String s = readFromFile();
        assertEquals(TEST_LINE, s);
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

}
