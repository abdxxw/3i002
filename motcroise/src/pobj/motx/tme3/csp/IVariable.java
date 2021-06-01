package pobj.motx.tme3.csp;

import java.util.List;

/**
 * Variable d'un probleme d'un probleme CSP
 * @author 3804891
 *
 */
public interface IVariable {

	/**
	 * Acceder au domain de la variable
	 * @return liste de String des valeur possible de cette variable
	 */
	List<String> getDomain();
}
