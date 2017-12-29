package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Token;
import cptt.chapter2.mini_front_end.symbols.Type;

public class Logical extends Expr{
	
	private Expr expr1, expr2;
	
	public Logical(Token tok, Type p) {
		super(tok, p);
	}
	
	public Logical(Token tok, Expr x1, Expr x2){
		super(tok, null);
		this.expr1 = x1;
		this.expr2 = x2;
		if(check(expr1.getType(), expr2.getType())  == null) {
			error("Type Error");
		}
		
	}
	
	private Type check(Type p1, Type p2) {
		if(p1 == Type.Bool && p2 == Type.Bool) {
			return Type.Bool;
		}
		
		return null;
	}
	
	public Expr gen() {
		int f = newLabel();
		int a = newLabel();
		
		Temp temp = new Temp(getType());
		this.jumping(0, f);
		emit(temp.toString() + " =true");
		emit("goto L" + a);
		emitLabel(f);
		emit(temp.toString() + " =false");
		emitLabel(a);
		return temp;
	}
	
	public String toString() {
		return expr1.toString() + " " + getOp().toString() + " " + expr2.toString();
	}
	
	
	public Expr getExpr1() {
		return expr1;
	}
	
	public Expr getExpr2() {
		return expr2;
	}
	
	
	
}
