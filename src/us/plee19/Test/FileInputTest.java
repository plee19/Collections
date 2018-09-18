package us.plee19.Test;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import us.plee19.FileInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FileInputTest extends TestCase {

    FileInput inputTest;
    private final ByteArrayOutputStream outStuff = new ByteArrayOutputStream();
    private final PrintStream original = System.out;

    @Before
    public void setUp() throws Exception {
        inputTest = new FileInput("FileInputTest.txt");
        System.setOut(new PrintStream(outStuff));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(original);
    }

    @Test
    public void testFileRead() {
        inputTest.fileRead();
        assertEquals("test|testStuff\n",outStuff.toString());
    }

    @Test
    public void testFileReadLine() {
        assertEquals("test|testStuff", inputTest.fileReadLine());
    }

    @Test
    public void testFileClose() {
        inputTest.fileClose();
        assertNull(inputTest.fileReadLine());
    }

    @Test
    public void testFileInputError() {
        String noFile = "error.txt";
        new FileInput(noFile);
        assertEquals("File Open Error: error.txt java.io.FileNotFoundException: " + noFile + " (The system cannot find the file specified)\n", outStuff.toString());
    }
}