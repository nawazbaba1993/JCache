package com.jcache.service;

import com.jcache.model.Cache;

public interface CacheService<T, E> {
	
	/**
	 * This method fetches data. Returns null if doesn't exist.
	 * @param key
	 * @return data
	 */
	E getData(T key);
	
	/**
	 * This method remove the data if exist.
	 * @param key
	 */
	void removeData(T key);
	
	/**
	 * This method return the current cache.
	 * @return cache
	 */
	Cache<T, E> getCache();

	/**
	 * This method store's data in to cache if key doesn't exist's else update's the data if key exist's
	 * @param key
	 * @param value
	 */
	void storeData(T key, E data);

}
