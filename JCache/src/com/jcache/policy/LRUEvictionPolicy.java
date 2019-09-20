package com.jcache.policy;

import java.util.Map;

import com.jcache.model.Cache;
import com.jcache.model.CacheNode;

public class LRUEvictionPolicy<T, E> implements EvictionPolicy<T, E> {

	private Cache<T, E> cache;
	
	/**
	 * @param cache
	 */
	public LRUEvictionPolicy(int size) {
		super();
		this.cache = new Cache<T, E>(size);
	}

	@Override
	public E getData(T key) {
		Map<T, CacheNode<T, E>> cacheMap = cache.getCacheMap();
		if(cacheMap.containsKey(key)) {
			CacheNode<T, E> cacheNode = cacheMap.get(key);
			moveToHead(cacheNode);
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
			if(prev != null)
				prev.setNext(next);
			if(next != null)
				next.setPrev(prev);
			cacheMap.remove(key);
		}
	}

	@Override
	public Cache<T, E> getCache() {
		return this.cache;
	}

	@Override
	public void storeData(T key, E data) {
		Map<T, CacheNode<T, E>> cacheMap = cache.getCacheMap();
		CacheNode<T, E> cacheNode = null;
		if(cacheMap.containsKey(key)) {
			cacheNode = cacheMap.get(key);
			cacheNode.setData(data);
		} else {
			if(cache.isCacheFull()) {
				System.out.println("Cache is full calling eviction policy to evict cache");
				evictFromCache(cache);
			}
			cacheNode = new CacheNode<T, E>();
			cacheNode.setData(data);
			cacheNode.setKey(key);
		}
		moveToHead(cacheNode);
		
	}

	private void moveToHead(CacheNode<T, E> cacheNode) {
		CacheNode<T, E> next = cacheNode.getNext();
		CacheNode<T, E> prev = cacheNode.getPrev();
		if(prev != null)
			prev.setNext(next);
		if(next != null)
			next.setPrev(prev);
		CacheNode<T, E> cacheStart = cache.getCacheStart();
		cacheNode.setNext(cacheStart);
		if(cacheStart != null) {
			cacheNode.setPrev(cacheStart.getPrev());
			cacheStart.setPrev(cacheNode);
		} else {
			cache.setCacheEnd(cacheNode);
		}
		cache.setCacheStart(cacheNode);
		cache.getCacheMap().put(cacheNode.getKey(), cacheNode);
	}

	private void evictFromCache(Cache<T, E> cache) {
		CacheNode<T, E> cacheNode = cache.getCacheEnd();
		Map<T, CacheNode<T, E>> cacheMap = cache.getCacheMap();
		if(cacheMap.containsKey(cacheNode.getKey()))
				cacheMap.remove(cacheNode.getKey());
		cache.setCacheMap(cacheMap);
		cacheNode.getPrev().setNext(null);
		cache.setCacheEnd(cacheNode.getPrev());
		
	}

}
