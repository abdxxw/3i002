package pobj.expr;

public class Question4 {

	public static Expression e1() {
		return new Mult(new Add(new Constant(2),new Constant(3)),new Constant(4));
	}
	
	public static Expression e2() {
		return new Mult(new Add(new Var("x"),new Constant(3)),new Add(new Var("x"),new Constant(4)));
	}
	
	public static Expression e3() {
		return new Mult(new Add(new Var("x"),new Constant(10)),new Add(new Var("y"),new Constant(-8)));
	}
}


