package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({test.TestCacheList.class, test.TestCacheToFile.class, 
    test.TestCacheRequest.class, test.TestCacheLog.class, test.TestProxy.class})
public class TestCacheSuite {
    
}
