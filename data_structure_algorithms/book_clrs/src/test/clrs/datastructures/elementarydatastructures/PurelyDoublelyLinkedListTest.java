package test.clrs.datastructures.elementarydatastructures;

import static org.junit.Assert.*;

import java.util.ListIterator;

import main.clrs.datastructures.elementarydatastructures.LinkedList;
import main.clrs.datastructures.elementarydatastructures.PureDoublelyLinkedList;

import org.junit.Before;
import org.junit.Test;

public class PurelyDoublelyLinkedListTest {
	
	private  PureDoublelyLinkedList<Object> list = null;
	
	@Before
	public void constructLinkedList(){
		list = new PureDoublelyLinkedList<>();
	}
	
	
	@Test
	public void testInitialState(){
		assertTrue(list.getHead() == null && list.getTail() == null);
		assertTrue(list.size() == 0);
	}
	
	@Test
	public void testClear(){
		Object first, second;
		list.add(first = new Object());
		list.add(second = new Object());
		
		assertTrue(list.size() == 2);
		assertTrue(list.getHead() == first);
		assertTrue(list.getTail() == second);
		
		assertTrue(list.get(0) == first);
		assertTrue(list.get(1) == second);
		
		LinkedList.Node<Object> fnode = list.getHeadNode();
		LinkedList.Node<Object> snode = list.getTailNode();
		
		assertTrue(fnode != null);
		assertTrue(fnode.getValue() == first);
		
		assertTrue(snode != null);
		assertTrue(snode.getValue() == second);
		
		assertTrue(fnode.next() == snode);
		assertTrue(fnode.prev() == null);
		
		assertTrue(snode.next() == null);
		assertTrue(snode.prev() == fnode);
		
		list.clear();
		
		assertTrue(list.size() == 0);
		assertTrue(list.getHead() == null);
		assertTrue(list.getTail() == null);
		assertTrue(list.getHeadNode() == null);
		assertTrue(list.getTailNode() == null);
		
	}
	
	
	
}
