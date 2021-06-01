package pobj.multiset.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pobj.multiset.HashMultiSet;
import pobj.multiset.MultiSet;
import pobj.multiset.MultiSetDecorator;

public class HashMultiSetTest3 {
	
	
	private MultiSet<String> m;
	
	@Before
	public void creatHashMultiSet() {
		m = new MultiSetDecorator<String>(new HashMultiSet<String>());
	}
	
	@Test 
	public void testAdd1() {
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.count("a"));
	}

	@Test(expected = IllegalArgumentException.class) 
	public void testAdd2() {
		m.add("a");
		m.add("a",-1);
	}
	
	@Test
	public void testAdd3() {
		m.add("a");
		m.add("a",0);
		assertEquals(1, m.count("a"));
	}
	
	@Test 
	public void testRemove1() {
		m.add("a",5);
		m.remove("a",2);
		assertEquals(3, m.count("a"));
	}

	@Test(expected = IllegalArgumentException.class) 
	public void testRemove2() {
		m.add("a");
		m.remove("a",-1);
	}
	
	@Test
	public void testRemove3() {
		m.add("a");
		m.remove("a",0);
		assertEquals(1, m.count("a"));
	}
	@Test
	public void testSize() {
		m.add("a");
		m.add("b",3);
		m.add("c",10);
		assertEquals(14, m.size());
		m.remove("c",5);
		m.remove("b");
		assertEquals(8, m.size());
	}	
	
	@Test
	public void testToString() {
		m.add("a");
		m.add("b",2);
		m.add("c",99);
		assertEquals(m.toString(), "[a:1; b:2; c:99]");
	}	
	
	@Test
	public void testClear() {
		m.add("a");
		m.add("b",3);
		m.add("c",10);
		assertEquals(14, m.size());
		m.clear();
		assertEquals(0, m.size());
		assertEquals(m.toString(),"[]");
	}
	
	
	@Test 
	public void testAddRemove1() {
		m.add("a",5);
		m.add("b",15);
		m.add("b");
		m.add("c",8);
		m.add("d");
		assertEquals(30, m.size());
		m.remove("a");
		m.remove("a",2);
		m.remove("b",5);
		m.remove("c");
		assertEquals(21, m.size());
	}	
	
	@Test 
	public void testAddRemove2() {
		m.add("a",5);
		m.remove("a",5);
		assertEquals(0, m.count("a"));
	}	
	
	@Test 
	public void testCount() {
		assertEquals(0, m.count("z"));
	}
}
