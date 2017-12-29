package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.symbols.Type;

public class Else extends Stmt{
	private Expr expr;
	private Stmt stmt1;
	private Stmt stmt2;
	
	public Expr getExpr() {
		return expr;
	}

	public Stmt getStmt1() {
		return stmt1;
	}

	public Stmt getStmt2() {
		return stmt2;
	}

	public Else(Expr x, Stmt s1, Stmt s2) {
		this.expr = x;
		this.stmt1 = s1;
		this.stmt2 = s2;
		
		if(expr.getType() != Type.Bool) {
			throw new IllegalArgumentException("Expression should not be a boolean type");
		}
	}
	
	public void gen(int begin, int after) {
		int label1 = newLabel();
		int label2 = newLabel();
		expr.jumping(0, label2);
		emitLabel(label1);
		stmt1.gen(label1, after);
		emit("goto L" + after);
		emitLabel(label2);
		stmt2.gen(label2, after);
		
	}
}
