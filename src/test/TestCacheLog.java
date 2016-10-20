package test;

import main.CacheLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class TestCacheLog {
    
    public TestCacheLog() {
    }

    @Test
    public void testCacheLog() {
        CacheLog cache = new CacheLog("checkout/testData/");
        cache.openLogForAppend();
        
        cache.logHit("www.my_test.com");
        cache.logMiss("www.my_test.com");
        cache.logRemoval("www.my_test.com");
        
        try {
            //read file for make sure info saved
            Scanner input = new Scanner(new File("checkout/testData/output.log"));
            int numFound = 0;
            while (input.hasNextLine()){
                String line = input.nextLine();
                if (line.contains("www.my_test.com cache hit")){
                    numFound++;
                }else if (line.contains("www.my_test.com the cached page is evicted")){
                    numFound++;
                }else if (line.contains("www.my_test.com cache miss")){
                    numFound++;
                }
            }
            input.close();
            
            assertTrue(numFound >= 3);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestCacheLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
