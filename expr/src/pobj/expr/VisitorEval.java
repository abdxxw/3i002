package pobj.expr;

public class VisitorEval implements IVisitor<Integer>{
	
	public Integer visit(Constant c) {
		return c.getValue();
	}
	
	public Integer visit(Var v) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	public Integer visit(Add a) {
		return a.getLeft().accept(this) + a.getRight().accept(this);
	}
	
	public Integer visit(Mult a) {
		return a.getLeft().accept(this) * a.getRight().accept(this);
	} 
}
