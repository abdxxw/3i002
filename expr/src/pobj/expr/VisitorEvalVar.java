package pobj.expr;

import java.util.Map;

public class VisitorEvalVar extends VisitorEval{
	private Map<String, Integer> env;
	
	public VisitorEvalVar(Map<String, Integer> env) {
		this.env = env;
	}
	
	@Override
	public Integer visit(Var v) {
		return env.get(v.getName());
	}

}
