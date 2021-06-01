package pobj.motx.tme3.csp;

/**
 * @author 3804891
 * Represante un couple de string avec son frequence
 */
public class StringFreq implements Comparable<StringFreq>{

	/**le string */
	private String s;
	/** sa frequecne*/
	private int freq;
	
	/**
	 * initialiser la valeur du string et sa frequence
	 * @param s	le string
	 * @param f la frequence du string
	 */
	public StringFreq(String s,int f) {
		this.s=s;
		freq=f;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(StringFreq o) {
		return this.freq - o.freq;
	}
	
	/**
	 * Acceder au string
	 * @return le string
	 */
	public String getS() {
		return s;
	}
}
