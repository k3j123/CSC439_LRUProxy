package test;

import main.CacheRequest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class TestCacheRequest {
    
    public TestCacheRequest() {
    }

    @Test
    public void testCacheRequest() {
        CacheRequest cache = new CacheRequest("./data/");
        assertEquals(cache.read(), "www.google.com");
        assertEquals(cache.read(), "www.yahoo.com");
        assertEquals(cache.read(), "www.wikipedia.com");
    }
}
