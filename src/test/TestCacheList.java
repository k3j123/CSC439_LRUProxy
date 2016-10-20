package test;

import main.CacheList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class TestCacheList {
    
    public TestCacheList() {
    }

    @Test
    public void testCacheList() {
        CacheList cache = new CacheList("../data/", 3);
        
        cache.addNewObject("site1.com", false);
        cache.addNewObject("site2.com", false);
        cache.addNewObject("site3.com", false);
        String url = cache.addNewObject("site4.com", false);
        
        assertEquals("site1.com", url);
    }
}
