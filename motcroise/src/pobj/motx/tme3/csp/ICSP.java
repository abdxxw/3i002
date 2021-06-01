package pobj.motx.tme3.csp;

import java.util.List;

/**
 * @author 3804891
 * Interface pour un probleme CSP
 */
public interface ICSP {

	/**
	 * Acceder au variables du probleme
	 * @return liste des variables des problemes
	 */
	List<IVariable> getVars();
	/**
	 * verifier si le probleme est consistant
	 * @return true si il est consistent, false sinon 
	 */
	boolean isConsistent();
	/**
	 * assigner une valeur a une variable du probleme 
	 * @param vi la variable du probleme 
	 * @param val la valeur a assigner
	 * @return le nouveau probleme apres affectation de la valeur
	 */
	ICSP assign(IVariable vi, String val);
}
