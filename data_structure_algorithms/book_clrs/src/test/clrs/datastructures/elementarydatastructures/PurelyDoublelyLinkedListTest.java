package test.clrs.datastructures.elementarydatastructures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import main.clrs.datastructures.elementarydatastructures.LinkedList;
import main.clrs.datastructures.elementarydatastructures.PureDoublelyLinkedList;

import org.junit.After;
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
		assertTrue(list.isEmpty());
		assertTrue(list.getHead() == null);
		assertTrue(list.getTail() == null);
		assertTrue(list.getHeadNode() == null);
		assertTrue(list.getTailNode() == null);
		
	}
	
	
	@Test
	public void testAdd(){
		list.clear();
		assertTrue(list.isEmpty());
		Object f,s,t;
		list.add(f = new Object());
		list.add(s=new Object());
		list.add(t = new Object());
		
		assertTrue(list.size() == 3);
		assertTrue(list.get(0) == f);
		assertTrue(list.get(1) == s);
		assertTrue(list.get(2) == t);
	}
	
	
	@Test
	public void testDelete(){
		Object f,s,t;
		list.add(f = new Object());
		list.add(s=new Object());
		list.add(t = new Object());
		
		list.delete(1);
		
		assertTrue(list.size() == 2);
		assertTrue(f == list.get(0));
		assertTrue(list.get(1) == t);
		assertTrue(list.getHead() == f);
		assertTrue(list.getTail() == t);
		assertTrue(list.getHeadNode().next() == list.getTailNode());
	}
	
	@Test
	public void testGetNode(){
		Object f,s,t;
		list.add(f = new Object());
		list.add(s=new Object());
		list.add(t = new Object());
		list.add(s);
		
		assertTrue(s == ((LinkedList.Node<Object>)list.getNode(1)).getValue());
		assertTrue(s == ((LinkedList.Node<Object>)list.getNode(3)).getValue());
		assertTrue(f == ((LinkedList.Node<Object>)list.getNode(0)).getValue());
		assertTrue(t == ((LinkedList.Node<Object>)list.getNode(2)).getValue());
		list.clear();
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testFirstIndices(){
		Object f,s,t;
		list.add(f = new Object());
		list.add(s=new Object());
		list.add(t = new Object());
		list.add(s);
		
		int first  = list.firstIndexOf(f);
		assertTrue(first == 0);
		int second = list.firstIndexOf(s);
		assertTrue(second == 1);
		int third = list.firstIndexOf(t);
		assertTrue(third == 2);
		int fourth = list.lastIndexOf(s);
		assertTrue(fourth == 3);
		int[] ns = list.searchOccurrence(s);
		assertArrayEquals(new int[]{1,3}, ns);
	}
	
	@Test
	public void testListIterator(){
		Object f,s,t;
		list.add(f = new Object());
		list.add(s=new Object());
		list.add(t = new Object());
		list.add(s);
		
		Iterator<Object> itr = list.iterator();
		ArrayList<Object> result1 = new ArrayList<>();
		ArrayList<Object> result2 = new ArrayList<>();
		while(itr.hasNext()){
			Object i = itr.next();
			result1.add(i);
			assertTrue(i != null);
		}
		
		
		for(Object o : list){
			assertTrue(o != null);
			result2.add(o);
		}
		
		assertArrayEquals(result1.toArray(), result2.toArray());
		assertTrue(result1.get(0) == f);
		assertTrue(result1.get(1) == s);
		assertTrue(result1.get(2) == t);
		assertTrue(result1.get(3) == s);
	}
	
	
	@After
	public void destroy(){
		list.clear();
		assertTrue(list.isEmpty());
	}
	
}
