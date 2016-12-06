package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Proxy;

public class TestProxy {
	
	Proxy proxy;
	int hits;
	
	@Test
	public void testLRUHitRatio() {
		proxy = new Proxy(System.getProperty("user.dir") + "/src/leastRecentlyUsed", 10, 1, 0);
		proxy.run();
		hits = proxy.getHits();
		double hitRatio = (hits / (double)proxy.getInputs()) * 100;
		System.out.printf("Least Recently Used ("+ hits + " hits) : %.2f%%\n", hitRatio);
	}
	
	@Test
	public void testMRUHitRatio() {
		proxy = new Proxy(System.getProperty("user.dir") + "/src/mostRecentlyUsed", 10, 1, 1);
		proxy.run();
		hits = proxy.getHits();
		double hitRatio = (proxy.getHits() / (double)proxy.getInputs()) * 100;
		System.out.printf("Most Recently Used ("+ hits + " hits): %.2f%%\n", hitRatio);

	}
	
	@Test
	public void testLFUHitRatio() {
		proxy = new Proxy(System.getProperty("user.dir") + "/src/leastFrequentlyUsed", 10, 1, 2);
		proxy.run();
		hits = proxy.getHits();
		double hitRatio = (proxy.getHits() / (double)proxy.getInputs()) * 100;
		System.out.printf("Least Frequently Used ("+ hits + " hits): %.2f%%\n", hitRatio);

	}
	
	@Test
	public void testRRHitRatio() {
		proxy = new Proxy(System.getProperty("user.dir") + "/src/randomReplacement", 10, 1, 3);
		proxy.run();
		hits = proxy.getHits();
		double hitRatio = (proxy.getHits() / (double)proxy.getInputs()) * 100;
		System.out.printf("Random Replacement ("+ hits + " hits): %.2f%%\n", hitRatio);
	}

}
