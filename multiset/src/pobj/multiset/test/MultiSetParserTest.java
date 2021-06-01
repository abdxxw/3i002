package pobj.multiset.test;

import pobj.multiset.InvalidMultiSetFormat;
import pobj.multiset.MultiSet;
import pobj.multiset.MultiSetParser;

public class MultiSetParserTest {

	public static void main(String[] args) {
		try {
			MultiSet<String> ms = MultiSetParser.parse("data/parse.txt");
			System.out.println(ms.toString());
		}catch(InvalidMultiSetFormat e) {
			System.out.println(e.getMessage()+" caused by : "+e.getCause());
		}
	}
}
