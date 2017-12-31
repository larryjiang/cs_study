package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Token;
import cptt.chapter2.mini_front_end.symbols.Type;

public class Arith extends Op {
	private Expr expr1, expr2;
	
	public Arith(Token tok, Expr x1, Expr x2) {
		super(tok, null);
		expr1 = x1;
		expr2 = x2;
		
		Type type = Type.max(x1.getType(), x2.getType());
		if(type == null) {
			error("Type Error");
		}
		
		this.type = type;
		
	}
	
	public Expr gen() {
		return new Arith(getOp(),expr1.reduce(), expr2.reduce());
	}
	
	public String toString() {
		return expr1.toString() + " " + getOp().toString() + " " + expr2.toString();
	}
}
