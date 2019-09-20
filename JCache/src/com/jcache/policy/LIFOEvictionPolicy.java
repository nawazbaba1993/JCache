package com.jcache.policy;

import java.util.Map;

import com.jcache.model.Cache;
import com.jcache.model.CacheNode;

public class LIFOEvictionPolicy<E, T> implements EvictionPolicy<T, E> {
	
	private Cache<T, E> cache;

	/**
	 * @param size
	 */
	public LIFOEvictionPolicy(int size) {
		super();
		this.cache = new Cache<T, E>(size);
	}

	@Override
	public E getData(T key) {
		Map<T, CacheNode<T, E>> cacheMap = cache.getCacheMap();
		if(cacheMap.containsKey(key)) {
			CacheNode<T, E> cacheNode = cacheMap.get(key);
			return cacheNode.getData();
		}
		return null;
	}

	@Override
	public void removeData(T key) {
		Map<T, CacheNode<T, E>> cacheMap = cache.getCacheMap();
		if(cacheMap.containsKey(key)) {
			CacheNode<T, E> cacheNode = cacheMap.get(key);
			CacheNode<T, E> next = cacheNode.getNext();
			CacheNode<T, E> prev = cacheNode.getPrev();
			if(prev != null) {
				prev.setNext(next);
			}
			if(next != null) {
				next.setPrev(prev);
			}
			cacheMap.remove(key);
		}
	}

	@Override
	public Cache<T, E> getCache() {
		return cache;
	}

	@Override
	public void storeData(T key, E data) {
		Map<T, CacheNode<T, E>> cacheMap = cache.getCacheMap();
		CacheNode<T, E> cacheNode = null;
		if(cacheMap.containsKey(key)) {
			cacheNode = cacheMap.get(key);
			cacheNode.setData(data);
			cacheNode.setKey(key);
		} else {
			if(cache.isCacheFull()) {
				evictFromCache();
			}
			cacheNode = new CacheNode<T, E>();
			cacheNode.setData(data);
			cacheNode.setKey(key);
			CacheNode<T, E> cacheStart = cache.getCacheStart();
			if(cacheStart != null) {
				cacheNode.setNext(cacheStart);
				cacheStart.setPrev(cacheNode);
			} else {
				cache.setCacheEnd(cacheNode);
			}
			cache.setCacheStart(cacheNode);
		}
		cacheMap.put(key, cacheNode);
		cache.setCacheMap(cacheMap);
	}

	private void evictFromCache() {
		CacheNode<T,E> cacheEnd = cache.getCacheEnd();
		if(cacheEnd != null) {
			CacheNode<T, E> prev = cacheEnd.getPrev();
			prev.setNext(cacheEnd.getNext());
			cache.setCacheEnd(prev);
			cache.getCacheMap().remove(cacheEnd.getKey());
		}
	}

}
