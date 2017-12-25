package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Token;
import cptt.chapter2.mini_front_end.symbols.Type;

public class Op extends Expr {

	Op(Token tok, Type p) {
		super(tok, p);
		// TODO Auto-generated constructor stub
	}
	
	public Expr reduce() {
		Expr x = gen();
		Temp t = new Temp(getType());
		emit(t.toString() + " = " + x.toString());
		return t;
	}

}
