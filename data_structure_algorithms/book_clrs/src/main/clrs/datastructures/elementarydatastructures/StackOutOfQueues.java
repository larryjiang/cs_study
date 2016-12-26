package main.clrs.datastructures.elementarydatastructures;

/**
 * As an exercise of clrs 10.1.6
 * @author larry
 *
 * @param <T>
 */
public class StackOutOfQueues<T> implements Stack<T> {
	private Queue<T> qOne;
	private Queue<T> qTwo;
	
	public static enum FAVORS{
		FAVOR_PUSH, FAVOR_POP;
	}
	
	private FAVORS favorPushOrPop;
	
	public StackOutOfQueues(int capacity) {
		qOne = new PureArrayQueue<>(capacity);
		qTwo = new PureArrayQueue<>(capacity);
		favorPushOrPop = FAVORS.FAVOR_PUSH;
	}
	
	public StackOutOfQueues(){
		qOne = new PureArrayQueue<>();
		qTwo = new PureArrayQueue<>();
		favorPushOrPop = FAVORS.FAVOR_PUSH;
	}

	@Override
	public int size() {
		return qOne.size() + qTwo.size();
	}

	@Override
	public T pop() {
		if(favorPushOrPop == FAVORS.FAVOR_PUSH){
			return popElement_A();
		}else{
			return popElement_B();
		}
	}

	@Override
	public void push(T t) {
		if(favorPushOrPop == FAVORS.FAVOR_PUSH){
			pushElement_A(t);
		}else{
			pushElement_B(t);
		}
		
	}
	
	public StackOutOfQueues<T> setFavorPushOrPop(FAVORS favor){
		this.favorPushOrPop = favor;
		return this;
	}
	
	private T popElement_B(){
		if(isEmpty()){
			throw new IllegalStateException("No elements contained in the stack!!!");
		}
		if(qOne.isEmpty()){
			return qTwo.dequeue();
		}else{
			return qOne.dequeue();
		}
	}
	
	private void pushElement_B(T t){
		if(qOne.isEmpty() && qTwo.isEmpty()){
			qOne.enqueue(t);
		}else if(qOne.isEmpty()){
			swapInPlace();
		}
		
		qTwo.enqueue(t);
		while(!qOne.isEmpty()){
			qTwo.enqueue(qOne.dequeue());
		}
	}
	
	private void pushElement_A(T t){
		if(qOne.isEmpty()){
			qTwo.enqueue(t);
		}else{
			qOne.enqueue(t);
		}
	}
	
	private T popElement_A(){
		if(isEmpty()){
			throw new IllegalStateException("Stack empty, can not pop!!!");
		}
		
		if(qOne.isEmpty()){
			swapInPlace();
		}
		
		T r = qOne.dequeue();
		
		while(!qOne.isEmpty()){
			qTwo.enqueue(r);
			r = qOne.dequeue();
		}
		
		return r;
	}
	
	
	@Override
	public boolean isEmpty() {
		return size() == 0 ;
	}
	
	private void swapInPlace(){
		Queue<T> q;
		q = qOne;
		qOne = qTwo;
		qTwo = q;
	}
	
	
	
	public static void main(String args[]){
		StackOutOfQueues<Integer> s = new StackOutOfQueues<Integer>();
		s.setFavorPushOrPop(StackOutOfQueues.FAVORS.FAVOR_POP);
		for(int i = 0; i < 10; i++){
			s.push(i);
			assert s.size() == i + 1;
		}
		
		for (int i = 0; i < 10; i++){
			System.out.println(s.pop());
		}
	}



}
