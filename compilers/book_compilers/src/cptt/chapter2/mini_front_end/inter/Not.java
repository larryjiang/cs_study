package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Token;

public class Not extends Logical {

	public Not(Token tok, Expr x2) {
		super(tok, x2, x2);
		// TODO Auto-generated constructor stub
	}
	
	public void jumping(int t, int f) {
		getExpr2().jumping(f, t);
		
	}
	
	public String toString() {
		return getOp().toString() + " " + getExpr2().toString();
	}

}
