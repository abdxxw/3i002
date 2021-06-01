package pobj.motx.tme3.csp;

import java.util.List;

/**
 * @author 3804891
 * Classe pour resoudre la grille
 */
public class CSPSolver {
	private IChoixVar stratVar;
	private IChoixValeur stratVal;
	
	/**
	 * Choisir la strategie de choix de variable
	 * @param strat le type de la strategie
	 */
	public void setChoixVarStrat(IChoixVar strat) {
		stratVar = strat;
	}
	/**
	 * Choisir la strategie de choix des valeurs
	 * @param strat le type de la strategie
	 */
	public void setChoixValStrat(IChoixValeur strat) {
		stratVal = strat;
	}
	/**
	 * Resoudre la grille
	 * @param problem le probleme a resoudre a une iteration donnée 
	 * @return
	 */
	public ICSP solve(ICSP problem) {
		//System.out.println("Solve : \n" + problem);
		// Condition terminale : succès
		if (problem.getVars().isEmpty()) {
			System.out.println("Problème résolu.");
			return problem;
		}
		// condition terminale : échec sur cette branche
		if (!problem.isConsistent()) {
			System.out.println("Problème invalide.");
			return problem;
		} else {
			System.out.println("Problème valide.");
		}
		// On choisit une variable arbitraire, ici la première
		// On est garantis que ! getVars().isEmpty(), testé au dessus
		IVariable vi = stratVar.chooseVar(problem);

		ICSP next = null;
		// On est garantis que toute variable a un domaine non nul
		List<String> domaine = stratVal.orderValues(problem, vi);
		for (String val : domaine) {
			System.out.println("Fixe var :" + vi + " à " + val);
			next = problem.assign(vi, val);
			next = solve(next);
			if (next.isConsistent()) {
				return next;
			} else {
				System.out.println("Essai valeur suivante.");
			}
		}
		System.out.println("Backtrack sur variable "+ vi);
		return next;
	}


}
