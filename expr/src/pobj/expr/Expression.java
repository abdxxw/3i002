package pobj.expr;

public interface Expression {
	
	int eval();
	<T> T accept(IVisitor<T> v);
}
