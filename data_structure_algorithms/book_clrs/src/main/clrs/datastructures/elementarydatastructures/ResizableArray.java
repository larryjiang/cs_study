package main.clrs.datastructures.elementarydatastructures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class ResizableArray<T> implements List<T>, RandomAccess{
	
	private int size;
	
	private static final Object[] SHARED_EMPTY_ARRAY = {};
	
    private static final int MAXIMUM_SIZE = Integer.MAX_VALUE - 8;
	
	private static int DEFAULT_SIZE = 8;
	
	transient Object[] underlying_array;
	
	transient int modCount;
	
	public ResizableArray() {
		underlying_array = SHARED_EMPTY_ARRAY;
	}
	
	public ResizableArray(int size){
		if(size < 0){
			throw new IllegalArgumentException("Illegal Array Size: " + size);
		}
		ensureCapacity(size);
	}
	
	public ResizableArray(int size, boolean exact){
		if(size < 0){
			throw new IllegalArgumentException("Illegal Array Size: " + size);
		}
		
		if(exact){
			underlying_array = new Object[size];
		}else{
			ensureCapacity(size);
		}
		
	}
	
	private void checkIndex(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Invid Index " + index);
		}
	}
	
	private void checkAvaibleIndex(int index){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
	}
	
	private void checkPosition(int position){
		if(position < 0 || position >= underlying_array.length){
			throw new IndexOutOfBoundsException("Invaid position " + position);
		}
		
		
	}
	
	private void ensureCapacity(int minSize){
		if(minSize > underlying_array.length){
			grow(minSize);
			modCount++;
		}
	}
	
	
	private void grow(int minSize){
		
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
		if(minSize <= DEFAULT_SIZE / 2){
			minSize = DEFAULT_SIZE / 2;
		}else if(minSize < DEFAULT_SIZE){
			minSize = DEFAULT_SIZE;
		}else if(minSize < MAXIMUM_SIZE / 2){
			minSize = minSize + minSize / 2;
		}else{
			minSize = minSize + (MAXIMUM_SIZE - minSize) / 2;
		}
		return minSize;
	}
	
	
	
	
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return firstIndexOf(o) != -1;
	}

	@Override
	public boolean add(T e) {
		int minSize = size + 1;
		ensureCapacity(minSize);
		underlying_array[size++] = e;
		modCount++;
		return true;
	}

	@Override
	public boolean addAll(Container<T> t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		int i = firstIndexOf(o);
		if(i < 0){
			return false;
		}
		
		delete(i);
		return true;
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
		modCount++;
		for(int i = 0; i< size; i++){
			underlying_array[i] = null;
		}
		size = 0;
	}

	@Override
	public boolean retainAll(Container<T> s) {
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
		return new ListIterator();
	}

	protected class ListIterator implements Iterator<T>{

		int next = 0;
		int expectedModeCount = modCount;
		//when we do removing, this is critical. If it is not present, 
		// we make do multiple removing which will result in wrong state 
		int lastReturned = -1;
		
		public ListIterator() {
		}
		
		public ListIterator(int firstIndex){
			checkIndex(firstIndex);
			this.next = firstIndex;
		}
		
		@Override
		public boolean hasNext() {
			return next < size;
		}

		@Override
		public T next() {
			checkForModification();
			try{
			T oldValue = get(next);
			lastReturned = next;
			next = next + 1;
			return oldValue;
			}catch(IndexOutOfBoundsException e){
				checkForModification();
				throw new ConcurrentModificationException();
			}
		}
		
		
		public void remove(){
			checkForModification();
			// to ensure remove is invoked once after next() is called
			if(lastReturned == -1){
				throw new IllegalStateException();
			}
			
			try{
			ResizableArray.this.delete(lastReturned);
			next = lastReturned;
			lastReturned = -1;
			expectedModeCount = modCount;
			}catch(IndexOutOfBoundsException e){
				throw new ConcurrentModificationException();
			}
		}
		
		
		final void checkForModification(){
			if(expectedModeCount != modCount){
				throw new ConcurrentModificationException();
			}
		}
		
	}
	
	
	@Override
	public void insert(int index, T element) {
		// TODO Auto-generated method stub
		checkAvaibleIndex(index);
		ensureCapacity(index + 1);
		int movedNum = size - index;
		if(movedNum > 0){
			System.arraycopy(underlying_array,index,underlying_array, index + 1, movedNum);
		}
		underlying_array[index] = element;
		size++;
		modCount++;
	}

	@Override
	public void delete(int index) {
		// TODO Auto-generated method stub
		checkIndex(index);
		int movedNum = size - index - 1;
		
		if(movedNum > 0){
			System.arraycopy(underlying_array, index + 1, underlying_array, index, movedNum);
		}
		underlying_array[--size] = null;
		modCount++;
	}

	@Override
	public int firstIndexOf(Object o) {
		// TODO Auto-generated method stub
		for(int i = 0; i < size; i++){
			if(underlying_array[i] == o){
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		int index = size -1;
		
		while(index >= 0){
			if(underlying_array[index] == o){
				break;
			}
			index--;
		}
		
		return index;
	}

	@Override
	public int[] searchOccurrence(Object o) {
		// TODO Auto-generated method stub
		LinkedList<Integer> list = new PureDoublelyLinkedList<>();
		for(int i = 0; i < size; i++){
			if(underlying_array[i] == o){
				list.add(i);
			}
		}
		int s = list.size();
		int[] result = new int[s];
		
		for(int i = 0; i < s; i++){
			result[i] = list.get(i);
		};
		return result;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index);
		underlying_array[index] = element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		checkIndex(index);
		return (T)underlying_array[index];
	}

	@Override
	public void add(int index, T element) {
		checkPosition(index);
		if(index == size -1){
			add(element);
		}else{
			insert(index, element);
		}
	}

}
