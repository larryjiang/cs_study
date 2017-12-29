package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Token;

public class And extends Logical{

	public And(Token tok, Expr x1, Expr x2) {
		super(tok, x1, x2);
		// TODO Auto-generated constructor stub
	}
	
	
	public void jumping(int t, int f) {
		int label = f != 0 ? f : newLabel();
		getExpr1().jumping(0, label);
		getExpr2().jumping(t, f);
		if(f == 0) {
			emitLabel(label);
		}
	}
	
}
