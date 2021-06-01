package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 3804891
 * represante un emplacement ou on peut mettre un mot
 */
public class Emplacement {
	/**liste de case contegu qui represante un emplcameent horizontal ou vertical */
	private List<Case> liste = new ArrayList<Case>();
	
	/**
	 * ajouter une case a l'emplacement
	 * @param c la case a ajouté
	 */
	public void AjouterCase(Case c) {
		liste.add(c);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		Iterator<Case> it = liste.iterator();
		while(it.hasNext()) {
			s.append(it.next().getChar());
		}
		return s.toString();
	}

	/**
	 * acceder a la taille de l'emplacement
	 * @return la taille de la liste
	 */
	public int size() {
		return liste.size();
	}
	
	/**
	 * acceder a la i'éme case de l'emplacement
	 * @param i indice de la case qu'on veut acceder
	 * @return la i'éme case de l'emplacement
	 */
	public Case getCaseAt(int i) {
		return liste.get(i);
	}
	/**
	 * Verifier si l'emplecement a une case vide 
	 * @return true si l'emplacement a une case vide , false sinon
	 */
	public boolean hasCaseVide() {
		for(Case c : liste) {
			if(c.isVide())
				return true;
		}
		return false;
	}
	
	
}