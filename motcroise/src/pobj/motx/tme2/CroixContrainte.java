package pobj.motx.tme2;

/**
 * @author 3804891
 * Represente un croisement entre deux mots 
 */
public class CroixContrainte implements IContrainte{

	/** indice de l'emplacement du premier mot */
	private int m1;
	/** indice de la case du premier mot */
	private int c1;
	/** indice de l'emplacement du deuxieme mot */
	private int m2;
	/** indice de la case du deuxieme mot */
	private int c2;
	
	/**
	 * initialiser le croisement 
	 * @param m1 indice de l'emplacement du premier mot
	 * @param c1 indice de la case du premier mot
	 * @param m2 indice de l'emplacement du deuxieme mot
	 * @param c2 indice de la case du deuxieme mot
	 */
	public CroixContrainte(int m1, int c1, int m2, int c2) {
		this.m1 = m1;
		this.c1 = c1;
		this.m2 = m2;
		this.c2 = c2;
		
	}



	/**
	 * permet de filter les mots potentiels et laisser seulement qui respecte se croisent 
	 * @param grille la grille potentiel a reduire
	 * @return nombre de mots supprimé
	 */
	@Override
	public int reduce(GrillePotentiel grille) {
		
		Dictionnaire d1 = grille.getMotsPot().get(m1);
		Dictionnaire d2 = grille.getMotsPot().get(m2);
		EnsembleLettre<Character> l1 = d1.getEnsembleLettre(c1);
		EnsembleLettre<Character> l2 = d2.getEnsembleLettre(c2);
		
		EnsembleLettre<Character> s = new EnsembleLettre<Character>(l1);
		s.retainAll(l2);
		
		int cpt=0;
		
		if (l1.size() > s.size()) {
			cpt += d1.filtreParEnsemble(s, c1);
		}
		
		if (l2.size() > s.size()) {
			cpt += d2.filtreParEnsemble(s, c2);
		}
		return cpt;
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if(other == this) return true;
		if(!(other instanceof CroixContrainte)) return false;
		CroixContrainte c = (CroixContrainte) other;
		return (m1==c.m1 && m2==c.m2 && c1==c.c1 && c2==c.c2);
	}

}
