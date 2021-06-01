package pobj.multiset.test;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import pobj.multiset.HashMultiSet;

public class CollectionTest {

	@Test
	public void testImplementsCollection() {
		HashMultiSet<String> m = new HashMultiSet<>();
		assertTrue(m instanceof Collection);
	}
	
}
