package main.clrs.datastructures.elementarydatastructures;


public interface List<T> extends Queue<T>, Stack<T>, Container<T>, Iterable<T>{
	
	void insert(int index, T element);
	
	void delete(int index);
	
	int firstIndexOf(Object o);
	
	int lastIndexOf(Object o);
	
	int[] searchOccurrence(Object o);
	
	void set(int index, T element);
	
	T get(int index);
	
}
