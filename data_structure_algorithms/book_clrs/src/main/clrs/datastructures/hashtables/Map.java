package main.clrs.datastructures.hashtables;

import main.clrs.datastructures.elementarydatastructures.Countable;

public interface Map<K,V> extends Countable{

	V get(Object key);
	
	void put(K key, V value);
	
	void remove(Object k);
			
	
}
