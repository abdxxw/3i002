package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author 3804891
 * represante un ensemble multiple d'un type donnee gerer par un HashMap
 * @param <T> le type contenu dans le multiset
 */
public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{

	/** le hashmap qui contient les elements comme clé et leur nb d 'occurence*/
	private HashMap<T,Integer> hashMap;
	/** nombre d'element dans le hashmap*/
	private int size;
	/**
	 * initialiser un hashMultiSet
	 */
	public HashMultiSet(){
		hashMap= new HashMap<T,Integer>();
		size=0;
	}	
	
	/**
	 * initialiser un hashMultiSet depuis une collection
	 * @param c la collection qui contient les elements a ajouter
	 */
	public HashMultiSet(Collection<T> c){
		hashMap= new HashMap<T,Integer>();
		size=0;
		for(T e : c)
			add(e);
		
	}
	/* (non-Javadoc)
	 * @see pobj.multiset.MultiSet#add(java.lang.Object, int)
	 */
	@Override
	public boolean add(T e, int count) {
		if(count<0 || e==null)
			throw new IllegalArgumentException();
		if (count>0) {
			if(hashMap.containsKey(e))
				hashMap.put(e, hashMap.get(e)+count);
			else
				hashMap.put(e, count);
			size+=count;
			return true;
		}
		return false;
		
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		if(e==null)
			throw new IllegalArgumentException();
		if(hashMap.containsKey(e))
			hashMap.put(e, hashMap.get(e)+1);
		else
			hashMap.put(e,1);
		size++;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object e) {
		if(e==null)
			throw new IllegalArgumentException();
		if(!hashMap.containsKey(e))
			return false;
		if(hashMap.get(e) == 1)
			hashMap.remove(e);
		else
			hashMap.put((T) e, hashMap.get(e)-1);
		size--;
		return true;
	}

	/* (non-Javadoc)
	 * @see pobj.multiset.MultiSet#remove(java.lang.Object, int)
	 */
	@Override
	public boolean remove(Object e, int count) {
		if(e==null || count((T) e) < count || count <0 || !hashMap.containsKey(e))
			throw new IllegalArgumentException();
		if(count >0) {
			if(hashMap.get(e) == count)
				hashMap.remove(e);
			else
				hashMap.put((T) e, hashMap.get(e)-count);
			size-=count;
			return true;
			
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see pobj.multiset.MultiSet#count(java.lang.Object)
	 */
	@Override
	public int count(T o) {
		if(hashMap.containsKey(o))
			return hashMap.get(o);
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#clear()
	 */
	@Override
	public void clear() {
		size=0;
		hashMap.clear();
		
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#size()
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * @author 3804891
	 * une classe iterateur prope au HashMultiSet
	 */
	private class HashMultiSetIterator implements Iterator<T>{
		
		/** l'iterator sur les couple cle valeur du hashmap*/
		private Iterator<Map.Entry<T,Integer>> it;
		/** l'element courant*/
		private Map.Entry<T, Integer> now;
		/** la clé courante */
		private T key;
		/**l'indice ou on est dans l'ensemble*/
		private int ind;
		/**nombre d'element qui rest pour chaque clé courante */
		private int nbLeft;

		/**
		 * intialiser l'iterateur
		 * @param e le multiset a iterter
		 */
		public HashMultiSetIterator(HashMultiSet<T> e) {
			it = e.hashMap.entrySet().iterator();
			if(it.hasNext()) {		
				now = it.next();
				ind=0;
				nbLeft = now.getValue();
				key = now.getKey();
			}
		}
		/**
		 * verifie si on a encore un element ou non
		 * @return true si on a un element suivant , false sinon
		 */
		@Override
		public boolean hasNext() {
			return ind < size();
		}

		/**
		 * acceder au element suivant en parcourant tous les occurence de chaque element
		 * l'element suivant
		 */
		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			if(nbLeft == 0) {
				now = it.next();
				nbLeft = now.getValue();
				key = now.getKey();
			}
			nbLeft--;
			ind++;
			return key;
				
		}
		
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return new HashMultiSetIterator(this);
	}

	/**
	 * @author 3804891
	 *	un comparateur propre au hashMultiSet
	 */
	private class MultiSetComparator implements Comparator<T> {
		
		/**le multiset a comparer*/
		MultiSet<T> ms;
		/**
		 * initialiser le comparateur
		 * @param ms le multiset a comparer
		 */
		public MultiSetComparator(MultiSet<T> ms) {
			this.ms = ms;
		}
		/**
		 * comparer pour avoir un ordre decroissant
		 * -1 si le nombre d'occ de o2 est inferieur a o1, 1 si superieur 0 si egeaux 
		 */
		public int compare(T o1, T o2) {
			return Integer.compare(ms.count(o2), ms.count(o1));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see pobj.multiset.MultiSet#comparator()
	 */
	@Override
	public MultiSetComparator comparator() {
		return new MultiSetComparator(this);
	}
	
	/* (non-Javadoc)
	 * @see pobj.multiset.MultiSet#elements()
	 */
	@Override
	public List<T> elements() {
		return new ArrayList<T>(new HashSet<T>(new ArrayList<T>(this)));
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder("[");
		for (T x : elements()) {
			out.append(x);
			out.append(":");
			out.append(count(x));
			out.append("; ");
		}
		if(out.length()>2) {
			out.deleteCharAt(out.length()-1);
			out.deleteCharAt(out.length()-1);	
		}
		out.append("]");
		return out.toString();
	}
	
	/**
	 * verifie que le hashset contient que des valeur positive et que la somme des valeur egale au size
	 * @return true si le set est consistent , false sinon
	 */
	@Override
	public boolean isConsistent() {
		int nb=0;
		for (T x : elements()) {
			int temp = count(x);
			if(temp <= 0)
				return false;
			nb+=temp;
		}
		return nb == size();
	}

}