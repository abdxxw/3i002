package pobj.motx.tme2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 3804891
 * Un ensemble de lettre qui fonctionne de la meme façon qu'un HashSet
 * @param <Character> le type de l'ensemble qui est caracter
 */
public class EnsembleLettre<Character> extends HashSet<Character>{

	/**
	 * redefinition du constructeur de HashSet sans parametres
	 */
	public EnsembleLettre() {
		super();
	}
	/**
	 *  redefinition du constructeur de HashSet avec parametres
	 * @param l1 ensemble de lettre que la classe mere utilise pour appeler son constructeur
	 */
	public EnsembleLettre(EnsembleLettre<Character> l1) {
		super(l1);
	}

}
