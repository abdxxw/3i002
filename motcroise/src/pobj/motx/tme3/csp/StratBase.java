package pobj.motx.tme3.csp;
import java.util.List;

/**
 * @author 3804891
 * Strategie de choix de valeur dans l'ordre de base 
 */
public class StratBase implements IChoixValeur{

	/**
	 * Rendre les valeur d'une varable dans leur ordre de base
	 * @param problem le probleme a resoudre
	 * @param v la variable 
	 * @return liste des valeurs dans leurs ordre de base
	 */
	@Override
	public List<String> orderValues(ICSP problem, IVariable v) {
		return v.getDomain();
	}

}
