package test.clrs.datastructures.elementarydatastructures;

import static org.junit.Assert.*;
import main.clrs.datastructures.elementarydatastructures.PureArrayQueue;
import main.clrs.datastructures.elementarydatastructures.Queue;

import org.junit.Test;


public class PureArrayQueueTest {
	static final int [] utilArray = new int[10];
	static {
		for(int i = 0; i < 10; i++){
			utilArray[i] = i;
		}
		
	}
	
	@Test
	public void testEnqueue(){
		Queue<Object> queue = new PureArrayQueue<>(10);
		for(int i : utilArray){
			queue.enqueue(new Object());
			assert(queue.size() == i + 1);
		}
	}
	
	@Test
	public void testDequeue(){
		Queue<Object> queue = new PureArrayQueue<>(10);
		for(int i : utilArray){
			queue.enqueue(new Object());
			assert(queue.size() == i + 1);
		}
		for(int i : utilArray){
			assertTrue(queue.dequeue() != null);
			assertTrue(queue.size() == 9 -i);
		}
	}
	
	@Test
	public void testEmpty(){
		Queue<Object> queue = new PureArrayQueue<>();
		assertTrue(queue.isEmpty());
		queue.enqueue(new Object());
		queue.dequeue();
		assertTrue(queue.isEmpty());
	}
	
	@Test(expected = OutOfMemoryError.class)
	public void testOutOfMemoryError(){
		Queue<Object> o = new PureArrayQueue<>(Integer.MAX_VALUE - 3);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testEmptyPop(){
		Queue<Object> o = new PureArrayQueue<>();
		o.dequeue();
	}
	
}
