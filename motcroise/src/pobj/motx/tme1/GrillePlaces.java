package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 3804891
 *represante tous les emplacement dans une grille
 */
public class GrillePlaces {
	
	/**la grille */
	private Grille g;
	/**liste des emplacement dans une grille donnée	 */
	private List<Emplacement> places = new ArrayList<Emplacement>();
	/** nombre d'emplacement horizotaux */
	private int nbHorizontal;
	
	/**
	 * initialiser la liste d'emplacement 
	 * @param grille qu'on veut retrouver ces places
	 */
	public GrillePlaces (Grille grille) {
		g=grille;
		for(int i=0;i<grille.nbLig();i++) {
			cherchePlaces(getLig(i));
		}
		nbHorizontal = places.size();

		for(int i=0;i<grille.nbCol();i++) {
			cherchePlaces(getCol(i,grille));
		}
	}
	/**
	 * acceder a la liste des places
	 * @return la liste  des places
	 */
	public List<Emplacement> getPlaces() {
		return places;
	}
	
	/**
	 * acceder au nombre des places horizantaux
	 * @return nombre des places horizantaux
	 */
	public int getNbHorizontal() {
		return nbHorizontal;
	}
	/**
	 * retourner une liste des case d'une ligne
	 * @param lig la ligne qu'on veut retourner ces cases
	 * @param g la grille conserner 
	 * @return	liste des case d'une ligne de grille
	 */
	private List<Case> getLig(int lig){
		List<Case> out = new ArrayList<Case>();
		for(int i=0;i<g.nbCol();i++)
			out.add(g.getCase(lig, i));
		return out;
	}
	
	
	/**
	 * retourner une liste des case d'une colone
	 * @param col la colone qu'on veut retourner ces cases
	 * @param g la grille conserner 
	 * @return	liste des case d'une colone de grille
	 */
	private List<Case> getCol(int col,Grille g){
		List<Case> out = new ArrayList<Case>();
		for(int i=0;i<g.nbLig();i++)
			out.add(g.getCase(i, col));
		return out;
	}
	
	/**
	 * cherche les places dans une ligne ou une colone
	 * @param cases la liste des cases ( ligne ou colone)
	 */
	private void cherchePlaces(List<Case> cases) {
		Emplacement out = new Emplacement();
		Iterator<Case> it = cases.iterator();
		while(it.hasNext()) {
			Case temp = it.next();
			if(!temp.isPleine())
				out.AjouterCase(temp);
			else {
				if(out.size()>1)
					places.add(out);
				out = new Emplacement();
			}
		}

		if(out.size()>1)
			places.add(out);
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		/*StringBuilder s = new StringBuilder();
		int cpt = 0;
		s.append("\nMots Horizontaux : \n");
		Iterator<Emplacement> it = places.iterator();
		while(it.hasNext()) {
			if(cpt == nbHorizontal)	s.append("\nMots Verticaux : \n");
			cpt++;
			s.append(it.next().toString());
			s.append("\n");
		}
		return s.toString();*/
		return g.toString();
	}
	
	
	/**
	 * Inserver un mot dans un emplacement
	 * @param m indice de l'emplacement
	 * @param soluce le mot qu'on veut ajouter
	 * @return	nouvelle grille modifié apres ajout du mot
	 */
	public GrillePlaces fixer(int m, String soluce) {
		Grille temp = g.copy();
		Emplacement mot = places.get(m);
		for (int i=0; i<mot.size(); i++) 
			temp.getCase(mot.getCaseAt(i).getLig(), mot.getCaseAt(i).getCol()).setChar(soluce.charAt(i));
		
		return new GrillePlaces(temp);	
		
	}
}