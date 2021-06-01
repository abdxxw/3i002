package pobj.motx.tme3.csp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author 3804891
 * Strategie de choix de valeur par frequence
 */
public class StratFreq implements IChoixValeur{

	/**
	 * ordonner les valeurs d'une varables dans leur ordre de frequence 
	 * @param problem le probleme a resoudre
	 * @param v variable a trie ses valeur
	 * @return liste des valeurs trie par frequence 
	 */
	@Override
	public List<String> orderValues(ICSP problem, IVariable v) {
		int[] scrable= {1,3,3,2,1,4,2,4,1,8,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10};
		List<StringFreq> out = new ArrayList<StringFreq>();
		for(String s : v.getDomain()) {
			int cpt=0;
			for(int i=0;i<s.length();i++)
				cpt+=scrable[(int)s.charAt(i)- (int)'a'];
			out.add(new StringFreq(s,cpt));
		}

		Collections.sort(out);
		List<String> sortedList = new ArrayList<String>();
		for(StringFreq s : out) {
			sortedList.add(s.getS());
		}
		return sortedList;
	}

}
