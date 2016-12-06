package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.CacheList;

import static org.junit.Assert.*;

/**
 *
 * @author lamba
 */
public class TestCacheList {

    public TestCacheList() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addNewObject method, of class CacheList.
     */
    @Test
    public void testAddNewObjectLRU() {
        boolean hit = false;
        /**
         * Set replacement method to be used: 0 - Least Recently Used (LRU)
         * (default)
         */
        int replacementMethod = 0;
        //create cache hit with size =3
        CacheList instance = new CacheList(System.getProperty("user.dir") + "/src/data", 3);
        String expResult = "";
        //we have in cache : "www.google.com"
        String result = instance.addNewObject("www.google.com", hit, replacementMethod);
        assertEquals(expResult, result);

        //we have in cache : "www.google.com","www.yahoo.com"
        result = instance.addNewObject("www.yahoo.com", hit, replacementMethod);
        assertEquals(expResult, result);

        //we have in cache : "www.google.com","www.yahoo.com","www.facebook.com"
        result = instance.addNewObject("www.facebook.com", hit, replacementMethod);
        assertEquals(expResult, result);

        //we have in cache : "www.apple.com","www.yahoo.com","www.facebook.com"
        result = instance.addNewObject("www.apple.com", hit, replacementMethod);
        assertEquals("www.google.com", result);

        //we have in cache : "www.apple.com","www.wikipedia.com","www.facebook.com"
        result = instance.addNewObject("www.wikipedia.com", hit, replacementMethod);
        assertEquals("www.yahoo.com", result);
    }

    /**
     * Test of addNewObject method, of class CacheList.
     */
    @Test
    public void testAddNewObjectMRU() {
        boolean hit = false;
        /**
         * Set replacement method to be used: 1 - Most Recently Used (MRU)
         * (default)
         */
        int replacementMethod = 1;
        //create cache hit with size =3
        CacheList instance = new CacheList(System.getProperty("user.dir") + "/src/data", 3);
        String expResult = "";
        //we have in cache : "www.google.com"
        String result = instance.addNewObject("www.google.com", hit, replacementMethod);
        assertEquals(expResult, result);

        //we have in cache : "www.google.com","www.yahoo.com"
        result = instance.addNewObject("www.yahoo.com", hit, replacementMethod);
        assertEquals(expResult, result);

        //we have in cache : "www.google.com","www.yahoo.com"
        result = instance.addNewObject("www.yahoo.com", hit, replacementMethod);
        assertEquals(expResult, result);

        //we have in cache : "www.google.com","www.yahoo.com"
        result = instance.addNewObject("www.yahoo.com", hit, replacementMethod);
        assertEquals(expResult, result);

        //we have in cache : "www.google.com","www.yahoo.com","www.facebook.com"
        result = instance.addNewObject("www.facebook.com", hit, replacementMethod);
        assertEquals(expResult, result);

        //we have in cache : "www.google.com","www.yahoo.com","www.apple.com"
        result = instance.addNewObject("www.apple.com", hit, replacementMethod);
        assertEquals("www.facebook.com", result);

    }

    /**
     * Test of addNewObject method, of class CacheList.
     */
    @Test
    public void testAddNewObjectLFU() {
        boolean hit = false;
        /**
         * Set replacement method to be used: 2 - Least Frequently Used (LFU)
         */
        int replacementMethod = 2;
        //create cache hit with size =3
        CacheList instance = new CacheList(System.getProperty("user.dir") + "/src/data", 3);
        String expResult = "";

        String result = instance.addNewObject("www.yahoo.com", hit, replacementMethod);
        assertEquals(expResult, result);

        result = instance.addNewObject("www.google.com", hit, replacementMethod);
        assertEquals(expResult, result);

        result = instance.addNewObject("www.yahoo.com", hit, replacementMethod);
        assertEquals(expResult, result);

        result = instance.addNewObject("www.facebook.com", hit, replacementMethod);
        assertEquals(expResult, result);

        result = instance.addNewObject("www.yahoo.com", hit, replacementMethod);
        assertEquals("www.facebook.com", result);

        result = instance.addNewObject("www.apple.com", hit, replacementMethod);
        assertEquals("", result);

    }
    
     /**
     * Test of addNewObject method, of class CacheList.
     */
    @Test
    public void testAddNewObjectRR() {
        boolean hit = false;
        /**
         * Set replacement method to be used: 3 - Random Replacement (RR)
         */
        int replacementMethod = 3;
        //create cache hit with size =3
        CacheList instance = new CacheList(System.getProperty("user.dir") + "/src/data", 3);
        String expResult = "";

        String result = instance.addNewObject("www.yahoo.com", hit, replacementMethod);
        assertEquals(expResult, result);

        result = instance.addNewObject("www.google.com", hit, replacementMethod);
        assertEquals(expResult, result);

        result = instance.addNewObject("www.yahoo.com", hit, replacementMethod);
        assertEquals(expResult, result);

        result = instance.addNewObject("www.facebook.com", hit, replacementMethod);
        assertEquals(expResult, result);

        result = instance.addNewObject("www.yahoo.com", hit, replacementMethod);
        assertEquals(result, result);

        result = instance.addNewObject("www.apple.com", hit, replacementMethod);
        assertEquals(result, result);

    }
}
