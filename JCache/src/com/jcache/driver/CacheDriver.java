package com.jcache.driver;

import com.jcache.model.Cache;
import com.jcache.model.CacheNode;
import com.jcache.policy.EvictionPolicy;
import com.jcache.policy.LRUEvictionPolicy;
import com.jcache.service.CacheService;
import com.jcache.service.GenricCacheServiceImpl;

public class CacheDriver {

	public static void main(String[] args) {
		EvictionPolicy<Integer, String> evictionPolicy = new LRUEvictionPolicy<>();
		CacheService<Integer, String> service = new GenricCacheServiceImpl<Integer, String>(4, evictionPolicy);
		service.storeData(1, "A");
		service.storeData(2, "B");
		service.storeData(3, "D");
		service.storeData(4, "E");
		
		System.out.println("CACHE AFTER ADDING DATA");
		printDataInCache(service.getCache());
		
		System.out.println("CACHE AFTER MODIFYING EXISTING DATA");
		service.storeData(3, "C");
		printDataInCache(service.getCache());
		
		System.out.println("CACHE AFTER ADDING MORE THAN ITS SIZE DATA");
		service.storeData(5, "F");
		printDataInCache(service.getCache());
		
		System.out.println("GETTING EXISTING DATA");
		System.out.println(service.getData(3));
		
		System.out.println("CACHE AFTER GETTING EXISTING DATA");
		printDataInCache(service.getCache());
		
		System.out.println("CACHE AFTER REMOVING EXISTING DATA");
		service.removeData(4);
		printDataInCache(service.getCache());
	}

	private static void printDataInCache(Cache<Integer, String> cache) {
		CacheNode<Integer, String> cNode = cache.getCacheStart();
		while(cNode != null) {
			System.out.println(cNode.getKey()+"-"+cNode.getData());
			cNode = cNode.getNext();
		}
	}

}
