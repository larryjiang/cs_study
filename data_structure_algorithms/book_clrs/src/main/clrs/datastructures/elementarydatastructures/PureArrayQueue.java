package main.clrs.datastructures.elementarydatastructures;


/**
 * When we are programming, we may notice the states that an
 * object may have. Take this as an example, an queue may have three 
 * states:
 * (1) Empty;
 * (2) Full;
 * (3) Normal(the queue has element(s) within it);
 * The key in constructing this class is to find a way to 
 * represent those three states properly and this representation at the same time
 * should distinct different states easily.
 * @author larry
 *
 * @param <T>
 */
public class PureArrayQueue<T> implements Queue<T>{
	
	private static final int DEFAULT_SIZE  = 8;
	
	private static final Object [] SHARED_EMPTY_ARRAY = {};
	
	private transient Object [] underlying_array;
	
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
	
	private  int increment(int value){
		return (value + 1) % underlying_array.length; 
	}
	
	private int calculateTail(){
		return (head + size) % underlying_array.length;
	}
	
	public PureArrayQueue(int initial_capacity){
		if(initial_capacity < 0){
			throw new IllegalArgumentException("Invalid initial size: " + initial_capacity);
		}
		ensureCapacity(initial_capacity);
	}
	
	public PureArrayQueue(){
		underlying_array = SHARED_EMPTY_ARRAY;
	}
	
	
	
	private void ensureCapacity(int minSize){
		
		if(minSize > MAXIMUM_SIZE){
			throw new OutOfMemoryError();
		}
		
		if(underlying_array == SHARED_EMPTY_ARRAY || underlying_array == null){
			minSize = getNewMinSize(minSize);
			underlying_array = new Object[minSize];
			return;
		}else{
			minSize = getNewMinSize(minSize);
			Object[] new_underlying_array = new Object[minSize];
			int tail = calculateTail();
			
			if(tail <= head){
				System.arraycopy(underlying_array, head, new_underlying_array, 0, underlying_array.length - head);
				System.arraycopy(underlying_array, 0, new_underlying_array,underlying_array.length - head , tail);
			}else{
				System.arraycopy(underlying_array, head, new_underlying_array, 0, size);
			}
			head = 0;
			underlying_array = new_underlying_array;
		}
	} 
	
	private int getNewMinSize(int minSize){
		if(minSize < DEFAULT_SIZE){
			minSize = DEFAULT_SIZE;
		}else if(minSize < MAXIMUM_SIZE / 2){
			minSize = minSize + minSize / 2;
		}else{
			minSize = minSize + (MAXIMUM_SIZE - minSize) / 2;
		}
		return minSize;
	}
	
	
	@Override
	public int size(){
		return size; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public T dequeue(){
		if(isEmpty()){
			throw new IllegalStateException("Queue empty, considering calling empty() method before dequeue");
		}
		T t;
		t = (T)underlying_array[head];
		head = increment(head);		
		size -= 1;
		return t;
	}

	@Override
	public void enqueue(T t) {
		if(full() || underlying_array == SHARED_EMPTY_ARRAY){
			ensureCapacity(size + 1);
		}
		int tail = calculateTail();
		underlying_array[tail] = t;
		size += 1;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	
	private boolean full(){
		return size == underlying_array.length;
	}
}
