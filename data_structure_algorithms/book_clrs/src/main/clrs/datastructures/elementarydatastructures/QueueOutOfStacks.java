package main.clrs.datastructures.elementarydatastructures;

public class QueueOutOfStacks<T> implements Queue<T>{
	private Stack<T> sOne;
	private Stack<T> sTwo;
 	
	public QueueOutOfStacks(int capacity) {
		// TODO Auto-generated constructor stub
		sOne = new PureArrayStack<T>(capacity);
		sTwo = new PureArrayStack<T>(capacity);
	}
	
	
	
	@Override
	public int size() {
		return sOne.size() + sTwo.size();
	}

	@Override
	public T dequeue() {
		if(isEmpty()){
			throw new IllegalStateException("The Queue is empty, considering call empty method first!");
		}
		Stack<T> emptyStack;
		Stack<T> nonEmptyStack;
		if(sOne.isEmpty()){
			emptyStack = sOne;
			nonEmptyStack = sTwo;
		}else{
			nonEmptyStack = sOne;
			emptyStack = sTwo;
		}
		
		T t =  nonEmptyStack.pop();
		while(!nonEmptyStack.isEmpty()){
			emptyStack.push(t);
			t = nonEmptyStack.pop();
		}
		
		while(!emptyStack.isEmpty()){
			nonEmptyStack.push(emptyStack.pop());
		}
		
		return t;
	}

	@Override
	public void enqueue(T t) {
		if(sOne.isEmpty()){
			sTwo.push(t);
		}else{
			sOne.push(t);
		}
		
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	
	public static void main(String args[]){
		Queue<Integer> queue = new QueueOutOfStacks<>(10);
		for(int i = 0; i < 10; i++){
			queue.enqueue(i);
			assert queue.size() == i + 1;
		}
		
		while(!queue.isEmpty()){
			System.out.println(queue.dequeue());
		}
		
		
	}
	
}
