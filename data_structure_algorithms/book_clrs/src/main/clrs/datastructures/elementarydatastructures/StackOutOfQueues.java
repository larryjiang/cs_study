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
		if(empty()){
			throw new IllegalStateException("No elements contained in the stack!!!");
		}
		if(qOne.empty()){
			return qTwo.dequeue();
		}else{
			return qOne.dequeue();
		}
	}
	
	private void pushElement_B(T t){
		if(qOne.empty() && qTwo.empty()){
			qOne.enqueue(t);
		}else if(qOne.empty()){
			swapInPlace();
		}
		
		qTwo.enqueue(t);
		while(!qOne.empty()){
			qTwo.enqueue(qOne.dequeue());
		}
	}
	
	private void pushElement_A(T t){
		if(qOne.empty()){
			qTwo.enqueue(t);
		}else{
			qOne.enqueue(t);
		}
	}
	
	private T popElement_A(){
		if(empty()){
			throw new IllegalStateException("Stack empty, can not pop!!!");
		}
		
		if(qOne.empty()){
			swapInPlace();
		}
		
		T r = null;
		
		do{
			T t = qOne.dequeue();
			if( qOne.empty()){
				r = t;
			}else{
				qTwo.enqueue(t);
			}
		}while(!qOne.empty());
		
		return r;
	}
	
	
	@Override
	public boolean empty() {
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
