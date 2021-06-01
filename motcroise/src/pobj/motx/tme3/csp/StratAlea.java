package pobj.motx.tme3.csp;
import java.util.Collections;
import java.util.List;

/**
 * @author 3804891
 * Strategie de choix de valeur aleatoirement
 */
public class StratAlea implements IChoixValeur{

	/**
	 * ordonner les valeurs d'une varables aleatoirement
	 * @param problem le probleme a resoudre
	 * @param v variable a trie ses valeur
	 * @return liste des valeurs trie aleatoirement
	 */
	@Override
	public List<String> orderValues(ICSP problem, IVariable v) {
		List<String> out = v.getDomain();
		Collections.shuffle(out);
		return out;
	}

}
