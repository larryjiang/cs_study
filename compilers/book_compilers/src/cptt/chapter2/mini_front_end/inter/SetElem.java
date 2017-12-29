package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.symbols.Array;
import cptt.chapter2.mini_front_end.symbols.Type;

public class SetElem extends Stmt {
	private Id array;
	private Expr index;
	private Expr expr;
	
	
	public SetElem(Access x, Expr y) {
		this.array = x.array;
		this.index = x.index;
		this.expr = y;
		if(check(x.getType(), expr.getType()) == null) {
			throw new IllegalArgumentException("Type does not match");
		}
	}
	
	private Type check(Type p1, Type p2) {
		if(p1 instanceof Array || p2 instanceof Array) {
			return null;
		}else if( p1 == p2) {
			return p2;
		}else if(Type.numeric(p1) && Type.numeric(p2)) {
			return p2;
		}else {
			return null;
		}
	}
	
	public void gen(int begin, int after) {
		String s1 = index.reduce().toString();
		String s2 = expr.reduce().toString();
		emit(array.toString() + "[" + s1 +  "] = " + s2);
	}

	public Id getArray() {
		return array;
	}

	public Expr getIndex() {
		return index;
	}

	public Expr getExpr() {
		return expr;
	}
	
}
