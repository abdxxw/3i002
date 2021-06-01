package pobj.multiset;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * @author 3804891
 *	represante un ensemble multiple d'un type donnee
 * @param <T> le type contenu dans le multiset
 */
public interface MultiSet<T> extends Collection<T>{
	/**
	 * ajouter un nombre d'un element au multiset
	 * @param e l'element a ajouté
	 * @param count le nombre d'element a ajouter
	 * @return	true si l'ajout se fait sans probleme , false sinon
	 */
	public boolean add(T e, int count);
	/**
	 * ajouter un seul element au multiset
	 * @param e l'element a ajouté
	 * @return	true si l'ajout se fait sans probleme , false sinon
	 */
	public boolean add(T e);
	/**
	 * supprimer un seul element du multiset
	 * @param e l'element a supprimer
	 * @return	true si la suppression se fait sans probleme , false sinon
	 */
	public boolean remove(Object e);
	/**
	 * supprimer un nomnbre d'un element du multiset
	 * @param e l'element a supprimer
	 * @param count nombre d'element a supprimer
	 * @return true si la suppression se fait sans probleme , false sinon
	 */
	public boolean remove(Object e, int count);
	/**
	 * compter le nombre d'occurence  d'un element
	 * @param o l'element a compter
	 * @return nombre d'occurence de l'element
	 */
	public int count(T o);
	/**
	 * vider tous l'ensemble
	 */
	public void clear();	
	/**
	 * acceder au nombre d'element de l'ensemble
	 * @return nombre d'element de l'ensemble
	 */
	public int size();
	/**
	 * retourner tous les elements de l'ensemble
	 * @return une liste des elements de l'ensemble
	 */
	public List<T> elements();
	
	/**
	 * comparateur redefinie
	 * @return un comparateur redefinie
	 */
	public Comparator<T> comparator();
	
	/**
	 * verifie que le set est consistent
	 * @return true si le set est consistent , false sinon
	 */
	public boolean isConsistent();
}