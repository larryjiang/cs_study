package test.clrs.datastructures.elementarydatastructures;

import static org.junit.Assert.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.TypedSingletonIterator;

import main.clrs.datastructures.elementarydatastructures.ResizableArray;

public class ResizableArrayTest {
	
	private ResizableArray<Object> array = null;
	
	@Before
	public void initialize(){
		array = new ResizableArray<>();
	}
	
	@Test
	public void testInitialState(){
		assertTrue(array.size() == 0);
		assertTrue(array.isEmpty() == true);
	}
	
	@Test
	public void testClear(){
		array.clear();
		assertTrue(array.size() == 0);
		
		Object o1, o2;
		o1 = new Object();
		o2 = new Object();
		
		array.add(o1);
		assertTrue(array.size() == 1);
		array.add(o2);
		assertTrue(array.size() == 2);
		array.clear();
		assertTrue(array.size() == 0);
	}
	
	@Test
	public void testGet(){
		array.clear();
		assertTrue(array.size() == 0);
		
		Object o1, o2;
		o1 = new Object();
		o2 = new Object();
		
		array.add(o1);
		assertTrue(array.size() == 1);
		assertTrue(array.get(0) == o1);
		
		try{
			array.get(1);
		}catch(Exception e){
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		array.add(o2);
		assertTrue(array.get(1) == o2);
	
		array.add(o1);
		array.add(o2);

		array.add(o2);

		array.add(o2);

		array.add(o2);

		
		
		array.delete(3);
		
		try{
			array.get(array.size());
		}catch(Exception e){
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSet(){
		array.clear();
		assertTrue(array.size() == 0);
		
		Object o1, o2;
		o1 = new Object();
		o2 = new Object();
		
		
		array.add(o1);
		array.add(o2);

		array.add(o2);

		array.add(o2);

		array.add(o2);

		array.set(2, o1);
		
		assertTrue(array.get(2) == o1);
		
		array.set(4, o1);
		assertTrue(array.get(4) == o1);
		
		array.set(6, new Object());
		
	}
	
	
	@Test
	public void testAdd(){
		array.clear();
		assertTrue(array.size() == 0);
		
		Object o1, o2;
		o1 = new Object();
		o2 = new Object();
		
		array.add(o1);
		assertTrue(array.size() == 1);
		assertTrue(array.get(0) == o1);
		
		array.add(o2);
		array.add(o1);
		array.add(o2);
		array.add(o1);
		array.add(o2);
		array.add(o1);
		array.add(o2);
		array.add(o1);
		array.add(o2);
		array.add(o1);
		assertTrue(array.size() == 11);
	}

	
	@Test
	public void testDelete(){
		array.clear();
		assertTrue(array.size() == 0);
		
		Object o1, o2, o3,o4,o5,o6,o7,o8,o9,o10;
		o1 = new Object();
		o2 = new Object();
		
		o3 = new Object();
		o4 = new Object();
		
		o5 = new Object();
		o6 = new Object();
		
		o7 = new Object();
		o8 = new Object();
		
		o9 = new Object();
		o10 = new Object();
		
		array.add(o1);
		array.add(o2);
		array.add(o3);
		array.add(o4);
		array.add(o5);
		array.add(o6);
		array.add(o7);
		array.add(o8);
		array.add(o9);
		array.add(o10);
		array.add(o10);
		assertTrue(array.size() == 11);
		array.delete(4);
		assertTrue(array.size() == 10);
		assertTrue(array.get(4) == o6); 
		try{
			array.delete(11);
		}catch(Exception e){
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		array.delete(0);
		assertTrue(array.size() == 9);
		assertTrue(array.get(0) == o2);
		
		
		while(!array.isEmpty()){
			array.delete(0);
		}
		
		assertTrue(array.size() == 0);
	}
	
	
	
	@Test
	public void testInsert(){
		array.clear();
		assertTrue(array.size() == 0);
		
		Object o1, o2, o3,o4,o5,o6,o7,o8,o9,o10;
		o1 = new Object();
		o2 = new Object();
		
		o3 = new Object();
		o4 = new Object();
		
		o5 = new Object();
		o6 = new Object();
		
		o7 = new Object();
		o8 = new Object();
		
		o9 = new Object();
		o10 = new Object();
		
		array.add(o1);
		array.add(o2);
		array.add(o3);
		array.add(o4);
		array.add(o5);
		array.add(o6);
		array.add(o7);
		array.add(o8);
		array.add(o9);
		array.add(o10);
		array.add(o10);
		assertTrue(array.size() == 11);
		
		array.insert(8, o1);
		assertTrue(array.size() == 12);
		
		assertTrue(array.get(8) == o1);
		assertTrue(array.get(9) == o9);
		
		array.insert(0, new Object());
		assertTrue(array.get(1) == o1);
		assertTrue(array.get(0) != null);
	}
	
	@Test
	public void testIterator(){
		array.clear();
		assertTrue(array.size() == 0);
		
		Iterator<Object> ite = array.iterator();
		assertTrue(!ite.hasNext());
		
		Object o1, o2, o3,o4,o5,o6,o7,o8,o9,o10;
		o1 = new Object();
		o2 = new Object();
		
		o3 = new Object();
		o4 = new Object();
		
		o5 = new Object();
		o6 = new Object();
		
		o7 = new Object();
		o8 = new Object();
		
		o9 = new Object();
		o10 = new Object();
		
		array.add(o1);
		array.add(o2);
		array.add(o3);
		array.add(o4);
		array.add(o5);
		array.add(o6);
		array.add(o7);
		array.add(o8);
		array.add(o9);
		array.add(o10);
		array.add(o10);
		
		assertTrue(array.size() == 11);
		assertTrue(ite.hasNext());;
		
		try{
			Object next = ite.next();
		}catch(Exception e){
			assertTrue(e instanceof ConcurrentModificationException);
		}
		
		ite = array.iterator();
		
		int size = 0;
		while(ite.hasNext()){
			size++;
			ite.next();
		}
		
		assertTrue(array.size() == size);
		
		ResizableArray<Object> copy = new ResizableArray<>();
		ite = array.iterator();
		
		while(ite.hasNext()){
			copy.add(ite.next());
		}
		
		
		for(int i = 0; i < size; i++){
			assertTrue(array.get(i) == copy.get(i));
		}
		
		ite = array.iterator();
		
		try{
			ite.remove();
		}catch(Exception e){
			assertTrue(e instanceof IllegalStateException);
		}
		
		Object o = ite.next();
		assertTrue(o == o1);
		
		ite.remove();
		
		assertTrue(array.size() == 10);
		assertTrue(array.get(0) == o2);
		
		try{
			ite.remove();
		}catch(Exception e){
			assertTrue(e instanceof IllegalStateException);
		}
		
		array.add(new Object());
		try{
			ite.next();
		}catch(Exception e){
			assertTrue(e instanceof ConcurrentModificationException);
		}
		
		array.clear();
		
		array.add(o1);
		array.add(o2);
		array.add(o3);
		array.add(o4);
		array.add(o5);
		array.add(o6);
		array.add(o7);
		array.add(o8);
		array.add(o9);
		array.add(o10);
		array.add(o10);
		
		
		ite = array.iterator();
		
		ite.next();
		ite.next();
		ite.remove();
		ite.next();
		ite.remove();
		ite.next();
		assertTrue(ite.next() == o5);
		
		while(ite.hasNext()){
			ite.next();
		}
		
		ite.remove();
		assertTrue(array.size() == 8);
		
		try{
			ite.remove();
		}catch(Exception e){
			assertTrue(e instanceof IllegalStateException);
		}
		assertTrue(!ite.hasNext());
		
		
	}
	
	
	@Test
	public void testRemove(){
		array.clear();
		assertTrue(array.size() == 0);
		
		Object o1, o2, o3,o4,o5,o6,o7,o8,o9,o10;
		o1 = new Object();
		o2 = new Object();
		
		o3 = new Object();
		o4 = new Object();
		
		o5 = new Object();
		o6 = new Object();
		
		o7 = new Object();
		o8 = new Object();
		
		o9 = new Object();
		o10 = new Object();
		
		array.add(o1);
		array.add(o2);
		array.add(o3);
		array.add(o4);
		array.add(o5);
		array.add(o6);
		array.add(o7);
		array.add(o8);
		array.add(o9);
		array.add(o10);
		array.add(o10);
		
		assertTrue(array.size() == 11);
		boolean result = array.remove(new Object());
		assertTrue(!result);
		assertTrue(array.size() == 11);
		
		array.remove(o1);
		assertTrue(array.size() == 10);
		assertTrue(array.get(0) == o2);
		
		array.remove(o10);
		assertTrue(array.size() == 9);
		assertTrue(array.get(8) == o10);
		
		
	}
	
	
	
	
	
	@Test
	public void testIndices(){
		array.clear();
		assertTrue(array.size() == 0);
		
		Object o1, o2, o3,o4,o5,o6,o7,o8,o9,o10;
		o1 = new Object();
		o2 = new Object();
		
		o3 = new Object();
		o4 = new Object();
		
		o5 = new Object();
		o6 = new Object();
		
		o7 = new Object();
		o8 = new Object();
		
		o9 = new Object();
		o10 = new Object();
		
		array.add(o1);
		array.add(o2);
		array.add(o3);
		array.add(o4);
		array.add(o5);
		array.add(o6);
		array.add(o7);
		array.add(o8);
		array.add(o9);
		array.add(o10);
		array.add(o10);
		assertTrue(array.size() == 11);
		
		assertTrue(array.firstIndexOf(o1) == 0);
		assertTrue(array.firstIndexOf(o2) == 1);
		assertTrue(array.lastIndexOf(o1) == 0);
		assertTrue(array.lastIndexOf(o2) == 1);
		
		assertTrue(array.firstIndexOf(o10) == 9);
		assertTrue(array.lastIndexOf(o10) == 10);
		
		
		assertArrayEquals(new int[]{9,10}, array.searchOccurrence(o10));
	}
	
}
