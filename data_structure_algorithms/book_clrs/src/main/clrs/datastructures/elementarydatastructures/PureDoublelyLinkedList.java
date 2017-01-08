package main.clrs.datastructures.elementarydatastructures;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author larry
 * Generally, there are only two possible states of a linked list:
 * 1) Empty
 * 2) Not empty	
 *
 * @param <T>
 */
public class PureDoublelyLinkedList<T> implements LinkedList<T>{
	
	private transient Node<T> head;
	
	private transient Node<T> tail;
	
	private int size;
	
	protected transient int modeCount = 0;
	
	
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
			pre = null;
			next = null;
		}
		
		public Node(T t, Node<T> pre, Node<T> next){
			this.t = t;
			this.pre = pre;
			this.next = next;
		}
		
		@Override
		public T getValue() {
			// TODO Auto-generated method stub
			return t;
		}

		public void setValue(T t){
			this.t = t;
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
		return x >=0 && x < size;
	}
	
	private boolean isValidPosition(int x){
		return x >= 0 && x <= size;
	}
	
	
	public Iterator<T> listIterator(final int index){
		return new ListIterator(index);
	}
	
	public Iterator<T> listIterator(final Node<T> startingNode){
		return new ListIterator(startingNode);
	}
	
	private class ListIterator implements Iterator<T>{
		Node<T> lastReturned = null;
		Node<T> current;
		
		int expectedModeCount;
		
		ListIterator(int index) {
			if(!isValidIndex(index)){
				throw new IndexOutOfBoundsException("Index out of Bound " + index);
			};
			current = getNode(index);
			expectedModeCount = modeCount;
		}
		
		public ListIterator(Node<T> cur) {
			if(cur == null){
				throw new NullPointerException();
			}
		
			if(!contains(cur)){
				throw new NoSuchElementException("The element is not contained in the list " + cur.getValue().toString());
			}
			current = cur;
			expectedModeCount = modeCount;
		}

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public T next() {
			checkForModification();
			if(!hasNext()){
				throw new IllegalArgumentException("No next element");
			}
			lastReturned = current;
 			current = current.next;
			return lastReturned.getValue();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			checkForModification();
			if(lastReturned == null){
				throw new IllegalStateException("No current element.");
			}
			
			//the difference of this from a standard implementation is that 
			//the standard edition considered iterating backward.
			unlinkNode(lastReturned);
			expectedModeCount++;
			lastReturned = null;
		}
		
		final void checkForModification(){
			if(expectedModeCount != modeCount){
				throw new ConcurrentModificationException();
			}
		}
	}
	
	
	  T unlinkNode(Node<T> e){
		final Node<T> pre = e.pre;
		final Node<T> next = e.next;
		
		if(pre == null){
			head = next;
		}else{
			pre.next = next;
			e.pre = null;
		}
		
		if(next == null){
			tail = pre;
		}else{
			next.pre = pre;
			e.next = null;
		}
		
		e.setValue(null);
		size--;
		modeCount++;
		return e.getValue();
	}
	  
	T unlinkFirstNode(){
		T value = head.getValue();
		Node<T> next = head.next;
		
		head.t = null;
		head.next = null;
		
		if(next == null){
			tail = null;
		}else{
			next.pre = null;
		}
		head = next;
		size--;
		modeCount++;
		return value;
	}
	  
	T unlinkLastNode(){
		T value = tail.getValue();
		Node<T> pre = tail.pre;
		
		tail.t = null;
		tail.pre = null;
		
		if(pre == null){
			head = null;
		}else{
			pre.next = null;
		}
		
		tail = pre;
		size--;
		modeCount++;
		return value;
	}
	  
	void linkFirstNode(Node<T> e){
		if(head == null){
			tail = e;
		}else{
			head.pre = e;
		}
		e.next = head;
		e.pre = null;
		head = e;
		size++;
		modeCount++;
	}
	  
	void linkLastNode(Node<T> e){
		
		if(head == null){
			head = e;
		}else{
			tail.next = e;
		}
		e.pre = tail;
		e.next = null;
		tail = e;
		size++;
		modeCount++;
	} 
	
	void linkBefore(Node<T> e, Node<T> succ){
		if(succ == null){
			throw new NullPointerException();
		}
		
		if(succ == head){
			head = e;
		}else{
			succ.pre.next = e;
		}
		
		e.next = succ;
		e.pre = succ.pre;
		succ.pre = e;
		size++;
		modeCount++;
	}
	
	
	
	private void checkIndex(int x){
		if(!isValidIndex(x)){
			throw new IndexOutOfBoundsException("Index " + x);
		}
	}
	
	private void checkPosition(int x){
		if(!isValidPosition(x)){
			throw new IndexOutOfBoundsException("Position " + x);
		}
	}
	
	
	
	@Override
	public void insert(int index, T element) {
		checkPosition(index);
		Node<T> node = new Node<T>(element);
		
		if(index == 0){
			linkFirstNode(node);
		}else if(index == size){
			linkLastNode(node);
		}else{
			Node<T> succ = getNode(index);
			linkBefore(node, succ);
		}
	}

	@Override
	public void delete(int index) {
		checkIndex(index);
		Node<T> node = getNode(index);
		unlinkNode(node);
	}

	@Override
	public int firstIndexOf(Object o) {
		int index = 0;
		if(o == null){
			for(T t : this){
				if(t == o){
					return index;
				}
				index++;
			}
		}else{
			for(T t : this){
				if(o.equals(t)){
					return index;
				}
				index++;
			}
			
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		int index = size -1;
		Node<T> node = tail;
		if(o == null){
			while(node != null){
				if(node.getValue()== o){
					return index;
				}
				index--;
				node = node.pre;
			}
		}else{
			while(node != null){
				if(o.equals(node.getValue())){
					return index;
				}
				index--;
				node = node.pre;
			}
			
		}
		return -1;
	}

	@Override
	public int[] searchOccurrence(Object o) {
		ArrayList<Integer> oList = new ArrayList<>();
		
		int index = 0;
		Node<T> node = head;
		if(o==null){
			while(node != null){
				if(node.getValue() == o){
					oList.add(index);
				}
				node = node.next;
			}
		}else{
			while(node != null){
				if(o.equals(node.getValue())){
					oList.add(index);
				}
				node = node.next;
			}
		}
		return oList.stream().mapToInt(i -> i).toArray();
	}

	@Override
	public void set(int index, T element) {
		
		Node<T> node = getNode(index);
		node.setValue(element);
	}

	
	public Node<T> getNode(int index){
		checkIndex(index);
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
		T result = this.get(0);
		unlinkFirstNode();
		return result;
	}

	@Override
	public void enqueue(T t) {
		// TODO Auto-generated method stub
		this.add(t);
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
		T result = get(this.size -1);
		unlinkLastNode();
		return result;
	}

	@Override
	public void push(T t) {
		// TODO Auto-generated method stub
		this.add(t);
	}

	@Override
	public boolean contains(Object o) {
		return firstIndexOf(o) != -1;
	}
	
	/**
	 * This always returns true since in the definition of 
	 * add in java collection interface.
	 * 
	 */
	@Override
	public boolean add(T e) {
		Node<T> node = new Node<T>(e);
		linkLastNode(node);
		return true;
	}

	@Override
	public boolean addAll(Container<T> t) {
		// TODO Auto-generated method stub
		if(t.size() == 0){
			return false;
		}
		
		for(T e : t){
			add(e);
		};
		
		return true;
	}
	
	@Override
	public boolean remove(Object o) {
		int index = firstIndexOf(o);
		if(index < 0){
			return false;
		}else{
			delete(index);
			return true;
		}
	}

	@Override
	public boolean containsAll(Container<T> s) {
		// TODO Auto-generated method stub
		for(T t : s){
			if(this.contains(t)){
				continue;
			}else{
				return false;
			}
		};
		
		return true;
	}

	@Override
	public boolean removeAll(Container<T> s) {
		// TODO Auto-generated method stub
		if(isEmpty()){
			return false;
		}
		
		for(T t : s){
			this.remove(s);
		}
		
		return true;
	}

	@Override
	public void clear() {
		Node<T> next;
		for(Node<T> first = head; head != null;){
			next = first.next;
			first.setValue(null);
			first.next = null;
			first.pre = null;
			first = next;
		};
		head = tail = null;
		size = 0;
		modeCount++;
	}

	@Override
	public boolean retainAll(Container<T> s) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		//return false;
	}

	@Override
	public Set<T> values() {
		// TODO Auto-generated method stub
		
		throw new UnsupportedOperationException();
		//return null;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator(0);
	}

	@Override
	public Set<main.clrs.datastructures.elementarydatastructures.LinkedList.Node<T>> NodeSet() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		//return null;
	}

	@Override
	public main.clrs.datastructures.elementarydatastructures.LinkedList.Node<T>[] getNodes(
			Object o) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		Node<T>[] nodes = new Node[size];
		Node<T> node = head;
		int i = 0;
		while(node != null){
			nodes[i] = node;
			i++;
			node = node.next;
		}
		
		return nodes;
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


	@Override
	public T getTail() {
		// TODO Auto-generated method stub
		if(isEmpty()){
			return null;
		}
		return getTailNode().getValue();
	}

}
