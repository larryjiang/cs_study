package cptt.chapter2.mini_front_end.inter;

public class Stmt extends Node {
	public Stmt() {};
	
	
	public static Stmt NULLSTMT = new Stmt();
	
	
	public void gen(int begin, int after) {
		
	}
	
	protected int after = 0;
	
	public static Stmt ENCLOSING = Stmt.NULLSTMT;
}
