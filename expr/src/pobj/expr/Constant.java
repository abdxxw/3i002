package pobj.expr;

public class Constant implements Expression {
	
	private int value;

	
	public Constant() {
		this(0);
	}

	
	public Constant(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Constant))
			return false;
		Constant other = (Constant) obj;
		return value == other.value;
	}


	@Override
	public int eval() {
		return value;
	}
	
	@Override
	public <T> T accept(IVisitor<T> v) {
		return v.visit(this);
	}
	
	

}
