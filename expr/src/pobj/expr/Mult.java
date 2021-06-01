package pobj.expr;

public class Mult extends BinOp implements Expression{

	public Mult(Expression left, Expression right) {
		super(left, right);
	}
	
	public String toString() {
		return getLeft()+" * "+getRight();
	}

	@Override
	public int eval() {
		return getLeft().eval() * getRight().eval();
	}

	
	@Override
	public <T> T accept(IVisitor<T> v) {
		return v.visit(this);
	}
}
