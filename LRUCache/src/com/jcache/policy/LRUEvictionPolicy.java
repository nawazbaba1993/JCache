package com.lrucache.policy;

import java.util.Map;

import com.lrucache.model.Cache;
import com.lrucache.model.CacheNode;

public class LRUEvictionPolicy<T, E> implements EvictionPolicy<T, E> {

	@Override
	public void evictFromCache(Cache<T, E> cache) {
		CacheNode<T, E> cacheNode = cache.getCacheEnd();
		Map<T, CacheNode<T, E>> cacheMap = cache.getCacheMap();
		if(cacheMap.containsKey(cacheNode.getKey()))
				cacheMap.remove(cacheNode.getKey());
		cache.setCacheMap(cacheMap);
		cacheNode.getPrev().setNext(null);
		cache.setCacheEnd(cacheNode.getPrev());
		
	}

}
