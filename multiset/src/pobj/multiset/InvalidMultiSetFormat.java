package pobj.multiset;

/**
 * @author 3804891
 * Exception propre au multiset
 */
public class InvalidMultiSetFormat extends Exception{
	
	/**
	 * initialisation avec message
	 * @param m le message a afficher
	 */
	public InvalidMultiSetFormat(String m) {
		super(m);
	}	
	
	/**
	 * initialisation avec message et cause
	 * @param m le message a afficher
	 * @param c l'exception qui a cause ca
	 */
	public InvalidMultiSetFormat(String m, Throwable c) {
		super(m,c);
	}
}
