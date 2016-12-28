package main.clrs.datastructures.elementarydatastructures;

public interface LinkedList<T> extends List<T>{

	Set<Node<T>> NodeSet();
	
	Node<T> [] getNodes(Object o);
	
	T getHead();
	
	T getTail();
	
	Node<T> getHeadNode();
	
	Node<T> getTailNode();
	
	interface Node<T>{
		
		T getValue();

		Node<T> next();
		
		Node<T> prev();
		
	}
	
}
