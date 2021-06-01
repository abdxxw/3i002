package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression>{
	VisitorConstant vc = new VisitorConstant();
	VisitorEval ve = new VisitorEval();
	@Override
	public Expression visit(Constant c) {
		return c;
	}

	@Override
	public Expression visit(Add a) {
		if(a.accept(vc))
			return new Constant(a.accept(ve));
		
		if (a.getLeft() instanceof Constant)
			if (((Constant) a.getLeft()).getValue() == 0)
				return a.getRight();
		if (a.getRight() instanceof Constant)
			if (((Constant) a.getRight()).getValue() == 0)
				return a.getLeft();
		return new Add(a.getLeft().accept(this),a.getRight().accept(this));
	}

	@Override
	public Expression visit(Mult a) {
		if(a.accept(vc))
			return new Constant(a.accept(ve));
		if (a.getLeft() instanceof Constant) {
			if (((Constant) a.getLeft()).getValue() == 1)
				return a.getRight();
			if (((Constant) a.getLeft()).getValue() == 0)
				return new Constant(0);
		}
		if (a.getRight() instanceof Constant) {
			if (((Constant) a.getRight()).getValue() == 1)
				return a.getLeft();			
			if (((Constant) a.getRight()).getValue() == 0)
				return new Constant(0);
		}
		return new Mult(a.getLeft().accept(this),a.getRight().accept(this));
	}

	@Override
	public Expression visit(Var v) {
		return v;
	}

}
