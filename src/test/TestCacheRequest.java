package test;

<<<<<<< HEAD
import cs1.CacheRequest;
=======
import main.CacheRequest;
>>>>>>> lesterBranch2
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
<<<<<<< HEAD
        CacheRequest cache = new CacheRequest("data/");
=======
        CacheRequest cache = new CacheRequest("/Users/Jake/Documents/workspace/lru/src/");
>>>>>>> lesterBranch2
        assertEquals(cache.read(), "www.google.com");
        assertEquals(cache.read(), "www.yahoo.com");
        assertEquals(cache.read(), "www.wikipedia.com");
    }
}
