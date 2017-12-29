package cptt.chapter2.mini_front_end.inter;



public class Break extends Stmt {
	private Stmt stmt;
	
	public Break() {
		if(Stmt.ENCLOSING == Stmt.NULLSTMT) {
			throw new IllegalStateException("Unenclosed break");
		}
		this.stmt = Stmt.ENCLOSING;
	}
	
	public void gen(int begin, int after) {
		emit("goto L" + stmt.after);
	}
}
