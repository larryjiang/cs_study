package main.clrs.datastructures.elementarydatastructures;

public class PureArrayStack<T>  implements Stack<T>{

	private static final int DEFAULT_SIZE  = 8;
	
	private static final int MAXIMUM_SIZE = Integer.MAX_VALUE - 8;
	
	private static final Object[] SHARED_EMPTY_ARRAY = {};
	
	private transient Object [] underlying_array;
	
	private int size;
	
	
	public PureArrayStack(int initial_capacity) {
		if(initial_capacity < 0){
			throw new IllegalArgumentException("Invalid initial size: " + initial_capacity);
		}
		insureCapacityAndAllocate(initial_capacity);
	}
	
	public PureArrayStack() {
		underlying_array = SHARED_EMPTY_ARRAY;
	}
	
	
	
	private void insureCapacityAndAllocate(int minSize){
		
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
			System.arraycopy(underlying_array, 0, new_underlying_array, 0, size);
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
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		if(isEmpty()){
			throw new IllegalStateException("Stack is empty: " + size);
		}
		
		T t;
		
		t = (T)underlying_array[size - 1];
		
		size -= 1;
		
		return t;
	}

	@Override
	public void push(T t) {
		
		if(full() || underlying_array == SHARED_EMPTY_ARRAY ){
			insureCapacityAndAllocate(size + 1);
		}
		
		size += 1;
		
		underlying_array[size - 1] = t;
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty(){
		// TODO Auto-generated method stub
		return size() == 0;
	}
	
	public boolean full(){
		return size == underlying_array.length;
	}

}
