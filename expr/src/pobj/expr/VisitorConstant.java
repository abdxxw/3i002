package pobj.expr;

public class VisitorConstant implements IVisitor<Boolean>{

	@Override
	public Boolean visit(Constant c) {
		return true;
	}

	@Override
	public Boolean visit(Add a) {
		return a.getLeft().accept(this) && a.getRight().accept(this);
	}

	@Override
	public Boolean visit(Mult a) {
		return a.getLeft().accept(this) && a.getRight().accept(this);
	}

	@Override
	public Boolean visit(Var v) {
		return false;
	}

}
