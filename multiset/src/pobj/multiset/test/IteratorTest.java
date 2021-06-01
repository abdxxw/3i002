package pobj.multiset.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import pobj.multiset.HashMultiSet;

public class IteratorTest {

	@Test
	public void testIterator() {
		HashMultiSet<String> m = new HashMultiSet<>();
		List<String> l = new ArrayList<>();
		m.add("a",3);
		m.add("b");
		m.add("c",2);
		for (String s : m) {
			l.add(s);
		}
		Collections.sort(l);
		assertEquals(6, l.size());
		assertEquals("a", l.get(0));
		assertEquals("a", l.get(1));
		assertEquals("a", l.get(2));
		assertEquals("b", l.get(3));
		assertEquals("c", l.get(4));
		assertEquals("c", l.get(5));
	}

}
