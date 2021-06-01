package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import pobj.util.Chrono;


public class WordCount {

	/**
	 * fonction main pour executer les testes 
	 */
	public static void main(String[] args) {
		
		System.out.println("HashMultiSet :\n");
		MultiSet<String> ms = new HashMultiSet<String>();
		wordcount(ms,"data/WarAndPeace.txt");
		//System.out.println("==========\n");
		//System.out.println("NaiveMultiSet :\n");
		//MultiSet<String> nms = new MultiSetDecorator<String>(new NaiveMultiSet<String>());
		//wordcount(nms,"data/WarAndPeace.txt");

		
		
	}
	/**
	 * compter l'occurence de chaque mot du fichier donne en utilisant un multiset donne
	 * @param ms le multiset a utiliser
	 * @param file le fichier a utiliser
	 */
	public static void wordcount(MultiSet<String> ms,String file) {
		Chrono chrono = new Chrono();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine())!=null) {
				for (String word : line.split("\\P{L}+")) {

					if (word.equals("")) continue;		
					ms.add(word);
				}
			}
		List<String> l = ms.elements();
		Collections.sort(l, ms.comparator());

			System.out.println(ms);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		chrono.stop();
			
		
	}
}
