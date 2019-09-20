package com.jcache.policy;

import com.jcache.model.Cache;

public interface EvictionPolicy<T, E> {
	
	void evictFromCache(Cache<T, E> cache);

}
