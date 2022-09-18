package com.luxoft.jva008.module02;

import static org.junit.Assert.assertEquals;

import java.io.*;

import org.junit.Before;
import org.junit.Test;

public class ReaderWriterTutor {
    private static final String FILES_TEST_PATH = "files/test.txt";
    private static final String TEST_LINE = "test line";

    /**
     * Write line TEST_LINE to the file FILES_TEST_PATH, using
     * method write of class BufferedWriter.
     */
    public void writeToFile() {

        try (FileWriter fr = new FileWriter(FILES_TEST_PATH);
             BufferedWriter bw = new BufferedWriter(fr)) {

            bw.write(TEST_LINE);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Reads line from file by using method readLine()
     * of class BufferedReader and returns it
     *
     * @return
     */
    public String readFromFile() {

        String res = "";

        try (FileReader fr = new FileReader(FILES_TEST_PATH);
             BufferedReader bw = new BufferedReader(fr)) {

            res = bw.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Test
    public void testStream() {
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
