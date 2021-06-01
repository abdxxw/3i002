package pobj.motx.tme3.csp;

/**
 * @author 3804891
 * Strategie de choix de variable min d'abord
 */
public class StratMin implements IChoixVar{

	/**
	 * choisir la variable min du domaine
	 * @param problem le probleme a resoudre
	 * @return la variable choisi
	 */
	@Override
	public IVariable chooseVar(ICSP problem) {
		IVariable min=problem.getVars().get(0);
		for(IVariable x : problem.getVars())
			if(x.getDomain().size()<min.getDomain().size()) min = x;
		return min;
	}

}
