package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Token;
import cptt.chapter2.mini_front_end.symbols.Type;

public class Unary extends Op {
	private Expr expr;
	public Unary(Token tok, Expr x) {
		super(tok, null);
		this.expr = x;
		Type type = Type.max(Type.Int, expr.getType());
		if(type  == null) {
			error("Type Error");
		}
	}

	public Expr gen() {
		return new Unary(getOp(), expr.reduce());
	}
	
	public String toString() {
		return getOp().toString() + " " + expr.toString();
	}
	
	
}
