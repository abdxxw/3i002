package pobj.motx.tme1;


/**
 * @author 3804891
 * represante la grille 
 */
public class Grille {
	/**	la matrice des cases qui represante la grille */
	private Case[][] mat;
	
	/**
	 * initialiser une grille vide 
	 * @param hauteur de la grille
	 * @param largeur de la grille
	 */
	public Grille(int hauteur, int largeur) {
		mat = new Case[hauteur][largeur];
		
		for(int i=0;i<hauteur;i++) {
			for(int j = 0;j<largeur;j++){
				mat[i][j] = new Case(i,j,' ');
			}
		}
	
	}
	/**
	 * acceder a une case de la matrice 
	 * @param lig	ligne de la case	
	 * @param col	colone de la case
	 * @return la case qui se trouver dans lig col
	 */
	public Case getCase(int lig, int col) {
		return mat[lig][col];
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return GrilleLoader.serialize(this, false);
	}
	
	/**
	 * acceder au nombre de lignes de la grille
	 * @return le nombre de lignes de la grille
	 */
	public int nbLig() {
		return mat.length;
	}
	/**
	 * acceder au nombre de colones de la grille
	 * @return le nombre de colones de la grille
	 */
	public int nbCol() {
		return mat[0].length;
	}
	
	/**
	 * copier une grille (clone)
	 * @return une grille identique au "this" avec une autre referance
	 */
	public Grille copy() {
		Grille out = new Grille(this.nbLig(),this.nbCol());
		
		for(int i=0;i<this.nbLig();i++) {
			for(int j = 0;j<this.nbCol();j++){
				out.mat[i][j] = mat[i][j].copy();
			}
		}
		return out;
	}
}