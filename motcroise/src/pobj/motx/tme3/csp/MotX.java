package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.GrillePotentiel;

/**
 * @author 3804891
 *
 */
public class MotX implements ICSP{
	/** liste des variable du probleme*/
	private List<IVariable> liste = new ArrayList<IVariable>();
	/** la grille potentiel*/
	private GrillePotentiel gp;
	
	/**
	 * Initialiser le probleme avec des varaible en amplacement qui ont encore des cases vides 
	 * @param gp la grille potentiel
	 */
	public MotX(GrillePotentiel gp) {
		this.gp=gp;
		int i=0;
		for(Emplacement x : gp.getGr().getPlaces()) {
			if(x.hasCaseVide()) {
				liste.add(new DicoVariable(i,gp));
			}
			i++;
		}
	}
	/* (non-Javadoc)
	 * @see pobj.motx.tme3.csp.ICSP#getVars()
	 */
	@Override
	public List<IVariable> getVars() {
		return liste;
	}

	/* (non-Javadoc)
	 * @see pobj.motx.tme3.csp.ICSP#isConsistent()
	 */
	@Override
	public boolean isConsistent() {
		return !gp.isDead();
	}

	/* (non-Javadoc)
	 * @see pobj.motx.tme3.csp.ICSP#assign(pobj.motx.tme3.csp.IVariable, java.lang.String)
	 */
	@Override
	public ICSP assign(IVariable vi, String val) {
		if(vi instanceof DicoVariable) {
			DicoVariable dico = (DicoVariable) vi;
			return(new MotX(gp.fixer(dico.getInd(),val)));
		}
		return new MotX(gp);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return gp.toString();
	}

}
