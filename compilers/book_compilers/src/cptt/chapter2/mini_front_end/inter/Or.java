package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Token;

public class Or extends Logical {
	
	
	public Or(Token tok, Expr x1, Expr x2) {
		super(tok, x1, x2);
		// TODO Auto-generated constructor stub
	}
	
	public void jumping(int t, int f) {
		int label = t != 0 ? t : newLabel();
		
		getExpr1().jumping(label, 0);
		getExpr2().jumping(t, f);
		if(t == 0) {
			emitLabel(label);
		}
	}
	

}
