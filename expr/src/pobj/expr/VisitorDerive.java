package pobj.expr;

public class VisitorDerive implements IVisitor<Expression>{

	private Var v;
	public VisitorDerive(Var v) {
		this.v=v;
	}
	
	@Override
	public Expression visit(Constant c) {
		return new Constant(0);
	}

	@Override
	public Expression visit(Add a) {
		return new Add(a.getLeft().accept(this),a.getRight().accept(this));
	}

	@Override
	public Expression visit(Mult a) {
		return new Add(new Mult(a.getLeft(),a.getRight().accept(this)),new Mult(a.getLeft().accept(this),a.getRight()));
	}

	@Override
	public Expression visit(Var v) {
		if(v.equals(this.v)) 
			return new Constant(1);
		else
			return new Constant(0);

	}

}
