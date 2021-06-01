package pobj.expr;

public class Var implements Expression {
	
	private final String name;

	public Var(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Var))
			return false;
		Var other = (Var) obj;
		return name.equals(other.name);
	}

	@Override
	public int eval() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	
	@Override
	public <T> T accept(IVisitor<T> v) {
		return v.visit(this);
	}
	

}
