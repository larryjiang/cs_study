package main.clrs.datastructures.elementarydatastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PureDoublelyLinkedList<T> implements LinkedList<T>{
	
	private Node<T> head;
	
	private Node<T> tail;
	
	private int size;
	
	public PureDoublelyLinkedList(){
		head = null;
		tail = null;
		size = 0;
	} 
	
	
	static class Node<T> implements LinkedList.Node<T>{
		T t;
		Node<T> pre;
		Node<T> next;
		
		public Node(T t){
			this.t  = t;
		}
		
		@Override
		public T getValue() {
			// TODO Auto-generated method stub
			return t;
		}

		@Override
		public main.clrs.datastructures.elementarydatastructures.LinkedList.Node<T> next() {
			// TODO Auto-generated method stub
			return next;
		}

		@Override
		public main.clrs.datastructures.elementarydatastructures.LinkedList.Node<T> prev() {
			// TODO Auto-generated method stub
			return pre;
		}
	}
	
	private boolean isValidIndex(int x){
		
		if(x < 0 || x >= size ){
			return false;
		}
		
		return true;
	}
	
	
	public Iterator<T> listIterator(final int index){
		return new ListIterator(index);
	}
	
	
	 private class ListIterator implements Iterator<T>{
		
		
		Node<T> current;
		
		ListIterator(int index) {
			if(!isValidIndex(index)){
				throw new IndexOutOfBoundsException("Index out of Bound " + index);
			};
			current = getNode(index);
		}
		
		public ListIterator(Node<T> cur) {
			if(cur == null || cur.getValue() == null){
				throw new NullPointerException();
			}
			
			if(!contains(cur)){
				throw new NoSuchElementException("The element is not contained in the list " + cur.getValue().toString());
			}
			current = cur;
		}
		
		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public T next() {
			Node<T> returned;
			returned = current;
 			current = current.next;
			return returned.getValue();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
	@Override
	public void insert(int index, T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int firstIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] searchOccurrence(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int index, T element) {
		// TODO Auto-generated method stub
		
	}

	
	public Node<T> getNode(int index){
		if(!isValidIndex(index)){
			throw new IndexOutOfBoundsException("Index out of Bound " + index);
		}
		Node<T> current = head;
		while(index > 0){
			current = current.next;
			index--;
		}
		return current;
	}
	
	@Override
	public T get(int index) {
		return getNode(index).getValue();
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enqueue(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return head == null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Container<T> t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Container<T> s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Container<T> s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean retainAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<T> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<main.clrs.datastructures.elementarydatastructures.LinkedList.Node<T>> NodeSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public main.clrs.datastructures.elementarydatastructures.LinkedList.Node<T>[] getNodes(
			Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getHead() {
		if(isEmpty()){
			return null;
		}
		return getHeadNode().getValue();
	}

	@Override
	public main.clrs.datastructures.elementarydatastructures.LinkedList.Node<T> getHeadNode() {
		// TODO Auto-generated method stub
		return head;
	}

	@Override
	public main.clrs.datastructures.elementarydatastructures.LinkedList.Node<T> getTailNode() {
		// TODO Auto-generated method stub
		return tail;
	}

}
