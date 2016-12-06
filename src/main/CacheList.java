package main;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * CacheList
 * @author Ken Cooney
 * @date 06/09/2011
 *
 * The most recently requested object is the first entry in
 * the linked list.  MaxSize is variable (assigned in the
 * constructor)
 *
 *
 * Cost associated with scenarios:
 * 1) adding a new cache entry, cached objects < maxSize
 *    cost: 3 (check for cached file, add to start of list, cache file)
 *
 * 2) adding a new cache entry, cached objects = maxSize
 *    cost: 4 (check for cached file, remove last object in linked list, add to start of list, remove cached file)
 *
 * 3) finding entry in cache
 *    average cost: 2+ceiling(maxSize/2) (check for cached file, remove object in linked list, add to start of list)
 *    worst case cost: 2+maxSize (check for cached file, remove object in linked list, add to start of list)
 *
 * TESTED via CachListTestSuite.  All tests pass.
 *
 */
public class CacheList
{

    private CacheLog log; // not used yet
    private LinkedHashMap<String, Integer> linkedMap;
    private int maxSize;

    /**
     * Constructor.  The minimum cache size is 1.
     * @param directory - cache log directory for logging
     *                    objects removed from cache
     * @param maxsize - maximum number of objects to cache
     */
    public CacheList(String directory, int maxsize)
    {
        log = new CacheLog(directory);
        linkedMap = new LinkedHashMap<String, Integer>();
        if (maxsize<1)
        {
            this.maxSize=1;
        }
        else
        {
            this.maxSize=maxsize;
        }
    }

    /**
     * addNewObject
     * This puts the object in the front of the queue.
     * It removes any repeated object and trims the
     * list if the length exceeds maxSize.
     *
     * @param URL - URL that was just requested
     * @param hit - true if it was already cached.
     * @param replacementMethod - which cache replacement method to be used
     * @return - object removed, if any.  We'll need
     *           to remove this from the hash.
     */
    public String addNewObject(String URL, boolean hit, int replacementMethod)
    {
    	
    	String removedURL="";
        
        // temporarily stores the specified URL's (key) number of times accessed (value)
        // to be used in the new entry
        int timesAccessed = 0;  

        // If hit, temporarily store the URL's number of times accessed (value)
        // from its previous entry, and then removes said previous entry
        if (hit)
        {
        	timesAccessed = linkedMap.get(URL);
        	linkedMap.remove(URL);
        }


        // If size is MAXSIZE, remove entry depending on replacement method specified
        if (linkedMap.size()==maxSize)
        {
        	
        	switch (replacementMethod) 
        	{
        		// Replacement Method 0: Least Recently Used
        		// removes oldest entry (last link)
        		case 0: 
        			String last = "";
        			for(String key : linkedMap.keySet())
                	{
                	    last = key;						// get the last url in the cache
                	}
        			removedURL= last;					// and set the url to be removed
        			break;
        			
        		// Replacement Method 1: Most Recently Used	
        		// removes newest entry (first link)	
        		case 1:
        			String first = linkedMap.keySet().iterator().next();	// get the first url in the cache
        			removedURL = first;										// and set the url to be removed
        			break;
        		
        		// Replacement Method 2: Least Frequently Used
        		// removes entry with lowest access (smallest value in map)
        			
        		case 2:	
        			/* Search through all URLs and find the minimum times accessed:
        			 * 		for each URL (key)  in the list:
        			 * 			get the number of times accessed (value) and compare
        			 * 			to the other URLs to find the least frequently accessed URL
        			 * 			to remove from the list
        			 */
        			String minKey = "";
        		    int minValue = Integer.MAX_VALUE;
        		    	    
        		    for(String key : linkedMap.keySet())	 
        		    {										
        		        int value = linkedMap.get(key);			
        		        if(value < minValue)					      										
        		        {										
        		            minValue = value;			// find smallest value in list (least accessed)
        		            minKey = key;				// and its corresponding url
        		        }
        		    }
        		    									
        		    removedURL = minKey;			// and set the url to be removed  
        			break;
        		
        		// Replacement Method 3: Random Replacement
        		// removes a random link in list
        		case 3: 	
        			Random rand = new Random();
        			int randomIndex =  rand.nextInt(maxSize); 	// generate random index (0 to size of cache)
        			
        			Iterator<String> iterator = linkedMap.keySet().iterator();		// create the map iterator
        			for (int i = 0; i <= randomIndex; i++) 					// iterate to the randomly generated index
        			{
        				removedURL = iterator.next();						// and set the url to be removed	
        			}														
        	
        			break;
        	}
        	
        	linkedMap.remove(removedURL);	// remove the specified url based on replacement method chosen
        	log.logRemoval(removedURL);		// and log the removal of the url from the cache
        	
        }

        /* Add the URL to the top of the list: because using LinkedHashMap, must
         * make a copy of the map, clear the old map and add the new url, and copy the 
         * new map back in to the old map. This effectively adds the url to the front of the list
         */
        @SuppressWarnings("unchecked")
		LinkedHashMap<String, Integer> updatedMap = (LinkedHashMap<String, Integer>) linkedMap.clone();
        linkedMap.clear();
        linkedMap.put(URL, timesAccessed + 1);
        linkedMap.putAll(updatedMap);

        //System.out.println("Added "+URL);

        //traverseTest();

        return removedURL;
    }
    
//
//    /**
//     * getCacheSize
//     * Used by CacheListSizeThreeTests
//     * @return the number of objects cached
//     */
//    public int getCacheSize()
//    {
//        return linkedList.size();
//    }
//
//    /**
//     * getHead
//     * Used by CacheListSizeThreeTests
//     * @return URL at this location or empty string if
//     *         linkedlist is empty.
//     */
//    public String getHead()
//    {
//        String returnedURL="";
//        if (linkedList.size()>0)
//        {
//            returnedURL=linkedList.getFirst().toString();
//        }
//        return returnedURL;
//    }
//
//    /**
//     * get
//     * Used by CacheListSizeThreeTests
//     * @param i - index into the linklist.
//     * @return URL at this location or empty string if
//     *         param exceeds the size of linked list
//     */
//    public String get(int i)
//    {
//        String returnedURL="";
//        if (i<linkedList.size())
//        {
//            returnedURL=linkedList.get(i).toString();
//        }
//        return returnedURL;
//    }
//
//    /**
//     * traverseList
//     * For testing purposes only.  This displays the
//     * linklist of URLs.
//     */
//    public void traverseTest()
//    {
//        for (int i=0; i<linkedList.size();i++)
//        {
//            System.out.print(linkedList.get(i)+" => ");
//        }
//        System.out.println("NULL");
//    }

}

