package test;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Test;
import main.*;
public class TestCacheToFile {
private String URL = "www.test.com";
private String directory = "checkout/testData/";
private String code = "<h1>Testing!<\\h1>";
// Test the write method to see if it creates the file
@Test
public void testWriteForExists() throws IOException{
CacheToFile thisFile = new CacheToFile(directory);
System.out.println("Testing write() method");
String fileName = "./src/test/data/" + URL;
StringBuffer sb = new StringBuffer(code);
// Calling write method
thisFile.write(URL, sb);
// Check to see if the file exists
File file = new File(fileName);
assertTrue(file.exists());
thisFile.remove(URL);
}

// Test the write method to see if it actually writes to the file
@Test
public void testWrite() throws IOException{
CacheToFile thisFile = new CacheToFile(directory);
System.out.println("Testing write() method");
String fileName = "checkout/testData/www.test.com";
StringBuffer sb = new StringBuffer(code);
// Calling write method
thisFile.write(URL, sb);
// Check to see if the correct stuff was written to fileName
BufferedReader br = new BufferedReader(new FileReader(fileName));
String line = br.readLine();
assertEquals(line, code);
br.close();
thisFile.remove(URL);
}
@Test
public void testRead(){
CacheToFile thisFile = new CacheToFile(directory);
System.out.println("Testing read() method");
StringBuffer sb = new StringBuffer(code);
// Calling write method
thisFile.write(URL, sb);
// Create a stream to hold the output
ByteArrayOutputStream baos = new ByteArrayOutputStream();
PrintStream ps = new PrintStream(baos);
// IMPORTANT: Save the old System.out!
PrintStream old = System.out;
// Tell Java to use your special stream
System.setOut(ps);
// Anything printed here will be sent to a string
StringBuffer actual = thisFile.read(URL);
// Put things back
System.out.flush();
System.
   setOut(old);
// Assert the two strings in baos and code
assertEquals(code.toString(), actual.toString());
thisFile.remove(URL);
}
@Test
public void testIsCached(){
CacheToFile thisFile = new CacheToFile(directory);
StringBuffer sb = new StringBuffer(code);
// Calling write method
thisFile.write(URL, sb);
System.out.println("Testing isCached() method");
assertTrue(thisFile.isCached(URL));
thisFile.remove(URL);
}
@Test
public void testRemove(){
System.out.println("Testing remove()");
CacheToFile thisFile = new CacheToFile(directory);
StringBuffer sb = new StringBuffer(code);
// Calling write method
thisFile.write(URL, sb);
thisFile.remove(URL);
File file = new File(directory + URL);
assertFalse(file.exists());
}
// Method is private, can't test
/*@Test
public void testGenerateFilename(){
String expected = "http:..www.test.com";
String out = cache.generateFilename(URL);
}*/
}

