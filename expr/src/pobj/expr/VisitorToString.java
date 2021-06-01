package pobj.expr;

public class VisitorToString implements IVisitor<String>{
	
	public String visit(Constant c) {
		return Integer.toString(c.getValue());
	}
	
	public String visit(Var v) {
		return v.getName();
	}
	
	public String visit(Add a) {
		String s1 = a.getLeft().accept(this);
		String s2 = a.getRight().accept(this);
		return "( "+ s1 + " + " + s2 + " )";
	}
	
	public String visit(Mult a) {
		String s1 = a.getLeft().accept(this); 
		String s2 = a.getRight().accept(this); 
		return s1 + " * " + s2; 
	} 
}