package test.clrs.datastructures.elementarydatastructures;

import static org.junit.Assert.*;
import main.clrs.datastructures.elementarydatastructures.PureArrayStack;
import main.clrs.datastructures.elementarydatastructures.Stack;

import org.junit.Test;


public class PureArrayStackTest {
	
	@Test
	public void testPush(){
		Stack<Object> s = new PureArrayStack<Object>();
		for(int i  = 0; i < 10; i++){
			s.push(new Object());
			assertTrue(s.size() == i + 1);
		}
	}
	
	@Test
	public void testPop(){
		Stack<Object> s = new PureArrayStack<Object>();
		for(int i  = 0; i < 10; i++){
			s.push(new Object());
		}
		
		for (int i = 0; i < 10; i++){
			assertTrue(s.pop() != null);
			assertTrue(s.size() == 9 -i);
		}
		
		assertTrue(s.empty());
	}
	
	@Test
	public void testEmpty(){
	    Stack<Object> s = new PureArrayStack<Object>();
	    assertTrue(s.empty());  
	}
	
	@Test(expected = OutOfMemoryError.class)
	public void testOutofMemoryError(){
		Stack<Object> s = new PureArrayStack<>(Integer.MAX_VALUE-7);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testEmptyPop(){
		Stack<Object> o = new PureArrayStack<>();
		o.pop();
	}
	
	
}
