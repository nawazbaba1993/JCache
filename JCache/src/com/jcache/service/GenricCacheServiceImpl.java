package com.jcache.service;

import com.jcache.model.Cache;
import com.jcache.policy.EvictionPolicy;

public class GenricCacheServiceImpl<T, E> implements CacheService<T, E> {

	private EvictionPolicy<T, E>  evictionPolicy;
	
	/**
	 * @param evictionPolicy
	 * @param cache
	 */
	public GenricCacheServiceImpl(EvictionPolicy<T, E> evictionPolicy) {
		super();
		this.evictionPolicy = evictionPolicy;
	}

	@Override
	public E getData(T key) {
		return evictionPolicy.getData(key);
	}

	@Override
	public void removeData(T key) {
		evictionPolicy.removeData(key);
	}

	@Override
	public Cache<T, E> getCache() {
		return evictionPolicy.getCache();
	}

	@Override
	public void storeData(T key, E data) {
		evictionPolicy.storeData(key, data);
	}

}
