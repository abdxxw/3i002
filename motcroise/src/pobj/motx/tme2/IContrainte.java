package pobj.motx.tme2;

/**
 * @author 3804891
 * interface qui represante la proprieté de contrainte
 */
public interface IContrainte {

	/**
	 * permet de filter les mots potentiels et laisser seulement qui respecte la contrainte
	 * @param grille la grille potentiel a reduire
	 * @return nombre de mots supprimé
	 */
	int reduce(GrillePotentiel grille);
}
