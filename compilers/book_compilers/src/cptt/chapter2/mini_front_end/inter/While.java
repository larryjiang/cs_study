package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.symbols.Type;

public class While extends Stmt {
	private Expr expr;
	private Stmt stmt;
	
	public Expr getExpr() {
		return expr;
	}
	public Stmt getStmt() {
		return stmt;
	}
	
	public While() {
		expr = null;
		stmt = null;
	}
	
	public void init(Expr expr, Stmt s) {
		this.expr = expr;
		this.stmt = s;
		if(expr.getType() != Type.Bool) {
			throw new IllegalArgumentException("Expression should be a boolean expression");
		}
	}
	
	
	public void gen(int begin, int after) {
		this.after = after;
		expr.jumping(0, after);
		int label = newLabel();
		emitLabel(label);
		stmt.gen(label, begin);
		emit("goto L" + begin);
		
	}
	
	
	
}
