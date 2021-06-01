package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @author 3804891
 * represante un ensemble multiple d'un type donnee gerer par une liste
 * @param <T> le type contenu dans le multiset
 */
public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{

	/**la liste qui contient les elements comme clé et leur nb d'occurence */
	private List<T> list;
	/**
	 * initialiser un naiveMultiSet
	 */
	public NaiveMultiSet(){
		list= new ArrayList<T>();
	}	
	
	/**
	 * initialiser un naiveMultiSet depuis une collection
	 * @param c la collection qui contient les elements a ajouter
	 */
	public NaiveMultiSet(Collection<T> c){
		list= new ArrayList<T>();
		for(T e : c)
			add(e);
		
	}
	/* (non-Javadoc)
	 * @see pobj.multiset.MultiSet#add(java.lang.Object, int)
	 */
	@Override
	public boolean add(T e, int count) {
		if(count<0)
			return false;
		for(int i=0;i<count;i++)
			list.add(e);
		return true;
		
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		list.add(e);
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object e) {
		list.remove(e);
		return true;
	}

	/* (non-Javadoc)
	 * @see pobj.multiset.MultiSet#remove(java.lang.Object, int)
	 */
	@Override
	public boolean remove(Object e, int count) {
		if(count((T) e) < count || count <0 )
			return false;
		for(int i=0;i<count;i++)
			list.remove(e);
		return true;
	}

	/* (non-Javadoc)
	 * @see pobj.multiset.MultiSet#count(java.lang.Object)
	 */
	@Override
	public int count(T o) {
		int out=0;
		for(T e : list)
			if (e.equals(o)) out++;
		return out;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#clear()
	 */
	@Override
	public void clear() {
		list.clear();
		
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#size()
	 */
	@Override
	public int size() {
		return list.size();
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	/**
	 * @author 3804891
	 * un comparateur propre au naiveMultiSet
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
		return new ArrayList<T>(new HashSet<T>(list));
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
	 * pas de verification demander pour le NaiveMultiSet
	 * @return true always
	 */
	@Override
	public boolean isConsistent() {
		// TODO Auto-generated method stub
		return true;
	}

}
