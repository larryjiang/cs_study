package main.clrs.datastructures.elementarydatastructures;
/**
 * 
 * @author larry
 *
 * @param <T>
 */
public interface Queue<T> extends Countable<T>{
	
	
	T dequeue();
	
	
	void enqueue(T t);
	
	
	boolean empty();
	
}