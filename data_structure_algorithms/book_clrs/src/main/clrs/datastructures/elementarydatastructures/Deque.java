package main.clrs.datastructures.elementarydatastructures;


public interface Deque<T> extends Queue<T>{
	
	void enqueueFromTail();
	
	void dequeueFromHead();
	
}
