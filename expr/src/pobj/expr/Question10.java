package pobj.expr;

public class Question10 { 
	public static boolean isConstant(Expression e) { 
		VisitorConstant vc = new VisitorConstant();
		return e.accept(vc);
	} 

	public static int evalConstantExpression (Expression e) { 
		VisitorEval ve = new VisitorEval();
		return e.accept(ve);
		
	} 
	} 
