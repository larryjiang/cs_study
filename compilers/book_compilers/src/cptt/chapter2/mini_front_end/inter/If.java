package cptt.chapter2.mini_front_end.inter;

public class If extends Stmt {
	private Expr expr;
	private Stmt stmt;
	
	public Expr getExpr() {
		return expr;
	}
	
	
	public Stmt getStmt() {
		return stmt;
	}
	
	
	public If(Expr x, Stmt s) {
		this.expr = x;
		this.stmt = s;
	}
	
	public void gen(int b, int a) {
		int label = newLabel();
		expr.jumping(0, a);
		emitLabel(label);
		stmt.gen(label, a);
	}
	
	
	
	
}
