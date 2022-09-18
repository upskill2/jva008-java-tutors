package com.luxoft.jva008.module02;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;

public class DataStreamTutor {
    private static final String FILES_TEST_PATH = "files/test.txt";
    private static final String TEST_LINE = "test line";

    /**
     * Writes a string TEST_LINE to the file FILES_TEST_PATH, using
     * method writeUTF of class DataOutputStream.
     * Also use BufferedOutputStream for buffering.
     * Then close the stream.
     */
    public void writeToFile() {

        try (FileOutputStream fos = new FileOutputStream(FILES_TEST_PATH);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             DataOutputStream dis = new DataOutputStream(bos)) {

            dis.writeUTF(TEST_LINE);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Reads a line from a file using the method readUTF and returns it.
     *
     * @return
     */
    public String readFromFile() {
        String res = "";
        Charset charset = StandardCharsets.UTF_8;

        try (FileInputStream fis = new FileInputStream(FILES_TEST_PATH);
             BufferedInputStream bis = new BufferedInputStream(fis);
             DataInputStream dis = new DataInputStream(bis)) {

         res =   dis.readUTF();


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
