package com.jcache.model;

public class CacheNode<T, E> {
	
	private T key;
	private E data;
	private CacheNode<T, E> next;
	private CacheNode<T, E> prev;
	
	/**
	 * @return the data
	 */
	public E getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(E data) {
		this.data = data;
	}
	/**
	 * @return the next
	 */
	public CacheNode<T, E> getNext() {
		return next;
	}
	/**
	 * @param next the next to set
	 */
	public void setNext(CacheNode<T, E> next) {
		this.next = next;
	}
	/**
	 * @return the prev
	 */
	public CacheNode<T, E> getPrev() {
		return prev;
	}
	/**
	 * @param prev the prev to set
	 */
	public void setPrev(CacheNode<T, E> prev) {
		this.prev = prev;
	}
	/**
	 * @return the key
	 */
	public T getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(T key) {
		this.key = key;
	}
	
}
