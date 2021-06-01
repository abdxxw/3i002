package pobj.motx.tme3.csp;

/**
 * @author 3804891
 * Interface des Strategie de choix de variable
 */
public interface IChoixVar {
	/**
	 * choisir une variable en utilisant une strategie donnée
	 * @param problem le probleme CSP
	 * @return une variable choisis en utilisant une strategie donnée
	 */
	IVariable chooseVar(ICSP problem);
}
