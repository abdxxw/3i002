package pobj.motx.tme3.csp;
import java.util.List;

/**
 * @author 3804891
 * Interface des Strategie de choix des valeurs
 */
public interface IChoixValeur {
	/**
	 * choisir l'ordre des valeurs en utilisant une strategie donnée
	 * @param problem le probleme CSP
	 * @param v la variable du probleme
	 * @return une liste des valeur de domaine dans l'ordre propose par la strategie
	 */
	List<String> orderValues (ICSP problem, IVariable v);
}
