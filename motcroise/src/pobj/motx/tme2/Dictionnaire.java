package pobj.motx.tme2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 3804891
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {

	// stockage des mots
	private List<String> mots = new ArrayList<>();
	private EnsembleLettre<Character> [] cache = null;

	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.cache = cache;
		copy.mots.addAll(mots);
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		if(cpt != 0) cache = null;
		mots = cible;
		return cpt;
	}
	
	
	/**
	 * Permet de cree un dictionnaire a partir d'un fichier
	 * @param path le chemin ou se trouve le fichier 
	 * @return un dictionnaire initialisé avec le contenu du fichier 
	 */
	public static Dictionnaire loadDictionnaire(String path) {
		Dictionnaire out = new Dictionnaire();
		
		try(BufferedReader br =new BufferedReader(new FileReader(path))) {
			for(String line = br.readLine() ; line !=null; line = br.readLine() ) {
				out.add(line);
			}
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		return out;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}
	
	/**
	 * retirer les mots qui ne contient pas un caractere donné dans une case donné
	 * @param c le caractere que le mot doit contenir
	 * @param i	l'indice du caractere dans les mots
	 * @return nombre de mots supprimer
	 */
	public int filtreParLettre(char c,int i) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.charAt(i) == c)
				cible.add(mot);
			else
				cpt++;
		}
		if(cpt != 0) cache = null;
		mots = cible;
		return cpt;
	}

	/**
	 * retirer les mots que leurs ieme caractere n'est pas dans un ensemble de lettres
	 * @param l ensemble de lettres 
	 * @param i indicie du caractere dans les mots
	 * @return le nombre de mots supprimés
	 */
	public int filtreParEnsemble(EnsembleLettre<Character> l,int i) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (l.contains(mot.charAt(i)))
				cible.add(mot);
			else
				cpt++;
		}
		if(cpt != 0) cache = null;
		mots = cible;
		return cpt;
	}
	

	/**
	 * Permet d'avoir un ensemble de lettre qui se trouver dans un incide donné dans tous les mots
	 * @param i indice des caractere 
	 * @return ensemble de lettre qui se trouve dans la ieme case 
	 */
	public EnsembleLettre<Character> getEnsembleLettre(int i) {

		EnsembleLettre<Character> out = new EnsembleLettre<Character>();
		if(mots.size() == 0) return out;
		if(cache == null)	cache = (EnsembleLettre<Character>[]) new EnsembleLettre[mots.get(0).length()];
		if(cache[i] == null) {
			for(String m : mots) {
				out.add(m.charAt(i));
			}
			cache[i] = out;
			return out;
			
		}else return cache[i];
	}

	public List<String> getMots() {
		return mots;
	}
	
}
