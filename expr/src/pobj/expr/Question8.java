package pobj.expr;

import java.util.HashMap;
import java.util.Map;

public class Question8 {

	public static Map<String, Integer> env1() {
		return new HashMap<String,Integer>();
	}
	
	public static Map<String, Integer> env2() {
		Map<String, Integer> out = new HashMap<String,Integer>();
		out.put("x", 10);
		out.put("y", 20);
		return out;
	}
	
	public static Map<String, Integer> env3() {
		Map<String, Integer> out = new HashMap<String,Integer>();
		out.put("z", 9);
		return out;
	}
}


