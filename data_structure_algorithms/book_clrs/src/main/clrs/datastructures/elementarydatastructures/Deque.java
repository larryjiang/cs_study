package main.clrs.datastructures.elementarydatastructures;


public interface Deque<T> extends Queue<T>{
	
	T dequeueFromTail();
	
	void enqueueFromHead(T t);
	
}
