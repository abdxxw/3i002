package pobj.motx.tme1;


/**
 * @author 3804891
 * represante une case de grille
 */
public class Case {
	/** coordonnee d'une case*/
	private int lig,col;
	/**le contenu d'une case */
	private char c;
	
	/**
	 * Construire une case initialisé
	 * @param lig ligne initiale de la case
	 * @param col colone initiale de la case
	 * @param c contenu de la case 
	 */
	public Case(int lig, int col, char c) {
		this.lig=lig;
		this.col=col;
		this.c=c;
	}

	/**
	 * acceder a la ligne de la case 
	 * @return la ligne d'une case
	 */
	public int getLig() {
		return lig;
	}

	/**
	 * acceder a la colone de la case 
	 * @return la colone d'une case
	 */
	public int getCol() {
		return col;
	}

	/**
	 * acceder au contenu de la case 
	 * @return le contenu d'une case
	 */
	public char getChar() {
		return c;
	}

	/**
	 * modifier le contenu de la case
	 * @param c le caractere a mettre dans la case
	 */
	public void setChar(char c) {
		this.c = c;
	}
	
	/**
	 * verifier si la case est vide
	 * @return true si vide, false sinon
	 */
	public boolean isVide() {
		 return c == ' ';
	 }
	/**
	 * verifier si la case est pleine
	 * @return true si pleinne, false sinon
	 */
	public boolean isPleine() {
		 return c == '*';
	 }
	
	/**
	 * copier une case (clone)
	 * @return une case indentique avec une autre referance
	 */
	public Case copy() {
		return new Case(lig,col,c);
	}
}
