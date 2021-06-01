package pobj.motx.tme3.csp;

import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme2.IContrainte;

/**
 * @author 3804891
 * COntrainte du mot unique 
 */
public class MotUnique implements IContrainte{

	/** le mot unique dans un dictionnaire*/
	private String motUnique;
	
	/**
	 * Initialiser le mot
	 * @param x le string du mot
	 */
	public MotUnique(String x) {
		motUnique = x;
	}
	
	/**
	 * reduire la grille en supprimant les mots qui sont unique dans un dictionnaire des autres domaines
	 * @param grille la grille a reduire
	 * @return le nombre de mots supprimer
	 */
	public int reduce(GrillePotentiel grille) {
		int cpt=0;
		for (Dictionnaire d : grille.getMotsPot()) {
			if(d.size() > 1) {
				if(d.getMots().remove(motUnique)) cpt++;
			}
			
		}
		return cpt;
	}

}
