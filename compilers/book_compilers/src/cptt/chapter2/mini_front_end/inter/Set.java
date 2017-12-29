package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.symbols.Type;

public class Set extends Stmt{
	private Id id;
	private Expr expr;
	
	public Set(Id id, Expr expr) {
		this.id = id;
		this.expr  = expr;
		if(check(id.getType(),expr.getType()) == null) {
			throw new IllegalArgumentException("Type does not match!");
		}
		
	}
	
	private Type check(Type p1, Type p2) {
		if(Type.numeric(p1) && Type.numeric(p2)) {
			return Type.max(p1, p2);
		}else if(p1 == Type.Bool && p2 == Type.Bool) {
			return Type.Bool;
		}else {
			return null;
		}
	}
	
	public void gen(int b, int a) {
		emit(id.toString() + " = " + expr.gen().toString());
	}

	public Id getId() {
		return id;
	}

	public Expr getExpr() {
		return expr;
	}
}
