package main.clrs.datastructures.elementarydatastructures;

/**
 * 
 * @author larry
 *
 * @param <T>
 */
public interface Stack<T> extends Countable{

	
	T pop();
	
	
	void push(T t);
	
	
	boolean isEmpty();
}
