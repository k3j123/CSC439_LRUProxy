package test;

import main.CacheToFile;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class TestCacheToFile {
    
    public TestCacheToFile() {
    }
     
    @Test
    public void testCacheToFile() {
        CacheToFile cache = new CacheToFile("checkout/testData/");
        StringBuffer sb = new StringBuffer();
        sb.append("content of www.test.com");
        cache.write("www.test.com", sb);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
      
        cache.read("www.test.com");
        
        String expectedOutput  = "content of www.test.com";
        // Do the actual assertion.
        assertEquals(expectedOutput, outContent.toString());
        
        //check is Cached
        assertTrue(cache.isCached("www.test.com"));
        
        cache.remove("www.test.com");
        
        //check cached after removed
        assertFalse(cache.isCached("www.test.com"));
    }
}
