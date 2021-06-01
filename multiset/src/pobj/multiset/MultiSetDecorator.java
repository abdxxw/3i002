package pobj.multiset;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author 3804891
 * Decorator pour verifier que le multiset est consistant
 * @param <T> le type du multiset
 */
public class MultiSetDecorator<T> extends AbstractCollection<T> implements MultiSet<T> {
	MultiSet<T> decorated;
	
	public MultiSetDecorator(MultiSet<T> x) {
		decorated = x;
	}

	@Override
	public boolean isConsistent() {
		return decorated.isConsistent();
	}
	
	@Override
	public boolean add(T e, int count) {
		boolean out = decorated.add(e,count);
		assert isConsistent();
		return out;
	}

	@Override
	public boolean remove(Object e, int count) {
		boolean out = decorated.remove(e,count);
		assert isConsistent();
		return out;
	}	
	
	@Override
	public boolean add(T e) {
		boolean out = decorated.add(e);
		assert isConsistent();
		return out;
	}

	@Override
	public boolean remove(Object e) {
		boolean out = decorated.remove(e);
		assert isConsistent();
		return out;
	}

	@Override
	public int count(T o) {
		return decorated.count(o);
	}

	@Override
	public List<T> elements() {
		return decorated.elements();
	}

	@Override
	public Comparator<T> comparator() {
		return decorated.comparator();
	}

	@Override
	public Iterator<T> iterator() {
		return decorated.iterator();
	}

	@Override
	public int size() {
		return decorated.size();
	}
	
	@Override
	public void clear() {
		decorated.clear();
	}
	
	@Override
	public String toString() {
		return decorated.toString();
	}

	public boolean addAll(Collection<? extends T> c) {
		return decorated.addAll(c);
	}

	public boolean contains(Object o) {
		return decorated.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return decorated.containsAll(c);
	}

	public boolean equals(Object o) {
		return decorated.equals(o);
	}

	public int hashCode() {
		return decorated.hashCode();
	}

	public boolean isEmpty() {
		return decorated.isEmpty();
	}

	public boolean removeAll(Collection<?> c) {
		return decorated.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return decorated.retainAll(c);
	}

	public Object[] toArray() {
		return decorated.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return decorated.toArray(a);
	}

	

}
