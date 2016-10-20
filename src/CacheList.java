
import java.util.LinkedList;

/**
 * CacheList
 *
 * @author Ken Cooney
 * @date 06/09/2011
 *
 * The most recently requested object is the first entry in the linked list.
 * MaxSize is variable (assigned in the constructor)
 *
 *
 * Cost associated with scenarios: 1) adding a new cache entry, cached objects <
 * maxSize cost: 3 (check for cached file, add to start of list, cache file)
 *
 * 2) adding a new cache entry, cached objects = maxSize cost: 4 (check for
 * cached file, remove last object in linked list, add to start of list, remove
 * cached file)
 *
 * 3) finding entry in cache average cost: 2+ceiling(maxSize/2) (check for
 * cached file, remove object in linked list, add to start of list) worst case
 * cost: 2+maxSize (check for cached file, remove object in linked list, add to
 * start of list)
 *
 * TESTED via CachListTestSuite. All tests pass.
 *
 */
public class CacheList {
    //maximum number of objects to cache
    private final int MAX_SIZE = 20;
    
    private CacheLog log; // not used yet
    private LinkedList<String> linkedList;
    private int maxSize;

    /**
     * Constructor. The minimum cache size is 1.
     *
     * @param directory - cache log directory for logging objects removed from
     * cache
     * @param maxsize - maximum number of objects to cache
     */
    public CacheList(String directory, int maxsize) {
        log = new CacheLog(directory);
        linkedList = new LinkedList<String>();
        if (maxsize < 1) {
            this.maxSize = 1;
        } else if (maxsize > MAX_SIZE) {
            maxsize = MAX_SIZE;
        }else{
            this.maxSize = maxsize;
        }
    }

    /**
     * addNewObject This puts the object in the front of he queue. It removes
     * any repeated object and trims the list if the length exceeds maxSize.
     *
     * @param URL - URL that was just requested
     * @return - object removed, if any. We'll need to remove this from the
     * hash.
     */
    public String addNewObject(String URL) {
        String removedURL = "";

        // If size is MAXSIZE, remove last link
        if (linkedList.size() == maxSize) {
            removedURL = (String) linkedList.getLast();
            log.logRemoval(removedURL);
            linkedList.removeLast();
        }

        // Newest is always the first.
        linkedList.addFirst(URL);

	//System.out.println("Added "+URL);
	//traverseTest();
        return removedURL;
    }

    /**
     * getCacheSize Used by CacheListSizeThreeTests
     *
     * @return the number of objects cached
     */
    public int getCacheSize() {
        return linkedList.size();
    }

    /**
     * getHead Used by CacheListSizeThreeTests
     *
     * @return URL at this location or empty string if linkedlist is empty.
     */
    public String getHead() {
        String returnedURL = "";
        if (linkedList.size() > 0) {
            returnedURL = linkedList.getFirst().toString();
        }
        return returnedURL;
    }

    /**
     * get Used by CacheListSizeThreeTests
     *
     * @param i - index into the linklist.
     * @return URL at this location or empty string if param exceeds the size of
     * linked list
     */
    public String get(int i) {
        String returnedURL = "";
        if (i < linkedList.size()) {
            returnedURL = linkedList.get(i).toString();
        }
        return returnedURL;
    }

    /**
     * traverseList For testing purposes only. This displays the linklist of
     * URLs.
     */
    public void traverseTest() {
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.print(linkedList.get(i) + " => ");
        }
        System.out.println("NULL");
    }

    /**
     * move url to the front position
     * @param url 
     */
    public void replaceObject(String url) {
       linkedList.remove(url);
       linkedList.addFirst(url);
    }

}
