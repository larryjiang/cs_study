package main.clrs.datastructures.hashtables;


import main.clrs.datastructures.elementarydatastructures.Container;
import main.clrs.datastructures.elementarydatastructures.Countable;
import main.clrs.datastructures.elementarydatastructures.Set;

public interface Map<K,V> extends Countable{

	V get(Object key);
	
	void put(K key, V value);
	
	void remove(Object k);
	
	void clear();
	
	boolean containsKey(Object key);
	
	boolean containsValue(Object value);
	
    void putAll(Map<? extends K, ? extends V> m);
    
    Set<K> keySet();
	
    Container<V> valueSet();
    
	interface Entry<K,V>{
		
		K getKey();
		
		V getValue();
		
		void setValue();
		
		boolean equals(Object o);
		
		int hashCode();
	}
	
}
