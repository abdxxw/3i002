package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 3804891
 * Parser pour le multiset 
 */
public class MultiSetParser {

	/**
	 * construire un multiset a partir d'un fichier qui est en format specifique
	 * @param fileName le nom de fichier
	 * @return le multiset 
	 * @throws InvalidMultiSetFormat exception par rapport a l'erreur
	 */
	public static MultiSet<String> parse (String fileName) throws InvalidMultiSetFormat{
		MultiSet<String> out = new HashMultiSet<String>();
		try {
			BufferedReader b = new BufferedReader(new FileReader(fileName));
			String s;
			while((s = b.readLine()) != null) {
				String[] l = s.split(":");
				int x = Integer.decode(l[1]);
				if(x < 0) throw new InvalidMultiSetFormat("Count negative");
				out.add(l[0],x);
			}
			try {
				b.close();
			}catch(IOException e) {
				throw new InvalidMultiSetFormat("Erreur fermeture fichier",e);
			}
		} catch (FileNotFoundException e) {
			throw new InvalidMultiSetFormat("Erreur ouverture fichier",e);
		} catch (IOException e) {
			throw new InvalidMultiSetFormat("Erreur lecture ligne",e);
		} catch (NumberFormatException e) {
			throw new InvalidMultiSetFormat("Erreur format d'entier", e);
		}
		
		return out;
	}
}
