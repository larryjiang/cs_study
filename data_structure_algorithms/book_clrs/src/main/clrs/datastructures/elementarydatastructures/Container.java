package main.clrs.datastructures.elementarydatastructures;

public interface Container<T> extends Countable<T>, Iterable<T>{
	
	boolean contains(Object o);
	
	boolean add(T e);
	
	boolean addAll(Container<T> t);
	
	/**
	 * remove one object in the container. If there are multiple occurrences
	 * of such an element, it is subject to the implementor to decide how the 
	 * operation should be.
	 * @param o
	 * @return
	 */
	boolean remove(Object o);
	
	boolean containsAll(Container<T> s);
	
	boolean removeAll(Container<T> s);
	
	void clear();
	
	boolean retainAll(Container<T> s);
	
	/**
	 * @return a set of unique values in this Container.
	 */
	Set<T> values();
}
