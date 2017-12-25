package cptt.chapter2.mini_front_end.inter;


import cptt.chapter2.mini_front_end.lexer.Token;
import cptt.chapter2.mini_front_end.symbols.Array;
import cptt.chapter2.mini_front_end.symbols.Type;

public class Rel extends Logical{
	
	public Rel(Token tok, Expr x1, Expr x2) {
		super(tok,x1,x2);
	}
	
	public Type check(Type p1, Type p2) {
		
		if(p1 == p2 && !(p1 instanceof Array)) {
			return Type.Bool;
		}
		
		return null;
	}
	
	public void jumping(int t, int f) {
		Expr a = getExpr1().reduce();
		Expr b = getExpr2().reduce();
		
		String test = a.toString() + " " + getOp().toString() + " " + b.toString();
		emitJumps(test, t, f);
	}
	
	
}
