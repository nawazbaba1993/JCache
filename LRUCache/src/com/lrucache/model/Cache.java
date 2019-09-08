package com.lrucache.model;

import java.util.HashMap;
import java.util.Map;

public class Cache<T, E> {
	
	private int size;
	private Map<T, CacheNode<T, E>> cacheMap;
	private CacheNode<T, E> cacheStart;
	private CacheNode<T,E> cacheEnd;
	
	/**
	 * @param size
	 */
	public Cache(int size) {
		super();
		this.size = size;
		cacheMap = new HashMap<T, CacheNode<T, E>>();
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * @return the cacheMap
	 */
	public Map<T, CacheNode<T, E>> getCacheMap() {
		return cacheMap;
	}
	/**
	 * @param cacheMap the cacheMap to set
	 */
	public void setCacheMap(Map<T, CacheNode<T, E>> cacheMap) {
		this.cacheMap = cacheMap;
	}
	/**
	 * @return the cacheStart
	 */
	public CacheNode<T, E> getCacheStart() {
		return cacheStart;
	}
	/**
	 * @param cacheStart the cacheStart to set
	 */
	public void setCacheStart(CacheNode<T, E> cacheStart) {
		this.cacheStart = cacheStart;
	}
	/**
	 * @return the cacheEnd
	 */
	public CacheNode<T, E> getCacheEnd() {
		return cacheEnd;
	}
	/**
	 * @param cacheEnd the cacheEnd to set
	 */
	public void setCacheEnd(CacheNode<T, E> cacheEnd) {
		this.cacheEnd = cacheEnd;
	}
	public boolean isCacheFull() {
		if(this.size == cacheMap.size())
			return true;
		return false;
	}
	
}
