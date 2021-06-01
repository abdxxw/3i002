package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme3.csp.MotUnique;

/**
 * @author 3804891
 * grille avec la liste des mots potentiel
 */
public class GrillePotentiel {
	/**grille avec les emplacement*/
	private GrillePlaces gr;
	/**dictionnaire complet qui contient tous les mots qu'on peut utiliser*/
	private Dictionnaire dict;
	/**ensemble des mots potontiels pour etre la solution de notre grille */
	private List<Dictionnaire> motsPot;
	/**le liste des croisement a respecter*/
	private List<IContrainte> contraintes = new ArrayList<IContrainte>();
	private List<IContrainte> contraintes2 = new ArrayList<IContrainte>();
	
	/**
	 * initialliser la grille avec tous mots potentiels et les contraintes a respecter 
	 * @param grille la grille avec emplacement
	 * @param dicoComplet le dictionnaire des mots a utiliser
	 */
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		gr=grille;
		dict=dicoComplet;
		motsPot = new ArrayList<Dictionnaire>();
		for (Emplacement e : gr.getPlaces()) {
			Dictionnaire d = dict.copy();
			d.filtreLongueur(e.size());
			filtreDictionnaire(e, d);
			if(d.size() == 1) contraintes2.add(new MotUnique(d.get(0)));
			motsPot.add(d);
		}
		for(int m1=0;m1<gr.getNbHorizontal();m1++) {
			for(int m2=gr.getNbHorizontal();m2<gr.getPlaces().size();m2++) {
				for(int c1=0;c1<gr.getPlaces().get(m1).size();c1++) {
					for(int c2=0;c2<gr.getPlaces().get(m2).size();c2++) {
						if((gr.getPlaces().get(m1).getCaseAt(c1) == gr.getPlaces().get(m2).getCaseAt(c2)) && (gr.getPlaces().get(m1).getCaseAt(c1).isVide()))
							contraintes.add(new CroixContrainte(m1,c1,m2,c2));
					}
				}
			}
		}
			this.propage();	
			
	}
	
	/**
	 * initialliser la grille avec tous mots potentiels et les contraintes a respecter depuis une liste des mots potentiel
	 * @param grille la grille avec emplacement
	 * @param dicoComplet le dictionnaire des mots a utiliser
	 * @param motsPot liste des dictionnaire des mots potentiel
	 */
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet,List<Dictionnaire> motsPot) {

		
		gr=grille;
		dict=dicoComplet;
		this.motsPot = new ArrayList<Dictionnaire>();
		int i=0;
		for (Emplacement e : gr.getPlaces()) {
			Dictionnaire d = motsPot.get(i).copy();
			filtreDictionnaire(e, d);
			this.motsPot.add(d);
			if(d.size() == 1) contraintes2.add(new MotUnique(d.get(0)));
			i++;
		}
		for(int m1=0;m1<gr.getNbHorizontal();m1++) {
			for(int m2=gr.getNbHorizontal();m2<gr.getPlaces().size();m2++) {
				for(int c1=0;c1<gr.getPlaces().get(m1).size();c1++) {
					for(int c2=0;c2<gr.getPlaces().get(m2).size();c2++) {
						if((gr.getPlaces().get(m1).getCaseAt(c1) == gr.getPlaces().get(m2).getCaseAt(c2)) && (gr.getPlaces().get(m1).getCaseAt(c1).isVide()))
							contraintes.add(new CroixContrainte(m1,c1,m2,c2));
					}
				}
			}
		}
			this.propage();	
			
	}
	/**
	 * Acceder a la liste des mots potentiels
	 * @return liste des dictionnaire qui represante les mots 
	 */
	public List<Dictionnaire> getMotsPot(){
		return motsPot;
	}
	/**
	 * Verifier si un emplacement dans la grille n'as plus de mots potentiel
	 * @return true si il y a plus de mots pour un emplacement donné, false sinon
	 */
	public boolean isDead() {
		Iterator<Dictionnaire> it = motsPot.iterator();
		while(it.hasNext()) {
			if(it.next().size() == 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * permet de cree une grille avec mots potentiels apres fixation d'un mot dans un emplacement
	 * @param m l'indice de l'emplacement dans la grille 
	 * @param soluce le mots a a inserer 
	 * @return une grille avec mots potentiels initialiser apres fixation du mot
	 */
	public GrillePotentiel fixer(int m, String soluce) {

		
		return new GrillePotentiel(gr.fixer(m, soluce), dict, motsPot);
	}
	
	/**
	 * Acceder a la liste des contraintes
	 * @return la liste des contraintes
	 */
	public List<IContrainte> getContraintes() {
		return contraintes;
	}
	
	/**
	 * Propager les contraintes sur la grille
	 * @return true si la propagation est terminé avec succés, false si un des dictionnaire des mots potentiels est vide 
	 */
	private boolean propage() {
		while(true) {
			if(isDead()) return false;
			int cpt = 0;
			Iterator<IContrainte> it = contraintes.iterator();
			while(it.hasNext()) {
				cpt+=it.next().reduce(this);
			}
			//utilisation de contrainte de mot unique 
			/*Iterator<IContrainte> it2 = contraintes2.iterator();
			while(it2.hasNext()) {
				cpt+=it2.next().reduce(this);
			}*/
			if(cpt == 0) return true;
			
		}
	}
	/**
	 * Acceder a la grille avec emplacement
	 * @return la grille avec emplacement 
	 */
	public GrillePlaces getGr() {
		return gr;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return gr.toString();
	}
	
	/**
	 * Filter le dictionnaire et supprimer les mots qui n'ont pas des lettres en communs avec un emplacement dans les cases non vides 
	 * @param e emplacement courant
	 * @param d dictionnaire a filter 
	 */
	public void filtreDictionnaire(Emplacement e,Dictionnaire d) {
		for (int j=0; j<e.size(); j++) {
			Case c = e.getCaseAt(j);
			if (!c.isVide()) {
				d.filtreParLettre(c.getChar(), j);
			}
		}
	}
}
