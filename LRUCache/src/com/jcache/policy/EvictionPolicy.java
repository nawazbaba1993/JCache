package com.lrucache.policy;

import com.lrucache.model.Cache;

public interface EvictionPolicy<T, E> {
	
	void evictFromCache(Cache<T, E> cache);

}
