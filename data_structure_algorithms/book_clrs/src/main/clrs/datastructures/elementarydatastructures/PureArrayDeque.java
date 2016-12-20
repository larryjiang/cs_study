package main.clrs.datastructures.elementarydatastructures;

import static org.junit.Assert.*;

/**
 * An implementation of Deque based on the exercise of clrs
 * Note this class resembles the {@link #PureArrayQueue} a lot.
 * It can be gained by augmenting {@link #PureArrayQueue} with 
 * two new methods, {@link #enqueueFromHead(T)}, {@link #dequeueFromTail()}} 
 * @author larry
 *
 * @param <T>
 */
public class PureArrayDeque<T> implements Deque<T> {

	private static final int DEFAULT_SIZE = 8;

	private static final Object[] SHARED_EMPTY_ARRAY = {};

	private transient Object[] underlying_array;

	private static final int MAXIMUM_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * let head points to the position of the element that should be retrieved
	 * when dequeue is called.
	 */
	private int head;

	/**
	 * gives the number of elements stored in the queue.
	 */
	private int size;

	private int increment(int value) {
		return (value + 1) % underlying_array.length;
	}
	
	private int decrement(int value){
		if(value == 0){
			value = underlying_array.length - 1;
		}else{
			value--;
		}
		return value;
	}

	private int calculateTail() {
		return (head + size) % underlying_array.length;
	}

	public PureArrayDeque(int initial_capacity) {
		if (initial_capacity < 0) {
			throw new IllegalArgumentException("Invalid initial size: "
					+ initial_capacity);
		}
		ensureCapacity(initial_capacity);
	}

	public PureArrayDeque() {
		underlying_array = SHARED_EMPTY_ARRAY;
	}

	private void ensureCapacity(int minSize) {

		if (minSize > MAXIMUM_SIZE) {
			throw new OutOfMemoryError();
		}

		if (underlying_array == SHARED_EMPTY_ARRAY || underlying_array == null) {
			minSize = getNewMinSize(minSize);
			underlying_array = new Object[minSize];
			return;
		} else {
			minSize = getNewMinSize(minSize);
			Object[] new_underlying_array = new Object[minSize];
			int tail = calculateTail();

			if (tail <= head) {
				System.arraycopy(underlying_array, head, new_underlying_array,
						0, underlying_array.length - head);
				System.arraycopy(underlying_array, 0, new_underlying_array,
						underlying_array.length - head, tail);
			} else {
				System.arraycopy(underlying_array, head, new_underlying_array,
						0, size);
			}
			head = 0;
			underlying_array = new_underlying_array;
		}
	}

	private int getNewMinSize(int minSize) {
		if (minSize < DEFAULT_SIZE) {
			minSize = DEFAULT_SIZE;
		} else if (minSize < MAXIMUM_SIZE / 2) {
			minSize = minSize + minSize / 2;
		} else {
			minSize = minSize + (MAXIMUM_SIZE - minSize) / 2;
		}
		return minSize;
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T dequeue() {
		if (empty()) {
			throw new IllegalStateException(
					"Queue empty, considering calling empty() method before dequeue");
		}
		T t;
		t = (T) underlying_array[head];
		head = increment(head);
		size -= 1;
		return t;
	}

	@Override
	public void enqueue(T t) {
		if (full() || underlying_array == SHARED_EMPTY_ARRAY) {
			ensureCapacity(size + 1);
		}
		int tail = calculateTail();
		underlying_array[tail] = t;
		size += 1;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	private boolean full() {
		return size == underlying_array.length;
	}

	@Override
	public void enqueueFromHead(T t) {
		if(full() || underlying_array == SHARED_EMPTY_ARRAY){
			ensureCapacity(size + 1);
		}
		
		head = decrement(head);
		underlying_array[head] = t;
		size++;

	}

	@Override
	public T dequeueFromTail() {
		if(empty()){
			throw new IllegalStateException(
					"Queue empty, considering calling empty() method before dequeue");
		}
		
		int tail = calculateTail();
		tail = decrement(tail);
		@SuppressWarnings("unchecked")
		T r = (T)underlying_array[tail];
		size--;
		return r;
	}

	
	public static void main(String args[]){
		Deque<Integer> deque = new PureArrayDeque<>();
		deque.enqueue(1);
		deque.enqueue(2);
		deque.enqueue(3);
		deque.enqueueFromHead(4);
		deque.enqueueFromHead(5);
		
		assertTrue(deque.dequeue().intValue() ==  5);
		assertTrue(deque.dequeue().intValue() == 4);
		assertTrue(deque.dequeue().intValue() == 1);
		assertTrue(deque.dequeueFromTail().intValue() == 3);
		assertTrue(deque.dequeueFromTail().intValue() == 2);
		
		System.out.println("Success!");
		
		
		
	}
	
	
}
