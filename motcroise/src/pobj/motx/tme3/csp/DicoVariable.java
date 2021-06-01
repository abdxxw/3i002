package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.GrillePotentiel;


/**
 * @author 3804891
 * la variable du probleme de grille
 */
public class DicoVariable implements IVariable{
	
	/**l'indice de la variable dans la grille */
	private int ind;
	/** la grille potentiel*/
	private GrillePotentiel grille;

	/**
	 * Initialiser la variable
	 * @param index l'indice de la variable
	 * @param gp la grille 
	 */
	public DicoVariable(int index, GrillePotentiel gp) {
		 ind=index;
		 grille=gp;
	 }
	/* (non-Javadoc)
	 * @see pobj.motx.tme3.csp.IVariable#getDomain()
	 */
	@Override
	public List<String> getDomain() {
		return grille.getMotsPot().get(ind).getMots();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder out = new StringBuilder("Domaine variable de l'indice "+ind+" : \n");
		List<String> domain = grille.getMotsPot().get(ind).getMots();
		for(String x : domain) {
			out.append(x);
			out.append(" ,");
		}
		//return out.toString();
		return "";
	}
	
	/**
	 * Acceder a l'indice de la variable
	 * @return l'indice de la variable
	 */
	public int getInd() {
		return ind;
	}
}
