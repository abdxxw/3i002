package pobj.motx.tme3.csp;

/**
 * @author 3804891
 * Strategie de choix de la premiere variable
 */
public class StratFirst implements IChoixVar{

	/**
	 * choisir la premiere variable du domaine
	 * @param problem le probleme a resoudre
	 * @return la variable choisi
	 */
	@Override
	public IVariable chooseVar(ICSP problem) {
		return problem.getVars().get(0);
	}

}
